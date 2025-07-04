package vn.edu.fpt.BeautyCenter.service.impl;

/*
 * Copyright(C) 2025,  FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-06-30>      <1.0>              TrungBD      First Implement
 */

import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.BeautyCenter.dto.request.BlogFilterParams;
import vn.edu.fpt.BeautyCenter.dto.request.BlogRequest;
import vn.edu.fpt.BeautyCenter.dto.response.BlogResponse;
import vn.edu.fpt.BeautyCenter.entity.Blog;
import vn.edu.fpt.BeautyCenter.entity.BlogCategory;
import vn.edu.fpt.BeautyCenter.entity.User;
import vn.edu.fpt.BeautyCenter.entity.enums.BlogStatus;
import vn.edu.fpt.BeautyCenter.entity.BlogTag;
import vn.edu.fpt.BeautyCenter.exception.AppException;
import vn.edu.fpt.BeautyCenter.exception.ErrorCode;
import vn.edu.fpt.BeautyCenter.mapper.BlogMapper;
import vn.edu.fpt.BeautyCenter.repository.BlogRepository;
import vn.edu.fpt.BeautyCenter.service.BlogService;
import vn.edu.fpt.BeautyCenter.service.BlogTagService;
import vn.edu.fpt.BeautyCenter.service.UserService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final BlogTagService blogTagService;
    private final BlogMapper blogMapper;
    private final UserService userService;

    @Override
    public BlogResponse createBlog(BlogRequest request, String authorId) {
        log.info("Creating new blog with title: {}", request.getTitle());
        // Check title uniqueness (excluding current blog)
        if (blogRepository.existsByTitle(request.getTitle())) {
            throw new AppException(ErrorCode.BLOG_TITLE_EXISTED);
        }

        // Tạo blog entity
        Blog blog = Blog.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .excerpt(request.getExcerpt())
                .thumbnailUrl(request.getThumbnailUrl())
                .status(request.getStatus())
                .featured(request.getFeatured())
                .viewCount(0)
                // Không set thời gian ở đây - để @PrePersist xử lý
                .build();

        // Set author
        User author = new User();
        author.setUserId(authorId);
        blog.setAuthor(author);

        // Process category
        if (request.getCategoryId() != null) {
            BlogCategory category = new BlogCategory();
            category.setId(request.getCategoryId());
            blog.setCategory(category);
        }

        // Process tags
        if (request.getTagNames() != null && !request.getTagNames().isEmpty()) {
            Set<BlogTag> tags = processBlogTags(request.getTagNames());
            blog.setBlogTags(tags);
        }

        // Save blog - @PrePersist sẽ tự động set thời gian
        Blog savedBlog = blogRepository.save(blog);

        return blogMapper.toResponse(savedBlog);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BlogResponse> getBlogById(String blogId) {
        return blogRepository.findById(blogId)
                .map(blog -> {
                    BlogResponse response = blogMapper.toResponse(blog);
                    // Xử lý trường hợp category null
                    if (blog.getCategory() == null) {
                        response.setCategoryId(null);
                        response.setCategoryName("Uncategorized");
                        response.setCategoryColor("#CCCCCC");
                    }
                    return response;
                });
    }

    @Override
    public BlogResponse updateBlog(String blogId, BlogRequest request) {
        log.info("Updating blog with ID: {}", blogId);

        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new AppException(ErrorCode.BLOG_NOT_FOUND));

        // Check title uniqueness (excluding current blog)
        if (blogRepository.existsByTitleAndBlogIdNot(request.getTitle(), blogId)) {
            throw new AppException(ErrorCode.BLOG_TITLE_EXISTED);
        }

        // Update blog fields
        blogMapper.updateEntity(blog, request);

        // Process tags
        if (request.getTagNames() != null) {
            // Remove old tag associations
            blog.getBlogTags().forEach(tag -> blogTagService.decrementUsageCount(tag.getId()));
            blog.getBlogTags().clear();

            // Add new tags
            if (!request.getTagNames().isEmpty()) {
                Set<BlogTag> tags = processBlogTags(request.getTagNames());
                blog.setBlogTags(tags);
            }
        }

        // Update published date if status changed to PUBLISHED
        if (request.getStatus() == BlogStatus.PUBLISHED && blog.getPublishedAt() == null) {
            blog.setPublishedAt(Instant.from(LocalDateTime.now()));
        }

        Blog updatedBlog = blogRepository.save(blog);
        log.info("Blog updated successfully with ID: {}", updatedBlog.getBlogId());

        return blogMapper.toResponse(updatedBlog);
    }

    @Override
    public void deleteBlog(String blogId) {
        log.info("Deleting blog with ID: {}", blogId);

        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new AppException(ErrorCode.BLOG_NOT_FOUND));

        // Decrement tag usage counts
        blog.getBlogTags().forEach(tag -> blogTagService.decrementUsageCount(tag.getId()));

        blogRepository.delete(blog);
        log.info("Blog deleted successfully with ID: {}", blogId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BlogResponse> getAllBlogs(Pageable pageable) {
        return blogRepository.findAll(pageable)
                .map(blogMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BlogResponse> getBlogsByStatus(BlogStatus status, Pageable pageable) {
        return blogRepository.findByStatusOrderByPublishedAtDesc(status, pageable)
                .map(blogMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BlogResponse> getBlogsByAuthor(String authorId, Pageable pageable) {
        return blogRepository.findByAuthorIdOrderByCreatedAtDesc(authorId, pageable)
                .map(blogMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BlogResponse> getBlogsByCategory(Integer categoryId, Pageable pageable) {
        return blogRepository.findByCategory_IdOrderByPublishedAtDesc(categoryId, pageable)
                .map(blogMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BlogResponse> searchBlogs(String keyword, Pageable pageable) {
        System.out.println("go here");
        return searchBlogsByStatus(keyword, BlogStatus.PUBLISHED, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BlogResponse> searchBlogsByStatus(String keyword, BlogStatus status, Pageable pageable) {
        System.out.println("goes here");
        return blogRepository.searchByKeywordAndStatus(keyword, String.valueOf(status), pageable)
                .map(blogMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BlogResponse> getBlogsByTags(List<String> tagNames, Pageable pageable) {
        return blogRepository.findByTagNamesAndStatus(tagNames, BlogStatus.PUBLISHED, pageable)
                .map(blogMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BlogResponse> getFeaturedBlogs() {
        return blogRepository.findByFeaturedTrueAndStatusOrderByPublishedAtDesc(BlogStatus.PUBLISHED)
                .stream()
                .map(blogMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BlogResponse> getRecentBlogs(int limit) {
        return blogRepository.findTop5ByStatusOrderByPublishedAtDesc(BlogStatus.PUBLISHED)
                .stream()
                .limit(limit)
                .map(blogMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BlogResponse> getPopularBlogs(int limit) {
        return blogRepository.findTop10ByStatusOrderByViewCountDescPublishedAtDesc(BlogStatus.PUBLISHED)
                .stream()
                .limit(limit)
                .map(blogMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BlogResponse publishBlog(String blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new AppException(ErrorCode.BLOG_NOT_FOUND));

        blog.setStatus(BlogStatus.PUBLISHED);

        Blog publishedBlog = blogRepository.save(blog);
        return blogMapper.toResponse(publishedBlog);
    }

    @Override
    public BlogResponse unpublishBlog(String blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new AppException(ErrorCode.BLOG_NOT_FOUND));

        blog.setStatus(BlogStatus.DRAFT);

        Blog unpublishedBlog = blogRepository.save(blog);
        return blogMapper.toResponse(unpublishedBlog);
    }

    @Override
    public void incrementViewCount(String blogId) {
        blogRepository.incrementViewCount(blogId);
    }

    @Override
    @Transactional(readOnly = true)
    public long getTotalBlogCount() {
        return blogRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public long getBlogCountByStatus(BlogStatus status) {
        return blogRepository.countByStatus(status);
    }

    @Override
    @Transactional(readOnly = true)
    public long getBlogCountByAuthor(String authorId) {
        return blogRepository.countByAuthorIdAndStatus(authorId, BlogStatus.PUBLISHED);
    }

    private Set<BlogTag> processBlogTags(List<String> tagNames) {
        return tagNames.stream()
                .filter(tagName -> tagName != null && !tagName.trim().isEmpty())
                .map(tagName -> blogTagService.findOrCreateTag(tagName.trim()))
                .collect(Collectors.toSet());
    }

    // Thêm vào BlogServiceImpl
    @Transactional(readOnly = true)
    public Page<BlogResponse> getBlogsWithAdvancedFilter(BlogFilterParams filterParams, Pageable pageable) {
        // Implement advanced filtering logic using Specification pattern or custom queries
        Specification<Blog> spec = Specification.where(null);

        if (filterParams.getKeyword() != null && !filterParams.getKeyword().trim().isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.or(
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("title")),
                                    "%" + filterParams.getKeyword().toLowerCase() + "%")
                    ));
        }

        if (filterParams.getStatus() != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("status"), filterParams.getStatus()));
        }

        if (filterParams.getCategoryId() != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("category").get("id"), filterParams.getCategoryId()));
        }

        if (filterParams.getFromDate() != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"),
                            filterParams.getFromDate().atStartOfDay()));
        }

        if (filterParams.getToDate() != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"),
                            filterParams.getToDate().atTime(23, 59, 59)));
        }

        if (filterParams.getAuthorId() != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("authorId"), filterParams.getAuthorId()));
        }

        if (filterParams.getFeatured() != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("featured"), filterParams.getFeatured()));
        }

        if (filterParams.getSelectedTags() != null && !filterParams.getSelectedTags().isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) -> {
                Join<Blog, BlogTag> tagJoin = root.join("tags");
                return tagJoin.get("tagName").in(filterParams.getSelectedTags());
            });
        }
        return blogRepository.findAll(spec, pageable).map(blogMapper::toResponse);
    }

}


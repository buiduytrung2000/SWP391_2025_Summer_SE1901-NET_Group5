package vn.edu.fpt.BeautyCenter.service;

/*
 * Copyright(C) 2025,  FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-06-30>      <1.0>              TrungBD      First Implement
 */
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.fpt.BeautyCenter.dto.request.BlogFilterParams;
import vn.edu.fpt.BeautyCenter.dto.request.BlogRequest;
import vn.edu.fpt.BeautyCenter.dto.response.BlogResponse;
import vn.edu.fpt.BeautyCenter.entity.enums.BlogStatus;

import java.util.List;
import java.util.Optional;

public interface BlogService {

    // CRUD operations
    BlogResponse createBlog(BlogRequest request, String authorId);
    Optional<BlogResponse> getBlogById(String blogId);
    BlogResponse updateBlog(String blogId, BlogRequest request);
    void deleteBlog(String blogId);

    // List and pagination
    Page<BlogResponse> getAllBlogs(Pageable pageable);
    Page<BlogResponse> getBlogsByStatus(BlogStatus status, Pageable pageable);
    Page<BlogResponse> getBlogsByAuthor(String authorId, Pageable pageable);
    Page<BlogResponse> getBlogsByCategory(Integer categoryId, Pageable pageable);

    // Search functionality
    Page<BlogResponse> searchBlogs(String keyword, Pageable pageable);
    Page<BlogResponse> searchBlogsByStatus(String keyword, BlogStatus status, Pageable pageable);
    Page<BlogResponse> getBlogsByTags(List<String> tagNames, Pageable pageable);

    // Featured and popular
    List<BlogResponse> getFeaturedBlogs();
    List<BlogResponse> getRecentBlogs(int limit);
    List<BlogResponse> getPopularBlogs(int limit);

    // Blog actions
    BlogResponse publishBlog(String blogId);
    BlogResponse unpublishBlog(String blogId);
    void incrementViewCount(String blogId);

    // Statistics
    long getTotalBlogCount();
    long getBlogCountByStatus(BlogStatus status);
    long getBlogCountByAuthor(String authorId);

    // Thêm method vào BlogService interface hiện có
    Page<BlogResponse> getBlogsWithAdvancedFilter(BlogFilterParams filterParams, Pageable pageable);

}


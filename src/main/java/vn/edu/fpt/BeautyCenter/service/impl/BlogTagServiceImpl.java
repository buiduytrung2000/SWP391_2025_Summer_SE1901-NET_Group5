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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.BeautyCenter.dto.response.BlogTagResponse;
import vn.edu.fpt.BeautyCenter.entity.BlogTag;
import vn.edu.fpt.BeautyCenter.mapper.BlogTagMapper;
import vn.edu.fpt.BeautyCenter.repository.BlogTagRepository;
import vn.edu.fpt.BeautyCenter.service.BlogTagService;
import vn.edu.fpt.BeautyCenter.util.SlugUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BlogTagServiceImpl implements BlogTagService {

    private final BlogTagRepository blogTagRepository;
    private final BlogTagMapper blogTagMapper;

    @Override
    public BlogTag findOrCreateTag(String tagName) {
        return blogTagRepository.findByTagName(tagName)
                .orElseGet(() -> {
                    BlogTag newTag = BlogTag.builder()
                            .tagName(tagName)
                            .tagSlug(SlugUtils.generateSlug(tagName))
                            .usageCount(0)
                            .build();
                    BlogTag savedTag = blogTagRepository.save(newTag);
                    incrementUsageCount(savedTag.getId());
                    return savedTag;
                });
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BlogTag> findByName(String tagName) {
        return blogTagRepository.findByTagName(tagName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BlogTagResponse> getAllTags() {
        return blogTagRepository.findAllByOrderByTagNameAsc()
                .stream()
                .map(blogTagMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BlogTagResponse> getPopularTags(int limit) {
        return blogTagRepository.findTop10ByOrderByUsageCountDesc()
                .stream()
                .limit(limit)
                .map(blogTagMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void incrementUsageCount(Integer tagId) {
        blogTagRepository.incrementUsageCount(tagId);
    }

    @Override
    public void decrementUsageCount(Integer tagId) {
        blogTagRepository.decrementUsageCount(tagId);
    }

    @Override
    public void deleteUnusedTags() {
        List<BlogTag> unusedTags = blogTagRepository.findAll()
                .stream()
                .filter(tag -> tag.getUsageCount() == 0)
                .collect(Collectors.toList());

        if (!unusedTags.isEmpty()) {
            blogTagRepository.deleteAll(unusedTags);
            log.info("Deleted {} unused tags", unusedTags.size());
        }
    }
}

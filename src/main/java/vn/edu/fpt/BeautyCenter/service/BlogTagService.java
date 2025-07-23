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

import vn.edu.fpt.BeautyCenter.dto.response.BlogTagResponse;
import vn.edu.fpt.BeautyCenter.entity.BlogTag;

import java.util.List;
import java.util.Optional;

public interface BlogTagService {

    BlogTag findOrCreateTag(String tagName);
    Optional<BlogTag> findByName(String tagName);
    List<BlogTagResponse> getAllTags();
    List<BlogTagResponse> getPopularTags(int limit);
    void incrementUsageCount(Integer tagId);
    void decrementUsageCount(Integer tagId);
    void deleteUnusedTags();
}


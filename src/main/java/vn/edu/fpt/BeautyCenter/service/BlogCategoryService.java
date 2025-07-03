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

import vn.edu.fpt.BeautyCenter.dto.request.BlogCategoryRequest;
import vn.edu.fpt.BeautyCenter.dto.response.BlogCategoryResponse;

import java.util.List;
import java.util.Optional;

public interface BlogCategoryService {

    BlogCategoryResponse createCategory(BlogCategoryRequest request);
    Optional<BlogCategoryResponse> getCategoryById(Integer categoryId);
    List<BlogCategoryResponse> getAllCategories();
    BlogCategoryResponse updateCategory(Integer categoryId, BlogCategoryRequest request);
    void deleteCategory(Integer categoryId);
    List<BlogCategoryResponse> getCategoriesWithBlogCount();
}


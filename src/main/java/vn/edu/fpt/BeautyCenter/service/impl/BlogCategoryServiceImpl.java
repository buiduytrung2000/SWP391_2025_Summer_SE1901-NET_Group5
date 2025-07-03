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
import vn.edu.fpt.BeautyCenter.dto.request.BlogCategoryRequest;
import vn.edu.fpt.BeautyCenter.dto.response.BlogCategoryResponse;
import vn.edu.fpt.BeautyCenter.entity.BlogCategory;
import vn.edu.fpt.BeautyCenter.exception.AppException;
import vn.edu.fpt.BeautyCenter.exception.ErrorCode;
import vn.edu.fpt.BeautyCenter.mapper.BlogCategoryMapper;
import vn.edu.fpt.BeautyCenter.repository.BlogCategoryRepository;
import vn.edu.fpt.BeautyCenter.service.BlogCategoryService;
import vn.edu.fpt.BeautyCenter.util.SlugUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BlogCategoryServiceImpl implements BlogCategoryService {

    private final BlogCategoryRepository categoryRepository;
    private final BlogCategoryMapper categoryMapper;

    @Override
    public BlogCategoryResponse createCategory(BlogCategoryRequest request) {
        if (categoryRepository.existsByCategoryName(request.getCategoryName())) {
            throw new AppException(ErrorCode.CATEGORY_EXISTED);
        }

        BlogCategory category = categoryMapper.toEntity(request);
        category.setCategorySlug(SlugUtils.generateSlug(request.getCategoryName()));

        BlogCategory savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(savedCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BlogCategoryResponse> getCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId)
                .map(categoryMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BlogCategoryResponse> getAllCategories() {
        return categoryRepository.findAllByOrderByCategoryNameAsc()
                .stream()
                .map(categoryMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BlogCategoryResponse updateCategory(Integer categoryId, BlogCategoryRequest request) {
        BlogCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        if (categoryRepository.existsByCategoryNameAndIdNot(request.getCategoryName(), categoryId)) {
            throw new AppException(ErrorCode.CATEGORY_EXISTED);
        }

        categoryMapper.updateEntity(category, request);
        category.setCategorySlug(SlugUtils.generateSlug(request.getCategoryName()));

        BlogCategory updatedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(updatedCategory);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        BlogCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        categoryRepository.delete(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BlogCategoryResponse> getCategoriesWithBlogCount() {
        return categoryRepository.findCategoriesWithBlogCount()
                .stream()
                .map(result -> {
                    BlogCategory category = (BlogCategory) result[0];
                    Long blogCount = (Long) result[1];
                    BlogCategoryResponse response = categoryMapper.toResponse(category);
                    response.setBlogCount(blogCount);
                    return response;
                })
                .collect(Collectors.toList());
    }
}


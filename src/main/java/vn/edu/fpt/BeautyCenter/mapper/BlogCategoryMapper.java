package vn.edu.fpt.BeautyCenter.mapper;

/*
 * Copyright(C) 2025,  FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-06-30>      <1.0>              TrungBD      First Implement
 */
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import vn.edu.fpt.BeautyCenter.dto.request.BlogCategoryRequest;
import vn.edu.fpt.BeautyCenter.dto.response.BlogCategoryResponse;
import vn.edu.fpt.BeautyCenter.entity.BlogCategory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BlogCategoryMapper {

    BlogCategoryMapper INSTANCE = Mappers.getMapper(BlogCategoryMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categorySlug", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "blogs", ignore = true)
    BlogCategory toEntity(BlogCategoryRequest request);

    @Mapping(target = "blogCount", ignore = true)
    BlogCategoryResponse toResponse(BlogCategory entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categorySlug", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "blogs", ignore = true)
    void updateEntity(@MappingTarget BlogCategory entity, BlogCategoryRequest request);

    // Helper method để map list
    List<BlogCategoryResponse> toResponseList(List<BlogCategory> categories);

    // Custom mapping với blog count
    @Mapping(target = "blogCount", source = "blogCount")
    BlogCategoryResponse toResponseWithCount(BlogCategory entity, Long blogCount);
    /**
     * Converts Instant to LocalDateTime in UTC timezone.
     *
     * @param instant the Instant to convert
     * @return corresponding LocalDateTime, or null if input is null
     */
    default LocalDateTime instantToLocalDateTime(Instant instant) {
        return instant == null ? null : LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    }
}


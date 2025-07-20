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
import vn.edu.fpt.BeautyCenter.dto.request.BlogRequest;
import vn.edu.fpt.BeautyCenter.dto.response.BlogResponse;
import vn.edu.fpt.BeautyCenter.entity.Blog;
import vn.edu.fpt.BeautyCenter.entity.BlogCategory;
import vn.edu.fpt.BeautyCenter.entity.BlogTag;
import vn.edu.fpt.BeautyCenter.entity.BlogImage;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Collections;

@Mapper(componentModel = "spring",
        uses = {BlogCategoryMapper.class, UserMapper.class, TimeMapper.class},
        imports = {java.util.Collections.class},injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BlogMapper {

    @Mapping(target = "blogId", ignore = true)
    Blog toEntity(BlogRequest request);

    @Named("standardResponse")
    @Mapping(target = "authorName", source = "author.fullName")
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.categoryName")
    @Mapping(target = "categoryColor", source = "category.colorCode")
    @Mapping(target = "authorId",source = "author.userId")
    @Mapping(target = "publishedAt", source = "publishedAt", qualifiedByName = "instantToLocalDateTime")
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "instantToLocalDateTime")
    @Mapping(target = "updatedAt", source = "updatedAt", qualifiedByName = "instantToLocalDateTime")
    @Mapping(target = "tags", source = "blogTags", qualifiedByName = "mapTagsToNames")
    @Mapping(target = "imageUrls", source = "blogImages", qualifiedByName = "mapImagesToUrls")
    BlogResponse toResponse(Blog entity);

    @InheritInverseConfiguration(name = "toEntity")
    void updateEntity(@MappingTarget Blog entity, BlogRequest request);

    @Named("mapTagsToNames")
    default List<String> mapTagsToNames(Set<BlogTag> tags) {
        if (tags == null || tags.isEmpty()) {
            return Collections.emptyList();
        }
        return tags.stream()
                .map(BlogTag::getTagName)
                .sorted()
                .collect(Collectors.toList());
    }

    @Named("mapImagesToUrls")
    default List<String> mapImagesToUrls(Set<BlogImage> images) {
        if (images == null || images.isEmpty()) {
            return Collections.emptyList();
        }
        return images.stream()
                .map(BlogImage::getImageUrl)
                .sorted()
                .collect(Collectors.toList());
    }

    // Helper method để map list entities
    @IterableMapping(qualifiedByName = "standardResponse")
    List<BlogResponse> toResponseList(List<Blog> blogs);

    // Custom mapping cho summary response (không cần full content)
    @Mapping(target = "content", ignore = true)
    @Mapping(target = "authorName", source = "author.fullName")
    @Mapping(target = "categoryName", source = "category.categoryName")
    @Mapping(target = "categoryColor", source = "category.colorCode")
    @Mapping(target = "tags", source = "blogTags", qualifiedByName = "mapTagsToNames")
    @Mapping(target = "imageUrls", expression = "java(Collections.emptyList())")
    BlogResponse toSummaryResponse(Blog entity);

    @Named("mapCategoryId")
    default Integer mapCategoryId(BlogCategory category) {
        return category != null ? category.getId() : null;
    }

    // Custom conversion methods


}


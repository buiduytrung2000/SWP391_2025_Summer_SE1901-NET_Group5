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
import vn.edu.fpt.BeautyCenter.dto.request.BlogImageRequest;
import vn.edu.fpt.BeautyCenter.dto.response.BlogImageResponse;
import vn.edu.fpt.BeautyCenter.entity.BlogImage;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BlogImageMapper {

    BlogImageMapper INSTANCE = Mappers.getMapper(BlogImageMapper.class);

    @Mapping(target = "imageId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "blog", ignore = true)
    BlogImage toEntity(BlogImageRequest request);

    @Mapping(target = "blogId", source = "blog.blogId")
    BlogImageResponse toResponse(BlogImage entity);

    @Mapping(target = "imageId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "blog", ignore = true)
    void updateEntity(@MappingTarget BlogImage entity, BlogImageRequest request);

    // Helper method để map list
    List<BlogImageResponse> toResponseList(List<BlogImage> images);
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

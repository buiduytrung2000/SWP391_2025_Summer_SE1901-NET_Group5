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
import vn.edu.fpt.BeautyCenter.dto.request.BlogTagRequest;
import vn.edu.fpt.BeautyCenter.dto.response.BlogTagResponse;
import vn.edu.fpt.BeautyCenter.entity.BlogTag;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BlogTagMapper {

    BlogTagMapper INSTANCE = Mappers.getMapper(BlogTagMapper.class);

    @Mapping(target = "id", ignore = true)
    BlogTag toEntity(BlogTagRequest request);
    @Named("standardResponse")
    BlogTagResponse toResponse(BlogTag entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tagSlug", ignore = true)
    @Mapping(target = "usageCount", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "blogs", ignore = true)
    void updateEntity(@MappingTarget BlogTag entity, BlogTagRequest request);

    // Helper method để map list
    @IterableMapping(qualifiedByName = "standardResponse")
    List<BlogTagResponse> toResponseList(List<BlogTag> tags);

    // Custom mapping cho popular tags
    @Mapping(target = "popularity", source = "usageCount", qualifiedByName = "calculatePopularity")
    BlogTagResponse toPopularTagResponse(BlogTag entity);

    @Named("calculatePopularity")
    default String calculatePopularity(Integer usageCount) {
        if (usageCount == null || usageCount == 0) return "New";
        if (usageCount < 5) return "Low";
        if (usageCount < 20) return "Medium";
        return "High";
    }
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


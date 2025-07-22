package vn.edu.fpt.BeautyCenter.mapper;

/*
 * Copyright(C) 2025,  FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-07-21>      <1.0>              TrungBD      First Implement
 */

import org.mapstruct.*;
import vn.edu.fpt.BeautyCenter.dto.request.CommentRequest;
import vn.edu.fpt.BeautyCenter.dto.response.CommentResponse;
import vn.edu.fpt.BeautyCenter.entity.Comment;
import java.util.List;

@Mapper(componentModel = "spring",
        uses = {TimeMapper.class, UserMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CommentMapper {

    @Mapping(target = "commentId", ignore = true)
    @Mapping(target = "blog", ignore = true) // Set manually in service
    @Mapping(target = "author", ignore = true) // Set manually in service
    @Mapping(target = "createdAt", ignore = true) // Set by @PrePersist
    @Mapping(target = "updatedAt", ignore = true) // Set by @PrePersist
    @Mapping(target = "likeCount", ignore = true) // Set by @PrePersist
    @Mapping(target = "isActive", ignore = true) // Set by @PrePersist
    @Mapping(target = "commentLikes", ignore = true)
    Comment toEntity(CommentRequest request);

    @Named("standardResponse")
    @Mapping(target = "blogId", source = "blog.blogId")
    @Mapping(target = "blogTitle", source = "blog.title")
    @Mapping(target = "authorId", source = "author.userId")
    @Mapping(target = "authorName", source = "author.fullName")
    @Mapping(target = "authorAvatar", source = "author.avatarUrl")
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "instantToLocalDateTime")
    @Mapping(target = "updatedAt", source = "updatedAt", qualifiedByName = "instantToLocalDateTime")
    @Mapping(target = "isLikedByCurrentUser", ignore = true) // Set manually in service
    CommentResponse toResponse(Comment comment);

    @InheritInverseConfiguration(name = "toEntity")
    @Mapping(target = "blog", ignore = true)
    @Mapping(target = "author", ignore = true)
    void updateEntity(@MappingTarget Comment comment, CommentRequest request);

    @IterableMapping(qualifiedByName = "standardResponse")
    List<CommentResponse> toResponseList(List<Comment> comments);
}


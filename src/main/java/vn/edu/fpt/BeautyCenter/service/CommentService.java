package vn.edu.fpt.BeautyCenter.service;

/*
 * Copyright(C) 2025,  FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-07-21>      <1.0>              TrungBD      First Implement
 */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.fpt.BeautyCenter.dto.request.CommentRequest;
import vn.edu.fpt.BeautyCenter.dto.response.CommentResponse;
import java.util.List;
import java.util.Optional;

public interface CommentService {

    // CRUD operations
    CommentResponse createComment(CommentRequest request, String authorId);
    Optional<CommentResponse> getCommentById(String commentId);
    CommentResponse updateComment(String commentId, CommentRequest request, String currentUserId);
    void deleteComment(String commentId, String currentUserId);

    // Get comments for a blog
    List<CommentResponse> getCommentsByBlogId(String blogId);
    List<CommentResponse> getCommentsByBlogId(String blogId, String currentUserId);
    Page<CommentResponse> getCommentsByBlogIdPaginated(String blogId, Pageable pageable);
    Page<CommentResponse> getCommentsByBlogIdPaginated(String blogId, String currentUserId, Pageable pageable);

    // Get comments by author
    Page<CommentResponse> getCommentsByAuthor(String authorId, Pageable pageable);

    // Like/Unlike operations
    boolean toggleCommentLike(String commentId, String userId);
    boolean isCommentLikedByUser(String commentId, String userId);
    int getCommentLikeCount(String commentId);

    // Statistics
    long getCommentCountByBlog(String blogId);
    long getCommentCountByAuthor(String authorId);
    List<CommentResponse> getPopularCommentsByBlog(String blogId);
    List<CommentResponse> getRecentCommentsByBlog(String blogId, int limit);

    // Admin operations
    Page<CommentResponse> getAllComments(Pageable pageable);
    void softDeleteComment(String commentId);
    void activateComment(String commentId);

    // Utility methods
    boolean hasUserCommentedOnBlog(String blogId, String userId);
}


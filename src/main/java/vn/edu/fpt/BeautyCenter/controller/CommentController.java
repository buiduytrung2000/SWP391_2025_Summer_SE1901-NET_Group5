package vn.edu.fpt.BeautyCenter.controller;

/*
 * Copyright(C) 2025,  FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-07-21>      <1.0>              TrungBD      First Implement
 */

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.fpt.BeautyCenter.dto.request.CommentRequest;
import vn.edu.fpt.BeautyCenter.dto.response.CommentResponse;
import vn.edu.fpt.BeautyCenter.entity.User;
import vn.edu.fpt.BeautyCenter.entity.enums.Role;
import vn.edu.fpt.BeautyCenter.exception.AppException;
import vn.edu.fpt.BeautyCenter.service.CommentService;
import vn.edu.fpt.BeautyCenter.service.NotificationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin/comments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentController {

    CommentService commentService;
    NotificationService notificationService;

    /**
     * Create a new comment for a blog post.
     * This endpoint is called via AJAX from the blog view page.
     */
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addComment(@Valid @RequestBody CommentRequest request,
                                        HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                return ResponseEntity.status(401)
                        .body(Map.of("success", false, "message", "Please login to comment"));
            }

            CommentResponse comment = commentService.createComment(request, user.getUserId());

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Comment added successfully!");
            response.put("comment", comment);

            return ResponseEntity.ok(response);

        } catch (AppException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("success", false, "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "Error creating comment"));
        }
    }
    /**
     * Update an existing comment.
     * Only the comment author can edit their own comments.
     */
    @PutMapping("/{commentId}")
    @ResponseBody
    public ResponseEntity<?> updateComment(@PathVariable String commentId,
                                           @Valid @RequestBody CommentRequest request,
                                           HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                return ResponseEntity.status(401)
                        .body(Map.of("success", false, "message", "Please login to edit comments"));
            }

            // Check if comment exists and belongs to current user
            Optional<CommentResponse> existingComment = commentService.getCommentById(commentId);
            if (existingComment.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("success", false, "message", "Comment not found"));
            }

            if (!existingComment.get().getAuthorId().equals(user.getUserId())) {
                return ResponseEntity.status(403)
                        .body(Map.of("success", false, "message", "You can only edit your own comments"));
            }

            CommentResponse updatedComment = commentService.updateComment(commentId, request, user.getUserId());

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Comment updated successfully!");
            response.put("comment", updatedComment);

            return ResponseEntity.ok(response);

        } catch (AppException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("success", false, "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "Error updating comment"));
        }
    }

    /**
     * Delete a comment (soft delete).
     * Only the comment author can delete their own comments.
     */
    @DeleteMapping("/{commentId}")
    @ResponseBody
    public ResponseEntity<?> deleteComment(@PathVariable String commentId,
                                           HttpSession session) {
         try {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                return ResponseEntity.status(401)
                        .body(Map.of("success", false, "message", "Please login to delete comments"));
            }

            // Check if comment exists and belongs to current user
            Optional<CommentResponse> existingComment = commentService.getCommentById(commentId);
            if (existingComment.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("success", false, "message", "Comment not found"));
            }

            if (!existingComment.get().getAuthorId().equals(user.getUserId())) {
                if(user.getRole().equals(Role.admin)){
                    commentService.deleteComment(commentId);
                    Map<String, Object> response = new HashMap<>();
                    response.put("success", true);
                    response.put("message", "Comment deleted successfully!");
                    response.put("commentId", commentId);

                    return ResponseEntity.ok(response);
                }
                return ResponseEntity.status(403)
                        .body(Map.of("success", false, "message", "You can only delete your own comments"));
            }

            commentService.deleteComment(commentId, user.getUserId());

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Comment deleted successfully!");
            response.put("commentId", commentId);

            return ResponseEntity.ok(response);

        } catch (AppException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("success", false, "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "Error deleting comment"));
        }
    }

    /**
     * Toggle like/unlike for a comment.
     */
    @PostMapping("/{commentId}/like")
    @ResponseBody
    public ResponseEntity<?> toggleCommentLike(@PathVariable String commentId,
                                               HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                return ResponseEntity.status(401)
                        .body(Map.of("success", false, "message", "Please login to like comments"));
            }

            boolean isLiked = commentService.toggleCommentLike(commentId, user.getUserId());
            int likeCount = commentService.getCommentLikeCount(commentId);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("isLiked", isLiked);
            response.put("likeCount", likeCount);
            response.put("message", isLiked ? "Comment liked!" : "Comment unliked!");

            return ResponseEntity.ok(response);

        } catch (AppException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("success", false, "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "Error processing like"));
        }
    }


}


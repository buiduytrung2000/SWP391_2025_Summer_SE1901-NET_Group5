package vn.edu.fpt.BeautyCenter.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.BeautyCenter.dto.request.CommentRequest;
import vn.edu.fpt.BeautyCenter.dto.response.CommentResponse;
import vn.edu.fpt.BeautyCenter.entity.*;
import vn.edu.fpt.BeautyCenter.entity.enums.BlogStatus;
import vn.edu.fpt.BeautyCenter.exception.AppException;
import vn.edu.fpt.BeautyCenter.exception.ErrorCode;
import vn.edu.fpt.BeautyCenter.mapper.CommentMapper;
import vn.edu.fpt.BeautyCenter.repository.*;
import vn.edu.fpt.BeautyCenter.service.CommentService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @Override
    public CommentResponse createComment(CommentRequest request, String authorId) {
        log.info("Creating new comment for blog: {} by user: {}", request.getBlogId(), authorId);

        // Validate blog exists and is active
        Blog blog = blogRepository.findById(request.getBlogId())
                .orElseThrow(() -> new AppException(ErrorCode.BLOG_NOT_FOUND));

        if (blog.getStatus() != BlogStatus.PUBLISHED) {
            throw new AppException(ErrorCode.BLOG_NOT_FOUND);
        }

        // Validate user exists
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        // Create comment entity
        Comment comment = commentMapper.toEntity(request);
        comment.setBlog(blog);
        comment.setAuthor(author);

        // Save comment
        Comment savedComment = commentRepository.save(comment);

        log.info("Comment created successfully with ID: {}", savedComment.getCommentId());

        CommentResponse response = commentMapper.toResponse(savedComment);
        response.setIsLikedByCurrentUser(false); // New comment is not liked by author

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CommentResponse> getCommentById(String commentId) {
        log.debug("Fetching comment with ID: {}", commentId);

        return commentRepository.findById(commentId)
                .filter(Comment::getIsActive)
                .map(comment -> {
                    CommentResponse response = commentMapper.toResponse(comment);
                    response.setIsLikedByCurrentUser(false); // Default value
                    return response;
                });
    }

    @Override
    public CommentResponse updateComment(String commentId, CommentRequest request, String currentUserId) {
        log.info("Updating comment: {} by user: {}", commentId, currentUserId);

        // Find existing comment
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND));

        if (!comment.getIsActive()) {
            throw new AppException(ErrorCode.COMMENT_NOT_FOUND);
        }

        // Check authorization - only author can update their comment
        if (!comment.getAuthor().getUserId().equals(currentUserId)) {
            throw new AppException(ErrorCode.COMMENT_ACCESS_DENIED);
        }

        // Update comment content
        commentMapper.updateEntity(comment, request);

        // Save updated comment
        Comment updatedComment = commentRepository.save(comment);

        log.info("Comment updated successfully: {}", commentId);

        CommentResponse response = commentMapper.toResponse(updatedComment);
        response.setIsLikedByCurrentUser(
                commentLikeRepository.existsByCommentCommentIdAndUserUserId(commentId, currentUserId)
        );

        return response;
    }

    @Override
    public void deleteComment(String commentId, String currentUserId) {
        log.info("Deleting comment: {} by user: {}", commentId, currentUserId);

        // Find existing comment
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND));

        if (!comment.getIsActive()) {
            throw new AppException(ErrorCode.COMMENT_NOT_FOUND);
        }

        // Check authorization - only author can delete their comment
        if (!comment.getAuthor().getUserId().equals(currentUserId)) {
            throw new AppException(ErrorCode.COMMENT_ACCESS_DENIED);
        }

        // Soft delete
        comment.setIsActive(false);
        commentRepository.save(comment);

        log.info("Comment soft deleted successfully: {}", commentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsByBlogId(String blogId) {
        return getCommentsByBlogId(blogId, null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsByBlogId(String blogId, String currentUserId) {
        log.debug("Fetching comments for blog: {} with user: {}", blogId, currentUserId);

        List<Comment> comments = commentRepository.findByBlogBlogIdAndIsActiveTrueOrderByCreatedAtDesc(blogId);
        List<CommentResponse> responses = commentMapper.toResponseList(comments);

        // Set like status for current user if provided
        if (currentUserId != null && !responses.isEmpty()) {
            Set<String> likedCommentIds = commentLikeRepository.findLikedCommentIds(
                    responses.stream().map(CommentResponse::getCommentId).collect(Collectors.toList()),
                    currentUserId
            );

            responses.forEach(response ->
                    response.setIsLikedByCurrentUser(likedCommentIds.contains(response.getCommentId()))
            );
        } else {
            responses.forEach(response -> response.setIsLikedByCurrentUser(false));
        }

        return responses;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CommentResponse> getCommentsByBlogIdPaginated(String blogId, Pageable pageable) {
        return getCommentsByBlogIdPaginated(blogId, null, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CommentResponse> getCommentsByBlogIdPaginated(String blogId, String currentUserId, Pageable pageable) {
        log.debug("Fetching paginated comments for blog: {} with user: {}, page: {}",
                blogId, currentUserId, pageable.getPageNumber());

        Page<Comment> commentsPage = commentRepository.findByBlogBlogIdAndIsActiveTrueOrderByCreatedAtDesc(blogId, pageable);

        return commentsPage.map(comment -> {
            CommentResponse response = commentMapper.toResponse(comment);

            if (currentUserId != null) {
                response.setIsLikedByCurrentUser(
                        commentLikeRepository.existsByCommentCommentIdAndUserUserId(
                                comment.getCommentId(), currentUserId)
                );
            } else {
                response.setIsLikedByCurrentUser(false);
            }

            return response;
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CommentResponse> getCommentsByAuthor(String authorId, Pageable pageable) {
        log.debug("Fetching comments by author: {}, page: {}", authorId, pageable.getPageNumber());

        Page<Comment> commentsPage = commentRepository.findByAuthorUserIdAndIsActiveTrueOrderByCreatedAtDesc(authorId, pageable);

        return commentsPage.map(comment -> {
            CommentResponse response = commentMapper.toResponse(comment);
            response.setIsLikedByCurrentUser(false); // Don't check like status in this context
            return response;
        });
    }

    @Override
    public boolean toggleCommentLike(String commentId, String userId) {
        log.info("Toggling like for comment: {} by user: {}", commentId, userId);

        // Validate comment exists and is active
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND));

        if (!comment.getIsActive()) {
            throw new AppException(ErrorCode.COMMENT_NOT_FOUND);
        }

        // Validate user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        // Check if like already exists
        Optional<CommentLike> existingLike = commentLikeRepository.findByCommentCommentIdAndUserUserId(commentId, userId);

        if (existingLike.isPresent()) {
            // Unlike - remove the like
            commentLikeRepository.delete(existingLike.get());
            commentRepository.updateLikeCount(commentId, -1);
            log.info("Comment unliked: {} by user: {}", commentId, userId);
            return false;
        } else {
            // Like - create new like
            CommentLike commentLike = CommentLike.builder()
                    .comment(comment)
                    .user(user)
                    .build();
            commentLikeRepository.save(commentLike);
            commentRepository.updateLikeCount(commentId, 1);
            log.info("Comment liked: {} by user: {}", commentId, userId);
            return true;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isCommentLikedByUser(String commentId, String userId) {
        return commentLikeRepository.existsByCommentCommentIdAndUserUserId(commentId, userId);
    }

    @Override
    @Transactional(readOnly = true)
    public int getCommentLikeCount(String commentId) {
        return Math.toIntExact(commentLikeRepository.countByCommentCommentId(commentId));
    }

    @Override
    @Transactional(readOnly = true)
    public long getCommentCountByBlog(String blogId) {
        return commentRepository.countByBlogBlogIdAndIsActiveTrue(blogId);
    }

    @Override
    @Transactional(readOnly = true)
    public long getCommentCountByAuthor(String authorId) {
        return commentRepository.countByAuthorUserIdAndIsActiveTrue(authorId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponse> getPopularCommentsByBlog(String blogId) {
        log.debug("Fetching popular comments for blog: {}", blogId);

        List<Comment> comments = commentRepository.findTop2ByBlogBlogIdAndIsActiveTrueOrderByLikeCountDescCreatedAtDesc(blogId);
        List<CommentResponse> responses = commentMapper.toResponseList(comments);

        // Don't check like status for popular comments listing
        responses.forEach(response -> response.setIsLikedByCurrentUser(false));

        return responses;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponse> getRecentCommentsByBlog(String blogId, int limit) {
        log.debug("Fetching {} recent comments for blog: {}", limit, blogId);

        Pageable pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Comment> commentsPage = commentRepository.findByBlogBlogIdAndIsActiveTrueOrderByCreatedAtDesc(blogId, pageable);

        List<CommentResponse> responses = commentMapper.toResponseList(commentsPage.getContent());
        responses.forEach(response -> response.setIsLikedByCurrentUser(false));

        return responses;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CommentResponse> getAllComments(Pageable pageable) {
        log.debug("Fetching all comments with pagination: {}", pageable);

        Page<Comment> commentsPage = commentRepository.findByIsActiveTrueOrderByCreatedAtDesc(pageable);

        return commentsPage.map(comment -> {
            CommentResponse response = commentMapper.toResponse(comment);
            response.setIsLikedByCurrentUser(false); // Admin view doesn't need like status
            return response;
        });
    }

    @Override
    public void softDeleteComment(String commentId) {
        log.info("Admin soft deleting comment: {}", commentId);

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND));

        comment.setIsActive(false);
        commentRepository.save(comment);

        log.info("Comment soft deleted by admin: {}", commentId);
    }

    @Override
    public void activateComment(String commentId) {
        log.info("Admin activating comment: {}", commentId);

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND));

        comment.setIsActive(true);
        commentRepository.save(comment);

        log.info("Comment activated by admin: {}", commentId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean hasUserCommentedOnBlog(String blogId, String userId) {
        return commentRepository.existsByBlogBlogIdAndAuthorUserIdAndIsActiveTrue(blogId, userId);
    }

    // Additional utility methods for better functionality

    /**
     * Get comment statistics for a blog
     */
    @Transactional(readOnly = true)
    public CommentStatistics getCommentStatistics(String blogId) {
        log.debug("Getting comment statistics for blog: {}", blogId);

        long totalComments = getCommentCountByBlog(blogId);
        List<CommentResponse> popularComments = getPopularCommentsByBlog(blogId);

        return CommentStatistics.builder()
                .totalComments(totalComments)
                .popularComments(popularComments)
                .build();
    }

    /**
     * Batch update comment like counts (useful for data consistency)
     */
    @Transactional
    public void updateAllCommentLikeCounts() {
        log.info("Batch updating all comment like counts");

        List<Comment> allComments = commentRepository.findAll();

        for (Comment comment : allComments) {
            long actualLikeCount = commentLikeRepository.countByCommentCommentId(comment.getCommentId());
            comment.setLikeCount(Math.toIntExact(actualLikeCount));
        }

        commentRepository.saveAll(allComments);
        log.info("Updated like counts for {} comments", allComments.size());
    }

    /**
     * Get user's comment activity summary
     */
    @Transactional(readOnly = true)
    public UserCommentActivity getUserCommentActivity(String userId) {
        log.debug("Getting comment activity for user: {}", userId);

        long totalComments = getCommentCountByAuthor(userId);
        long totalLikesReceived = commentRepository.findByAuthorUserIdAndIsActiveTrueOrderByCreatedAtDesc(userId, Pageable.unpaged())
                .getContent()
                .stream()
                .mapToLong(comment -> comment.getLikeCount())
                .sum();

        long totalLikesGiven = commentLikeRepository.findByUserUserIdOrderByCreatedAtDesc(userId).size();

        return UserCommentActivity.builder()
                .userId(userId)
                .totalComments(totalComments)
                .totalLikesReceived(totalLikesReceived)
                .totalLikesGiven(totalLikesGiven)
                .build();
    }

    // Inner classes for statistics
    @lombok.Builder
    @lombok.Data
    public static class CommentStatistics {
        private long totalComments;
        private List<CommentResponse> popularComments;
    }

    @lombok.Builder
    @lombok.Data
    public static class UserCommentActivity {
        private String userId;
        private long totalComments;
        private long totalLikesReceived;
        private long totalLikesGiven;
    }
}

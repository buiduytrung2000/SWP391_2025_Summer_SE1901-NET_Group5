package vn.edu.fpt.BeautyCenter.repository;

/*
 * Copyright(C) 2025,  FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-07-21>      <1.0>              TrungBD      First Implement
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.BeautyCenter.entity.CommentLike;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, String> {

    // Check if user has liked a comment
    boolean existsByCommentCommentIdAndUserUserId(String commentId, String userId);

    // Find like by comment and user
    Optional<CommentLike> findByCommentCommentIdAndUserUserId(String commentId, String userId);

    // Count likes for a comment
    long countByCommentCommentId(String commentId);

    // Get all likes for a comment
    List<CommentLike> findByCommentCommentIdOrderByCreatedAtDesc(String commentId);

    // Get all likes by a user
    List<CommentLike> findByUserUserIdOrderByCreatedAtDesc(String userId);

    // Delete like by comment and user
    void deleteByCommentCommentIdAndUserUserId(String commentId, String userId);

    // Check which comments in a list are liked by a user
    @Query("SELECT cl.comment.commentId FROM CommentLike cl WHERE cl.comment.commentId IN :commentIds AND cl.user.userId = :userId")
    Set<String> findLikedCommentIds(@Param("commentIds") List<String> commentIds, @Param("userId") String userId);
}

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.BeautyCenter.entity.Comment;
import java.time.Instant;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String>, JpaSpecificationExecutor<Comment> {

    // Find comments by blog ID
    List<Comment> findByBlogBlogIdAndIsActiveTrueOrderByCreatedAtDesc(String blogId);
    Page<Comment> findByBlogBlogIdAndIsActiveTrueOrderByCreatedAtDesc(String blogId, Pageable pageable);

    // Find comments by author
    Page<Comment> findByAuthorUserIdAndIsActiveTrueOrderByCreatedAtDesc(String authorId, Pageable pageable);

    // Count comments by blog ID
    long countByBlogBlogIdAndIsActiveTrue(String blogId);

    // Count comments by author
    long countByAuthorUserIdAndIsActiveTrue(String authorId);

    // Get recent comments for a blog
    List<Comment> findTop10ByBlogBlogIdAndIsActiveTrueOrderByCreatedAtDesc(String blogId);

    // Find comments within date range
    @Query("SELECT c FROM Comment c WHERE c.blog.blogId = :blogId AND c.isActive = true AND " +
            "c.createdAt BETWEEN :startDate AND :endDate ORDER BY c.createdAt DESC")
    List<Comment> findByBlogIdAndDateRange(@Param("blogId") String blogId,
                                           @Param("startDate") Instant startDate,
                                           @Param("endDate") Instant endDate);

    // Update like count
    @Modifying
    @Query("UPDATE Comment c SET c.likeCount = c.likeCount + :increment WHERE c.commentId = :commentId")
    void updateLikeCount(@Param("commentId") String commentId, @Param("increment") int increment);

    // Soft delete comment
    @Modifying
    @Query("UPDATE Comment c SET c.isActive = false WHERE c.commentId = :commentId")
    void softDeleteComment(@Param("commentId") String commentId);

    // Get popular comments
    List<Comment> findTop2ByBlogBlogIdAndIsActiveTrueOrderByLikeCountDescCreatedAtDesc(String blogId);

    // Check if user has commented on a blog
    boolean existsByBlogBlogIdAndAuthorUserIdAndIsActiveTrue(String blogId, String userId);

    // Get all active comments with pagination
    Page<Comment> findByIsActiveTrueOrderByCreatedAtDesc(Pageable pageable);
}


package vn.edu.fpt.BeautyCenter.repository;

/*
 * Copyright(C) 2025,  FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-06-30>      <1.0>              TrungBD      First Implement
 */
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.BeautyCenter.entity.Blog;
import vn.edu.fpt.BeautyCenter.entity.enums.BlogStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, String>, JpaSpecificationExecutor<Blog> {

    // Find by status
    List<Blog> findByStatusOrderByPublishedAtDesc(BlogStatus status);
    Page<Blog> findByStatusOrderByPublishedAtDesc(BlogStatus status, Pageable pageable);

    // Find featured blogs
    List<Blog> findByFeaturedTrueAndStatusOrderByPublishedAtDesc(BlogStatus status);

    // Find by author
    Page<Blog> findByAuthorIdOrderByCreatedAtDesc(@Param("authorId")String authorId, Pageable pageable);
    List<Blog> findByAuthorIdAndStatus(@Param("authorId")String authorId, BlogStatus status);

    // Find by category
    Page<Blog> findByCategory_IdOrderByPublishedAtDesc(Integer categoryId, Pageable pageable);

    @Query(value = "SELECT * FROM blogs WHERE (LOWER(title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(CAST(content AS CHAR)) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND status = :status",
            countQuery = "SELECT count(*) FROM blogs WHERE (LOWER(title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                    "OR LOWER(CAST(content AS CHAR)) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
                    "AND status = :status",
            nativeQuery = true)
    Page<Blog> searchByKeywordAndStatus(@Param("keyword") String keyword,
                                        @Param("status") String status,
                                        Pageable pageable);


    // Find by tags
    @Query("SELECT DISTINCT b FROM Blog b JOIN b.blogTags t WHERE t.tagName IN :tagNames AND b.status = :status")
    Page<Blog> findByTagNamesAndStatus(@Param("tagNames") List<String> tagNames,
                                       @Param("status") BlogStatus status,
                                       Pageable pageable);

    // Statistics queries
    long countByStatus(BlogStatus status);
    long countByAuthorIdAndStatus(@Param("authorId")String authorId, BlogStatus status);

    // Recent blogs
    List<Blog> findTop5ByStatusOrderByPublishedAtDesc(BlogStatus status);

    // Popular blogs (by view count)
    List<Blog> findTop10ByStatusOrderByViewCountDescPublishedAtDesc(BlogStatus status);

    // Find blogs published within date range
    @Query("SELECT b FROM Blog b WHERE b.status = :status AND " +
            "b.publishedAt BETWEEN :startDate AND :endDate ORDER BY b.publishedAt DESC")
    List<Blog> findByStatusAndPublishedDateBetween(@Param("status") BlogStatus status,
                                                   @Param("startDate") LocalDateTime startDate,
                                                   @Param("endDate") LocalDateTime endDate);

    // Check if title exists
    boolean existsByTitleAndBlogIdNot(String title, String blogId);
    boolean existsByTitle(String title);

    // Update view count
    @Modifying
    @Query("UPDATE Blog b SET b.viewCount = b.viewCount + 1 WHERE b.blogId = :blogId")
    void incrementViewCount(@Param("blogId") String blogId);
}


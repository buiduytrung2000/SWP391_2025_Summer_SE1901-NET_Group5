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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.BeautyCenter.entity.BlogTag;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogTagRepository extends JpaRepository<BlogTag, Integer> {

    // Find by name and slug
    Optional<BlogTag> findByTagName(String tagName);
    Optional<BlogTag> findByTagSlug(String tagSlug);
    List<BlogTag> findByTagNameIn(List<String> tagNames);

    // Check existence
    boolean existsByTagName(String tagName);
    boolean existsByTagSlug(String tagSlug);

    // Find popular tags
    List<BlogTag> findTop10ByOrderByUsageCountDesc();

    // Find tags by usage count
    @Query("SELECT t FROM BlogTag t WHERE t.usageCount > 0 ORDER BY t.usageCount DESC")
    List<BlogTag> findUsedTagsOrderByUsageCount();

    // Update usage count
    @Modifying
    @Query("UPDATE BlogTag t SET t.usageCount = t.usageCount + 1 WHERE t.id = :tagId")
    void incrementUsageCount(@Param("tagId") Integer tagId);

    @Modifying
    @Query("UPDATE BlogTag t SET t.usageCount = t.usageCount - 1 WHERE t.id = :tagId AND t.usageCount > 0")
    void decrementUsageCount(@Param("tagId") Integer tagId);

    // Find all ordered by name
    List<BlogTag> findAllByOrderByTagNameAsc();
}

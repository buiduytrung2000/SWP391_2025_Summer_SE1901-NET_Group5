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
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.BeautyCenter.entity.BlogCategory;
import java.util.List;
import java.util.Optional;
@Repository
public interface BlogCategoryRepository extends JpaRepository<BlogCategory, Integer> {
    // Find by name and slug
    Optional<BlogCategory> findByCategoryName(String categoryName);
    Optional<BlogCategory> findByCategorySlug(String categorySlug);
    // Check existence
    boolean existsByCategoryName(String categoryName);
    boolean existsByCategorySlug(String categorySlug);
    boolean existsByCategoryNameAndIdNot(String categoryName, Integer categoryId);
    // Find categories with blog count
    @Query("SELECT c, COUNT(b) FROM BlogCategory c LEFT JOIN c.blogs b " +
            "WHERE b.status = vn.edu.fpt.BeautyCenter.entity.enums.BlogStatus.PUBLISHED " +
            "GROUP BY c ORDER BY COUNT(b) DESC")
    List<Object[]> findCategoriesWithBlogCount();
    // Find all ordered by name
    List<BlogCategory> findAllByOrderByCategoryNameAsc();
}
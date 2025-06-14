package vn.edu.fpt.BeautyCenter.repository;

/*
 * Copyright(C) 2025, FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE         Version  AUTHOR   DESCRIPTION
 * 2025-06-08   1.0      TrungBD  First Implement
 */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.BeautyCenter.entity.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for Service entity persistence operations.
 * <p>
 * Extends JpaRepository to provide CRUD operations and custom queries for Service entities.
 * </p>
 */
@Repository
public interface ServiceRepository extends JpaRepository<Service, String> {

    /**
     * Checks if a service with the given name already exists.
     *
     * @param name the name of the service to check
     * @return true if a service with the given name exists, false otherwise
     */
    boolean existsByName(String name);

    /**
     * Retrieves a paginated list of services whose names contain the specified keyword (case-insensitive).
     *
     * @param trim     the keyword to search for (will be matched anywhere in the name)
     * @param pageable pagination and sorting information
     * @return a page of matching Service entities
     */
    Page<Service> findByNameContainingIgnoreCase(String trim, Pageable pageable);

    /**
     * Advanced filtering method with comprehensive criteria support for service management.
     * <p>
     * This custom query method handles multiple filter criteria simultaneously, including
     * text search, numeric range filtering, date range filtering, and tag-based filtering.
     * The query uses LEFT JOIN for tag relationships to handle services without tags
     * gracefully and employs DISTINCT to prevent duplicate results from tag joins.
     * </p>
     *
     * @param keyword      basic keyword search across service name and content fields
     * @param minPrice     minimum price boundary for cost-based filtering
     * @param maxPrice     maximum price boundary for cost-based filtering
     * @param minDuration  minimum duration in minutes for time-based filtering
     * @param maxDuration  maximum duration in minutes for time-based filtering
     * @param fromDate     start date for creation date range filtering
     * @param toDate       end date for creation date range filtering
     * @param tags         list of tag names for categorical filtering
     * @param createdBy    user ID for author-based filtering
     * @param pageable     pagination and sorting configuration
     * @return page of services matching all specified criteria
     */
    @Query(value = "SELECT DISTINCT s.service_id, s.content, s.created_at, s.created_by, " +
            "s.duration, s.name, s.price, s.updated_at " +
            "FROM services s " +
            "LEFT JOIN service_tag_map stm ON s.service_id COLLATE utf8mb4_unicode_ci = stm.service_id COLLATE utf8mb4_unicode_ci " +
            "LEFT JOIN service_tags st ON st.tag_id = stm.tag_id " +
            "WHERE (:keyword IS NULL OR " +
            "       LOWER(s.name COLLATE utf8mb4_unicode_ci) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "       s.content COLLATE utf8mb4_unicode_ci LIKE CONCAT('%', :keyword, '%')) " +
            "AND (:minPrice IS NULL OR s.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR s.price <= :maxPrice) " +
            "AND (:minDuration IS NULL OR s.duration >= :minDuration) " +
            "AND (:maxDuration IS NULL OR s.duration <= :maxDuration) " +
            "AND (:fromDate IS NULL OR DATE(s.created_at) >= :fromDate) " +
            "AND (:toDate IS NULL OR DATE(s.created_at) <= :toDate) " +
            "AND (:createdBy IS NULL OR s.created_by COLLATE utf8mb4_unicode_ci = :createdBy) " +
            "AND (:#{#tags == null || #tags.isEmpty()} = true OR " +
            "     st.tag_name COLLATE utf8mb4_unicode_ci IN (:tags)) " +
            "ORDER BY s.created_at DESC",
            countQuery = "SELECT COUNT(DISTINCT s.service_id) " +
                    "FROM services s " +
                    "LEFT JOIN service_tag_map stm ON s.service_id COLLATE utf8mb4_unicode_ci = stm.service_id COLLATE utf8mb4_unicode_ci " +
                    "LEFT JOIN service_tags st ON st.tag_id = stm.tag_id " +
                    "WHERE (:keyword IS NULL OR " +
                    "       LOWER(s.name COLLATE utf8mb4_unicode_ci) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "       s.content COLLATE utf8mb4_unicode_ci LIKE CONCAT('%', :keyword, '%')) " +
                    "AND (:minPrice IS NULL OR s.price >= :minPrice) " +
                    "AND (:maxPrice IS NULL OR s.price <= :maxPrice) " +
                    "AND (:minDuration IS NULL OR s.duration >= :minDuration) " +
                    "AND (:maxDuration IS NULL OR s.duration <= :maxDuration) " +
                    "AND (:fromDate IS NULL OR DATE(s.created_at) >= :fromDate) " +
                    "AND (:toDate IS NULL OR DATE(s.created_at) <= :toDate) " +
                    "AND (:createdBy IS NULL OR s.created_by COLLATE utf8mb4_unicode_ci = :createdBy) " +
                    "AND (:#{#tags == null || #tags.isEmpty()} = true OR " +
                    "     st.tag_name COLLATE utf8mb4_unicode_ci IN (:tags))",
            nativeQuery = true)
    Page<Service> findWithAdvancedFilter(
            @Param("keyword") String keyword,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            @Param("minDuration") Integer minDuration,
            @Param("maxDuration") Integer maxDuration,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            @Param("tags") List<String> tags,
            @Param("createdBy") String createdBy,
            Pageable pageable
    );


}

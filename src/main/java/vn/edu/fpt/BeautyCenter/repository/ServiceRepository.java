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
import org.springframework.stereotype.Repository;
import vn.edu.fpt.BeautyCenter.entity.Service;

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

    // Example custom queries for future use:
    // @Query("SELECT s FROM Service s ORDER BY s.createdAt desc")
    // List<Service> findAllOrderByCreatedAtDesc();

    // @Query("SELECT s FROM Service s WHERE s.name LIKE %:keyword% OR s.content LIKE %:keyword%")
    // List<Service> findByKeyword(String keyword);
}

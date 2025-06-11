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

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.BeautyCenter.entity.ServiceTag;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for ServiceTag entity persistence operations.
 * <p>
 * Extends JpaRepository to provide CRUD operations and custom queries for ServiceTag entities.
 * </p>
 */
@Repository
public interface ServiceTagRepository extends JpaRepository<ServiceTag, Integer> {

    /**
     * Finds a service tag by its exact name.
     *
     * @param tagName the name of the tag to search for
     * @return an Optional containing the found ServiceTag, or empty if not found
     */
    Optional<ServiceTag> findByTagName(String tagName);

    /**
     * Retrieves all service tags whose names are in the provided list.
     *
     * @param tagNames list of tag names to search for
     * @return list of matching ServiceTag entities
     */
    List<ServiceTag> findByTagNameIn(List<String> tagNames);
}

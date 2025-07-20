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
import org.springframework.stereotype.Repository;
import vn.edu.fpt.BeautyCenter.entity.BlogImage;

import java.util.List;

@Repository
public interface BlogImageRepository extends JpaRepository<BlogImage, String> {

    // Find images by blog ID
    List<BlogImage> findByBlogBlogIdOrderByCreatedAtAsc(String blogId);

    // Find image by URL
    List<BlogImage> findByImageUrl(String imageUrl);

    // Delete images by blog ID
    void deleteByBlogBlogId(String blogId);

    // Count images by blog
    long countByBlogBlogId(String blogId);
}


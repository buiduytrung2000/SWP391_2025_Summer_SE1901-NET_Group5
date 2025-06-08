package vn.edu.fpt.BeautyCenter.repository;
/*
 * Copyright(C) 2025,  FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-06-8/6/2025>           <1.0>              TrungBD                      First Implement
 */
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.BeautyCenter.entity.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, String> {
//    @Query("SELECT s FROM Service s ORDER BY s.createdAt desc")
//    List<Service> findAllOrderByCreatedAtDesc();
//    @Query("SELECT s FROM Service s WHERE s.name LIKE %:keyword% OR s.content LIKE %:keyword%")
//    List<Service> findByKeyword(String keyword);
    boolean existsByName(String name);

    Page<Service> findByNameContainingIgnoreCase(String trim, Pageable pageable);
}

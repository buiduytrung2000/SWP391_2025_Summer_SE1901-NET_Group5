package vn.edu.fpt.BeautyCenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.fpt.BeautyCenter.entity.Staff;

import java.time.LocalDateTime;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, String> {
    Optional<Staff> findByUserId(String userId);
    Optional<Staff> findByEmail(String email);
    boolean existsByEmail(String email);
    @Query("SELECT s FROM Staff s WHERE " +
            "LOWER(s.fullName) LIKE %:keyword% OR " +
            "LOWER(s.email) LIKE %:keyword% OR " +
            "LOWER(s.phone) LIKE %:keyword%")
    Page<Staff> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
    long countByCreatedAtBetweenAndRole(LocalDateTime start, LocalDateTime end, String role);
}

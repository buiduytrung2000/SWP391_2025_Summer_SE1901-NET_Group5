package vn.edu.fpt.BeautyCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.fpt.BeautyCenter.entity.Staff;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, String> {
    Optional<Staff> findByUserId(String userId);
    Optional<Staff> findByEmail(String email);
    boolean existsByEmail(String email);
}

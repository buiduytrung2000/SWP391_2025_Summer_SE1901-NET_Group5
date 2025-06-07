package vn.edu.fpt.BeautyCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.fpt.BeautyCenter.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, String> {
}

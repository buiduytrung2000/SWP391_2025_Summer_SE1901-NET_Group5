package vn.edu.fpt.BeautyCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.fpt.BeautyCenter.entity.Users;

public interface UserRepository extends JpaRepository<Users, String> {
}

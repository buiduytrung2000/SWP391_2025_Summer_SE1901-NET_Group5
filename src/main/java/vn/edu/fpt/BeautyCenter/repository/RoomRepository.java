package vn.edu.fpt.BeautyCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.BeautyCenter.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
    // có thể thêm hàm custom sau, ví dụ findByStatus(...)
}

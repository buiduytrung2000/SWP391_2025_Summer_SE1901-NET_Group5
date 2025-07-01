package vn.edu.fpt.BeautyCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.BeautyCenter.entity.StaffSchedule;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StaffScheduleRepository extends JpaRepository<StaffSchedule, String> {
    List<StaffSchedule> findByStaffId(String staffId);
    List<StaffSchedule> findByStartTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}

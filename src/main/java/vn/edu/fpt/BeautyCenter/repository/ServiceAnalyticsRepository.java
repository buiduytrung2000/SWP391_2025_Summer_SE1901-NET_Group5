package vn.edu.fpt.BeautyCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.fpt.BeautyCenter.entity.StaffSchedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ServiceAnalyticsRepository extends JpaRepository<StaffSchedule, UUID> {

    @Query("SELECT s.name, COUNT(ss) FROM StaffSchedule ss " +
            "JOIN ss.service s " +
            "WHERE ss.startTime BETWEEN :start AND :end " +
            "GROUP BY s.name ORDER BY COUNT(ss) DESC")
    List<Object[]> countServiceUsageInWeek(LocalDateTime start, LocalDateTime end);

}

package vn.edu.fpt.BeautyCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.fpt.BeautyCenter.entity.Appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ServiceAnalyticsRepository extends JpaRepository<Appointment, UUID> {

    @Query("""
    SELECT s.name, s.price, COUNT(a), SUM(s.price)
    FROM Appointment a
    JOIN a.service s
    WHERE a.status = :status
      AND a.startTime BETWEEN :start AND :end
    GROUP BY s.name, s.price
    ORDER BY COUNT(a) DESC
""")
    List<Object[]> countCompletedServiceStatsInWeek(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("status") Appointment.Status status
    );

}

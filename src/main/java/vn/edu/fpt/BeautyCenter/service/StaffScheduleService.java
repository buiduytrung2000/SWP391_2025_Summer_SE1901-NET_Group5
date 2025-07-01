package vn.edu.fpt.BeautyCenter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.fpt.BeautyCenter.entity.StaffSchedule;
import vn.edu.fpt.BeautyCenter.repository.StaffScheduleRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffScheduleService {

    private final StaffScheduleRepository scheduleRepository;

    public StaffSchedule create(StaffSchedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<StaffSchedule> findByStaffId(String staffId) {
        return scheduleRepository.findByStaffId(staffId);
    }

    public List<StaffSchedule> findAll() {
        return scheduleRepository.findAll();
    }

    public List<StaffSchedule> findByDateRange(LocalDate startDate, LocalDate endDate) {
        return scheduleRepository.findByStartTimeBetween(
                startDate.atStartOfDay(),
                endDate.atTime(23, 59, 59)
        );
    }
    public List<StaffSchedule> findByDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        return scheduleRepository.findByStartTimeBetween(startOfDay, endOfDay);
    }
    public StaffSchedule findById(String id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id));
    }

    public StaffSchedule update(String id, StaffSchedule updatedSchedule) {
        StaffSchedule existing = findById(id);
        existing.setStartTime(updatedSchedule.getStartTime());
        existing.setEndTime(updatedSchedule.getEndTime());
        existing.setDescription(updatedSchedule.getDescription());
        existing.setStatus(updatedSchedule.getStatus());
        existing.setUpdatedAt(updatedSchedule.getUpdatedAt());
        return scheduleRepository.save(existing);
    }
    public boolean existsSchedule(String staffId, LocalDate date, String shift) {
        LocalDateTime start = shift.equals("MORNING") ? date.atTime(8, 0) : date.atTime(14, 0);
        LocalDateTime end = shift.equals("MORNING") ? date.atTime(14, 0) : date.atTime(20, 0);
        return !scheduleRepository.findByStaffIdAndStartTimeBetween(staffId, start, end).isEmpty();
    }
    public void delete(String id) {
        scheduleRepository.deleteById(id);
    }
}

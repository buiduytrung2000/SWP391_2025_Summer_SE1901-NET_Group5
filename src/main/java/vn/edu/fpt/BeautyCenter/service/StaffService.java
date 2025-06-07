package vn.edu.fpt.BeautyCenter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.fpt.BeautyCenter.dto.request.StaffCreationRequest;
import vn.edu.fpt.BeautyCenter.entity.Staff;
import vn.edu.fpt.BeautyCenter.repository.StaffRepository;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Staff getByUserId(String userId) {
        return staffRepository.findByUserId(userId).orElse(null);
    }

    public Staff save(Staff staff) {
        return staffRepository.save(staff);
    }

    public void deleteById(String id) {
        staffRepository.deleteById(id);
    }

    public void addStaff(StaffCreationRequest dto) {
        Staff staff = new Staff();

        staff.setUserId(UUID.randomUUID().toString());
        staff.setFullName(dto.getFullName());
        staff.setEmail(dto.getEmail());
        staff.setGender(dto.getGender());
        staff.setPosition(dto.getPosition());
        staff.setPhone(dto.getPhone());
        staff.setRole(dto.getRole());
        staff.setUsername(dto.getEmail());
        staff.setPassword("default_password");

        if (dto.getStartDate() != null) {
            staff.setCreatedAt(dto.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        } else {
            staff.setCreatedAt(Instant.now());
        }

        staff.setAvatarUrl(null);

        staffRepository.save(staff);
    }

    public void updateStaffFromModal(Staff updatedStaff) {
        Staff existing = getByUserId(updatedStaff.getUserId());
        if (existing != null) {
            existing.setFullName(updatedStaff.getFullName());
            existing.setEmail(updatedStaff.getEmail());
            existing.setPhone(updatedStaff.getPhone());
            existing.setRole(updatedStaff.getRole());
            existing.setGender(updatedStaff.getGender());
            existing.setPosition(updatedStaff.getPosition());
            existing.setCreatedAt(updatedStaff.getCreatedAt());

            staffRepository.save(existing);
        }
    }
}

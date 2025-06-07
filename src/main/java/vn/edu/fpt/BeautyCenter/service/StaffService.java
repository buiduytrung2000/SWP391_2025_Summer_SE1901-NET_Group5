package vn.edu.fpt.BeautyCenter.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.fpt.BeautyCenter.dto.request.StaffCreationRequest;
import vn.edu.fpt.BeautyCenter.entity.Staff;
import vn.edu.fpt.BeautyCenter.repository.StaffRepository;
import java.time.Instant;
import java.util.UUID;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Staff getByUserId(String id) {
        return staffRepository.findById(id).orElse(null);
    }

    public Staff save(Staff staff) {
        return staffRepository.save(staff);
    }

    public void deleteById(String id) {
        staffRepository.deleteById(id);
    }
    public void addStaff(StaffCreationRequest dto) {
        Staff staff = new Staff();

        staff.setUserId(UUID.randomUUID().toString()); // đảm bảo có ID
        staff.setFullName(dto.getFullName());
        staff.setEmail(dto.getEmail());
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
    public void updateStaff(String id, StaffCreationRequest dto) {
        Staff staff = getByUserId(id);
        if (staff == null) return;

        staff.setFullName(dto.getFullName());
        staff.setEmail(dto.getEmail());
        staff.setPhone(dto.getPhone());
        staff.setRole(dto.getRole());

        if (dto.getStartDate() != null) {
            staff.setCreatedAt(dto.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        }

        staffRepository.save(staff);
    }


}

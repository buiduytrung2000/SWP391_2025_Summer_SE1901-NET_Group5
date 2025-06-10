package vn.edu.fpt.BeautyCenter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.fpt.BeautyCenter.dto.request.StaffCreationRequest;
import vn.edu.fpt.BeautyCenter.entity.Staff;
import vn.edu.fpt.BeautyCenter.repository.StaffRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
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
        try {
            if (staffRepository.findByEmail(dto.getEmail()).isPresent()) {
                throw new RuntimeException("Email đã tồn tại: " + dto.getEmail());
            }

            Staff staff = new Staff();
            staff.setUserId(UUID.randomUUID().toString());
            staff.setFullName(dto.getFullName());
            staff.setEmail(dto.getEmail());
            try {
                staff.setGender(Staff.Gender.valueOf(dto.getGender()));
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Giới tính không hợp lệ. Chỉ chấp nhận: Male, Female, Other.");
            }
            staff.setPosition(dto.getPosition());
            staff.setPhone(dto.getPhone());
            staff.setRole(dto.getRole() != null ? dto.getRole() : "Staff");
            staff.setStatus(Staff.Status.active);
            staff.setUsername(dto.getEmail());
            staff.setPassword("123456");

            if (dto.getStartDate() != null) {
                staff.setCreatedAt(dto.getStartDate().atStartOfDay());
            }

            MultipartFile avatar = dto.getAvatar();
            if (avatar != null && !avatar.isEmpty()) {
                String fileName = UUID.randomUUID() + "_" + avatar.getOriginalFilename();
                String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/uploads";
                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(fileName);
                avatar.transferTo(filePath.toFile());
                staff.setAvatarUrl(fileName);
            }

            staffRepository.save(staff);

        } catch (Exception e) {
            System.err.println("Lỗi khi tạo staff:");
            e.printStackTrace();
            throw new RuntimeException("Không thể tạo staff: " + e.getMessage());
        }
    }

    public void updateStaffFromModal(Staff updatedStaff) {
        Staff existing = getByUserId(updatedStaff.getUserId());
        if (existing != null) {
            existing.setFullName(updatedStaff.getFullName());
            existing.setEmail(updatedStaff.getEmail());
            existing.setPhone(updatedStaff.getPhone());
//            existing.setRole(updatedStaff.getRole());
            existing.setGender(updatedStaff.getGender());
            existing.setPosition(updatedStaff.getPosition());
            existing.setCreatedAt(updatedStaff.getCreatedAt());


            staffRepository.save(existing);
        }
    }
}

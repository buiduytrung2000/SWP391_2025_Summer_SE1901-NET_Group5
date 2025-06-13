package vn.edu.fpt.BeautyCenter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.fpt.BeautyCenter.dto.request.StaffCreationRequest;
import vn.edu.fpt.BeautyCenter.dto.request.StaffUpdateRequest;
import vn.edu.fpt.BeautyCenter.entity.Staff;
import vn.edu.fpt.BeautyCenter.exception.AppException;
import vn.edu.fpt.BeautyCenter.exception.ErrorCode;
import vn.edu.fpt.BeautyCenter.repository.StaffRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;

    public Page<Staff> getStaffPage(Pageable pageable) {
        return staffRepository.findAll(pageable);
    }
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }
    public Staff getByUserId(String userId) {
        return staffRepository.findByUserId(userId).orElse(null);
    }

    public Staff save(Staff staff) {
        return staffRepository.save(staff);
    }

    public void addStaff(StaffCreationRequest dto) {
        validateStaff(dto, false, null);  // validate thêm mới

        try {
            Staff staff = new Staff();
            staff.setUserId(UUID.randomUUID().toString());
            staff.setFullName(dto.getFullName().trim());
            staff.setEmail(dto.getEmail().trim());
            staff.setPhone(dto.getPhone().trim());
            staff.setPosition(dto.getPosition());
            staff.setRole(dto.getRole() != null ? dto.getRole() : "Staff");
            staff.setStatus(Staff.Status.active);
            staff.setUsername(dto.getEmail().trim());
            staff.setPassword("123456");
            staff.setGender(Staff.Gender.valueOf(dto.getGender()));


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
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {

            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    public void updateStaffFromDto(StaffUpdateRequest dto) {
        Staff existing = getByUserId(dto.getUserId());
        if (existing == null) {
            throw new AppException(ErrorCode.STAFF_NOT_FOUND);
        }

        String newEmail = dto.getEmail() != null ? dto.getEmail().trim() : null;
        if (!existing.getEmail().equals(newEmail) && staffRepository.existsByEmail(newEmail)) {
            throw new AppException(ErrorCode.STAFF_EMAIL_EXISTED);
        }

        existing.setFullName(dto.getFullName().trim());
        existing.setEmail(newEmail);
        existing.setPhone(dto.getPhone().trim());
        existing.setPosition(dto.getPosition().trim());
        existing.setGender(Staff.Gender.valueOf(dto.getGender()));
        if (dto.getStartDate() != null) {
            existing.setCreatedAt(dto.getStartDate().atStartOfDay());
        }

        staffRepository.save(existing);
    }




    public void validateStaff(StaffCreationRequest request, boolean isEdit, String currentEmail) {
        String email = request.getEmail() != null ? request.getEmail().trim() : null;

        if (staffRepository.existsByEmail(email)) {
            if (!isEdit || (currentEmail != null && !email.equals(currentEmail))) {
                throw new AppException(ErrorCode.STAFF_EMAIL_EXISTED);
            }
        }

    }


}

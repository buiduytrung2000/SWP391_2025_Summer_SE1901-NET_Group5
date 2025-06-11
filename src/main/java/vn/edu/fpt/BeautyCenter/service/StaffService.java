package vn.edu.fpt.BeautyCenter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.fpt.BeautyCenter.dto.request.StaffCreationRequest;
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

    public void deleteById(String id) {
        staffRepository.deleteById(id);
    }

    public void addStaff(StaffCreationRequest dto) {
        validateStaff(dto, false, null);  // validate ở chế độ thêm mới

        try {
            Staff staff = new Staff();
            staff.setUserId(UUID.randomUUID().toString());
            staff.setFullName(dto.getFullName());
            staff.setEmail(dto.getEmail());
            staff.setPhone(dto.getPhone());
            staff.setPosition(dto.getPosition());
            staff.setRole(dto.getRole() != null ? dto.getRole() : "Staff");
            staff.setStatus(Staff.Status.active);
            staff.setUsername(dto.getEmail());
            staff.setPassword("123456");

            try {
                staff.setGender(Staff.Gender.valueOf(dto.getGender()));
            } catch (IllegalArgumentException e) {
                throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
            }

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

    public void updateStaffFromModal(Staff updatedStaff) {
        Staff existing = getByUserId(updatedStaff.getUserId());
        if (existing != null) {
            StaffCreationRequest fakeDto = new StaffCreationRequest();
            fakeDto.setEmail(updatedStaff.getEmail());
            fakeDto.setPhone(updatedStaff.getPhone());
            fakeDto.setGender(updatedStaff.getGender() != null ? updatedStaff.getGender().name() : null);

            validateStaff(fakeDto, true, existing.getEmail());

            existing.setFullName(updatedStaff.getFullName());
            existing.setEmail(updatedStaff.getEmail());
            existing.setPhone(updatedStaff.getPhone());
            existing.setGender(updatedStaff.getGender());
            existing.setPosition(updatedStaff.getPosition());
            existing.setCreatedAt(updatedStaff.getCreatedAt());

            staffRepository.save(existing);
        }
    }

    public void validateStaff(StaffCreationRequest request, boolean isEdit, String currentEmail) {
        if (!isValidEmail(request.getEmail())) {
            throw new AppException(ErrorCode.STAFF_EMAIL_INVALID);
        }

        if (!isValidPhone(request.getPhone())) {
            throw new AppException(ErrorCode.STAFF_PHONE_INVALID);
        }

        if (staffRepository.existsByEmail(request.getEmail())) {
            if (!isEdit || (currentEmail != null && !request.getEmail().equals(currentEmail))) {
                throw new AppException(ErrorCode.STAFF_EMAIL_EXISTED);
            }
        }
        if (request.getGender() == null || request.getGender().isBlank()) {
            throw new AppException(ErrorCode.STAFF_GENDER_REQUIRED);
        }
    }

    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.com$";
        return email != null && email.matches(regex);
    }

    private boolean isValidPhone(String phone) {
        return phone != null && phone.matches("^0\\d{9}$");
    }

}

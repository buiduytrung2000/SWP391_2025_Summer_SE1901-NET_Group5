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

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;

    public List<Staff> findAll() {
        return staffRepository.findAll();
    }
    /**
     * Get paginated list of staff
     */
    public Page<Staff> getStaffPage(Pageable pageable) {
        return staffRepository.findAll(pageable);
    }

    /**
     * Get staff by userId
     */
    public Staff getByUserId(String userId) {
        return staffRepository.findByUserId(userId).orElse(null);
    }

    public Page<Staff> searchStaff(String keyword, Pageable pageable) {
        return staffRepository.searchByKeyword(keyword.toLowerCase(), pageable);
    }

    public void deleteById(String id) {
        staffRepository.deleteById(id);
    }
    /**
     * Save or update a staff record
     */
    public Staff save(Staff staff) {
        return staffRepository.save(staff);
    }

    /**
     * Add a new staff member
     */
    public void addStaff(StaffCreationRequest dto) {
        validateStaff(dto, false, null); // Validate before saving

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

            // Set start date (createdAt)
            if (dto.getStartDate() != null) {
                staff.setCreatedAt(dto.getStartDate().atStartOfDay());
            }

            // Handle avatar image upload
            MultipartFile avatar = dto.getAvatar();
            if (avatar != null && !avatar.isEmpty()) {
                String fileName = saveAvatar(avatar);
                staff.setAvatarUrl(fileName); // Save only the file name
            }

            staffRepository.save(staff);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    /**
     * Update staff profile from DTO
     */
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

    /**
     * Validate if email is duplicated before creation or update
     */
    public void validateStaff(StaffCreationRequest request, boolean isEdit, String currentEmail) {
        String email = request.getEmail() != null ? request.getEmail().trim() : null;

        if (staffRepository.existsByEmail(email)) {
            if (!isEdit || (currentEmail != null && !email.equals(currentEmail))) {
                throw new AppException(ErrorCode.STAFF_EMAIL_EXISTED);
            }
        }
    }

    /**
     * Save avatar image to static directory and return the saved file name
     */
    private String saveAvatar(MultipartFile avatar) throws IOException {
        // Generate unique file name
        String fileName = UUID.randomUUID() + "_" + avatar.getOriginalFilename();

        // Define the upload path (resources/static/uploads)
        String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/uploads";
        Path uploadPath = Paths.get(uploadDir);

        // Create directory if it doesn't exist
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Save the file to disk
        Path filePath = uploadPath.resolve(fileName);
        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            fos.write(avatar.getBytes());
            fos.flush(); // Ensure all data is written immediately
        }

        return fileName;
    }
}

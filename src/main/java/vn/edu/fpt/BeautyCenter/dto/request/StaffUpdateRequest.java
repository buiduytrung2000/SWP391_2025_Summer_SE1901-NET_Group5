package vn.edu.fpt.BeautyCenter.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class StaffUpdateRequest {

    /**
     * Unique identifier for the staff member (UUID).
     * Required to identify the record to update.
     */
    @NotBlank(message = "User ID is required")
    private String userId;

    /**
     * Full name of the staff.
     * Must contain only letters (including accented characters) and spaces.
     */
    @NotBlank(message = "Full name is required")
    @Size(max = 100, message = "Full name must not exceed 100 characters")
    @Pattern(
            regexp = "^[A-Za-zÀ-ỹà-ỹ\\s]+$",
            message = "Full name must contain only letters and spaces"
    )
    private String fullName;

    /**
     * Email address of the staff.
     * Must be valid and end with .com.
     */
    @NotBlank(message = "Email is required")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.com$",
            message = "Email must be valid and end with .com"
    )
    private String email;

    /**
     * Phone number of the staff.
     * Must start with 0 and contain exactly 10 digits.
     */
    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^0\\d{9}$",
            message = "Phone number must start with 0 and contain 10 digits"
    )
    private String phone;

    /**
     * Gender of the staff.
     * Must be one of: Male, Female, or Other.
     */
    @NotBlank(message = "Gender is required")
    @Pattern(
            regexp = "Male|Female|Other",
            message = "Gender must be Male, Female or Other"
    )
    private String gender;

    /**
     * Job title or position of the staff.
     */
    @NotBlank(message = "Position is required")
    @Size(max = 100, message = "Position must not exceed 100 characters")
    private String position;

    /**
     * Start date of employment (optional).
     * Input format must be yyyy-MM-dd.
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    // If needed in the future:
    // private MultipartFile avatar;
}

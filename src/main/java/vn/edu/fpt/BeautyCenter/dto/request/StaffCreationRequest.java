package vn.edu.fpt.BeautyCenter.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class StaffCreationRequest {

    /**
     * Full name of the staff.
     * Must only contain letters (including Vietnamese accented letters) and spaces.
     */
    @NotBlank(message = "Full name is required")
    @Size(max = 20, message = "Full name must not exceed 20 characters")
    @Pattern(
            regexp = "^[A-Za-zÀ-ỹà-ỹ\\s]+$",
            message = "Full name must contain only letters and spaces"
    )
    private String fullName;

    /**
     * Email address used for login and contact.
     * Must follow valid email format and end with .com.
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
     * Must start with 0 and have exactly 10 digits.
     */
    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^0\\d{9}$",
            message = "Phone number must start with 0 and contain 10 digits"
    )
    private String phone;

    /**
     * Gender must be one of: Male, Female, or Other.
     */
    @NotBlank(message = "Gender is required")
    @Pattern(
            regexp = "Male|Female|Other",
            message = "Gender must be Male, Female or Other"
    )
    private String gender;

    /**
     * Optional role of the user (e.g., Staff, Admin).
     */
    @Size(max = 50, message = "Role must not exceed 50 characters")
    private String role;

    /**
     * Job title or position of the staff.
     */
    @NotBlank(message = "Position is required")
    @Size(max = 100, message = "Position must not exceed 100 characters")
    private String position;

    /**
     * Password (optional, usually auto-generated or fixed for creation).
     */
    private String password;

    /**
     * Username for system login (usually equals email).
     */
    private String username;

    /**
     * Start date of employment or profile creation.
     * Expected in yyyy-MM-dd format from form input.
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    /**
     * Avatar image uploaded by user (optional).
     */
    private MultipartFile avatar;

}

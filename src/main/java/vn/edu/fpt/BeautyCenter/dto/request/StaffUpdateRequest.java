package vn.edu.fpt.BeautyCenter.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class StaffUpdateRequest {

    @NotBlank(message = "User ID is required")
    private String userId;

    // Only allow letters (uppercase/lowercase) and spaces, disallow numbers/special characters
    @NotBlank(message = "Full name is required")
    @Size(max = 100, message = "Full name must not exceed 100 characters")
    @Pattern(
            regexp = "^[A-Za-zÀ-ỹà-ỹ\\s]+$",
            message = "Full name must contain only letters and spaces"
    )
    private String fullName;

    @NotBlank(message = "Email is required")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.com$",
            message = "Email must be valid and end with .com"
    )
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^0\\d{9}$", message = "Phone number must start with 0 and contain 10 digits")
    private String phone;

    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "Male|Female|Other", message = "Gender must be Male, Female or Other")
    private String gender;

    @NotBlank(message = "Position is required")
    @Size(max = 100, message = "Position must not exceed 100 characters")
    private String position;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

//    private MultipartFile avatar;
}

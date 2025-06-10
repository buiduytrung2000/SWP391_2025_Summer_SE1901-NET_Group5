package vn.edu.fpt.BeautyCenter.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class StaffCreationRequest {
    private String fullName;
    private String email;
    private String phone;
    private String gender;
    private String role;
    private String position;
    private String password;
    private String username;
    private LocalDate startDate;
    private MultipartFile avatar;
}

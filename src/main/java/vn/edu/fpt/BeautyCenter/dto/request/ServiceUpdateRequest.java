package vn.edu.fpt.BeautyCenter.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ServiceUpdateRequest {
    @NotBlank(message = "Service name can not be blank")
    @Size(max = 100, message = "Service name must no exceed 100 characters")
    String name;
    @NotBlank(message = "Service content is required")
    private String content;
    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1 minute")
    @Max(value = 480, message = "Duration must not exceed 480 minutes")
    private Integer duration;
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @DecimalMax(value = "9999.99", message = "Price must not exceed 9999.99")
    private BigDecimal price;
    List<String> tagNames;
}

package vn.edu.fpt.BeautyCenter.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ServiceResponse {
    String serviceId;
    String name;
    String content;
    String duration;
    Double price;
    private List<String> tags;
//    private String createdBy;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;

}


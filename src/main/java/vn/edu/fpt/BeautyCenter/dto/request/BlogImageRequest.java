package vn.edu.fpt.BeautyCenter.dto.request;

/*
 * Copyright(C) 2025,  FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-06-30>      <1.0>              TrungBD      First Implement
 */

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BlogImageRequest {

    @NotBlank(message = "Image URL is required")
    @Size(max = 500, message = "Image URL must not exceed 500 characters")
    private String imageUrl;

    @Size(max = 255, message = "Image name must not exceed 255 characters")
    private String imageName;

    private Integer imageSize;

    @Size(max = 255, message = "Alt text must not exceed 255 characters")
    private String altText;
}

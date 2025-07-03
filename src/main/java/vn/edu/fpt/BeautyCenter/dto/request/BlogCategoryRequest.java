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
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BlogCategoryRequest {

    @NotBlank(message = "Category name is required")
    @Size(max = 100, message = "Category name must not exceed 100 characters")
    private String categoryName;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @Pattern(regexp = "^#([A-Fa-f0-9]{6})$", message = "Color code must be a valid hex color")
    private String colorCode = "#EA9999";
}


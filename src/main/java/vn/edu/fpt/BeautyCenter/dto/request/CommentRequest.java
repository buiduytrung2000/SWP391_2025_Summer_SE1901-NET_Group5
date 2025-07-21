package vn.edu.fpt.BeautyCenter.dto.request;

/*
 * Copyright(C) 2025,  FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-07-21>      <1.0>              TrungBD      First Implement
 */

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {

    @NotBlank(message = "Content is required")
    @Size(max = 1000, message = "Comment must not exceed 1000 characters")
    private String content;

    @NotNull(message = "Blog ID is required")
    private String blogId;

    public void setContent(String content) {
        if (content != null) {
            this.content = content.trim()
                    .replaceAll("<[^>]*>", "") // Remove HTML tags
                    .replaceAll("\\s+", " "); // Replace multiple spaces with single space
        } else {
            this.content = null;
        }
    }
}


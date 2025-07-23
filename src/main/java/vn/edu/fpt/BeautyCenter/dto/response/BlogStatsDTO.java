package vn.edu.fpt.BeautyCenter.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BlogStatsDTO {
    private String title;
    private String url;
    private String authorName;
    private Integer viewCount;
}

package personal.project.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UploadDto {

    private Integer category;
    private String content;
    private String title;
    private String linkTitle;
    private String linkUrl;
    private String linkImage;
    private String image;
}

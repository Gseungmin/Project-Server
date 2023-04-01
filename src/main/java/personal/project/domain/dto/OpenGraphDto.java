package personal.project.domain.dto;

import lombok.Getter;
import lombok.Setter;
import personal.project.domain.entity.Project;

@Getter @Setter
public class OpenGraphDto {

    private String url;
    private String title;
    private String image;

    public OpenGraphDto(Project project) {
        this.url = project.getLinkUrl();
        this.title = project.getLinkTitle();
        this.image = project.getLinkImage();
    }
}

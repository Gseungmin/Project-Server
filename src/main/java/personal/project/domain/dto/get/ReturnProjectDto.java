package personal.project.domain.dto.get;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import personal.project.domain.entity.Project;

import java.util.List;

@Getter @Setter
public class ReturnProjectDto {

    private Long projectId;
    private String content;
    private String title;
    private String thumbnailImage;
    private Integer state;
    private Integer likeCount;
    private Integer commentCount;
    private Integer view;

    @QueryProjection
    public ReturnProjectDto(Project project) {
        this.projectId = project.getId();
        this.content = project.getContent();
        this.title = project.getTitle();
        this.thumbnailImage = project.getImage();
        this.state = project.getCategory();
        this.likeCount = project.getLikeCount();
        this.commentCount = project.getCommentCount();
        this.view = project.getViewCount();
    }
}

package personal.project.domain.dto.get;

import lombok.Getter;
import lombok.Setter;
import personal.project.domain.dto.OpenGraphDto;
import personal.project.domain.entity.Project;

@Getter @Setter
public class ReturnProjectDetailDto {

    private Long projectId;
    private String content;
    private String title;
    private String nickname;
    private String image;
    private String profileImage;
    private Integer state;
    private Integer likeCount;
    private Integer commentCount;
    private Integer view;
    private Boolean isLiked;
    private Boolean isMy;
    private OpenGraphDto link;

    public ReturnProjectDetailDto(Project project) {
        this.projectId = project.getId();
        this.content = project.getContent();
        this.title = project.getTitle();
        this.image = project.getImage();
        this.profileImage = project.getMember().getProfileImage();
        this.state = project.getCategory();
        this.likeCount = project.getLikeCount();
        this.commentCount = project.getCommentCount();
        this.view = project.getViewCount();
        this.link = new OpenGraphDto(project);
    }
}

package personal.project.domain.dto.post;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentDto {

    private Long projectId;
    private String content;
}

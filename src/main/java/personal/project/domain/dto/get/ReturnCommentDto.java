package personal.project.domain.dto.get;

import lombok.Getter;
import lombok.Setter;
import personal.project.domain.entity.Comment;

@Getter @Setter
public class ReturnCommentDto {

    private Long commentId;
    private String profileImage;
    private String nickname;
    private String content;

    public ReturnCommentDto(Comment comment) {
        this.commentId = comment.getId();
        this.profileImage = comment.getMember().getProfileImage();
        this.nickname = comment.getMember().getNickname();
        this.content = comment.getContent();
    }
}

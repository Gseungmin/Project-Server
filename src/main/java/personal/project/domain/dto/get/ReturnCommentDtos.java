package personal.project.domain.dto.get;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ReturnCommentDtos {

    private List<ReturnCommentDto> content;

    public ReturnCommentDtos(List<ReturnCommentDto> content) {
        this.content = content;
    }
}

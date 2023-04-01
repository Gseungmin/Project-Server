package personal.project.domain.dto.get;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ReturnProjectDtos {

    private List<ReturnProjectDto> content;

    public ReturnProjectDtos(List<ReturnProjectDto> content) {
        this.content = content;
    }
}

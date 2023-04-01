package personal.project.domain.dto.get;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReturnLikeDto {

    private Boolean isLike;

    public ReturnLikeDto(Boolean isLike) {
        this.isLike = isLike;
    }
}

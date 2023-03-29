package personal.project.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReturnJoinDto {

    private Boolean isTrue;
    private String email;

    public ReturnJoinDto(Boolean isTrue, String email) {
        this.isTrue = isTrue;
        this.email = email;
    }
}

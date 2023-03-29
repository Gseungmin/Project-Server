package personal.project.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReturnMemberJoin {

    private Boolean isTrue;
    private String email;
    private String accessToken;

    public ReturnMemberJoin(Boolean isTrue, String email) {
        this.isTrue = isTrue;
        this.email = email;
    }

    public ReturnMemberJoin(Boolean isTrue, String email, String accessToken) {
        this.isTrue = isTrue;
        this.email = email;
        this.accessToken = accessToken;
    }
}

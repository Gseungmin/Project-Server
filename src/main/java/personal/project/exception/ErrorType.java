package personal.project.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ErrorType {

    // Token 관련 에러, 1로 시작
    TOKEN_NOT_EXIST(BAD_REQUEST, 10001, "JWT Token이 존재하지 않습니다."),
    TOKEN_INVALID(BAD_REQUEST, 10002, "유효하지 않은 JWT Token 입니다."),

    // User 관련 에러, 2로 시작
    MEMBER_EXIST(UNAUTHORIZED, 20001, "이미 존재하는 이메일입니다."),
    LOGIN_FAILED(UNAUTHORIZED, 20003, "아이디 또는 비밀번호가 일치하지 않습니다.");

    private final HttpStatus httpStatus;
    private final int code;
    private final String errorMessage;
}
package personal.project.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

    private ErrorType errorType;
    private int code;
    private String errorMessage;
}

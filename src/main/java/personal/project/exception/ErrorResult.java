package personal.project.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorResult {
    private String code;
    private String message;

    public ErrorResult(CustomException customException) {
        this.code = String.valueOf(customException.getCode());
        this.message = customException.getErrorMessage();
    }
}
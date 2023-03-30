package personal.project.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    // CustomException 처리
    @ExceptionHandler(CustomException.class)
    public ErrorResult customExceptionHandle(CustomException e, HttpServletRequest request) {
        log.error("[CustomException] url: {} | errorType: {} | errorMessage: {} | cause Exception: ",
                request.getRequestURL(), e.getErrorType(), e.getMessage(), e.getCause());

        return new ErrorResult(e);
    }
}

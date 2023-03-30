package personal.project.web.filter.entrypoint;

import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import personal.project.exception.ErrorResult;
import personal.project.exception.ErrorType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        ErrorType exception = (ErrorType) request.getAttribute("exception");
        System.out.println("예외가 발생했습니다. : " + exception);
        setResponse(response, exception);
    }

    private void setResponse(HttpServletResponse response, ErrorType errorType) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        JSONObject responseJson = new JSONObject();
        responseJson.put("message", errorType.getErrorMessage());
        responseJson.put("code", errorType.getCode());
        response.getWriter().print(responseJson);
    }
}

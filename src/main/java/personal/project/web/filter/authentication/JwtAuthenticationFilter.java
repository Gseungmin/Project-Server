package personal.project.web.filter.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.util.Utils;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWK;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import personal.project.web.signature.SecuritySigner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private HttpSecurity httpSecurity;
    private SecuritySigner securitySigner;
    private JWK jwk;

    /**알고리즘과 SINGER를 추상적으로 받아 처리*/
    public JwtAuthenticationFilter(HttpSecurity httpSecurity, SecuritySigner securitySigner, JWK jwk) {
        this.httpSecurity = httpSecurity;
        this.securitySigner = securitySigner;
        this.jwk = jwk;
    }

    /**인증 시작*/
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        System.out.println("인증 시작");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        AuthenticationManager authenticationManager = httpSecurity.getSharedObject(AuthenticationManager.class);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        return authentication;
    }

    /**인증에 성공시 즉 객체 조회 성공시 진행되는 로직, 토큰을 발행하는 코드 작성*/
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws ServletException, IOException {
        User user = (User) authResult.getPrincipal();
        String jwtToken;
        try {
            jwtToken = securitySigner.getJwtToken(user, jwk); //securitySigner를 통해 jwk 토큰을 받아옴
            response.addHeader("Authorization", "Bearer " + jwtToken); //발행받은 토큰을 response 헤더에 담아 응답
        } catch (JOSEException e) {
            e.printStackTrace();
        }
    }
}

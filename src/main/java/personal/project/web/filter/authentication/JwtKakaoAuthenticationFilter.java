package personal.project.web.filter.authentication;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWK;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import personal.project.domain.entity.Member;
import personal.project.domain.entity.MemberType;
import personal.project.exception.ErrorType;
import personal.project.exception.LoginException;
import personal.project.web.service.MemberService;
import personal.project.web.signature.SecuritySigner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

import static personal.project.domain.entity.MemberType.BASIC;
import static personal.project.domain.entity.MemberType.KAKAO;
import static personal.project.exception.ErrorType.BASIC_MEMBER_EXIST;

@RequiredArgsConstructor
public class JwtKakaoAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final HttpSecurity httpSecurity;
    private final SecuritySigner securitySigner;
    private final JWK jwk;
    private final MemberService memberService;

    /**인증 시작*/
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        String reqURL = "https://kapi.kakao.com/v2/user/me";
        String email = "";
        String password = "KAKAO_LOGIN";

        String accessToken = request.getHeader("Authorization");
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            JsonElement element = JsonParser.parseString(result);
            String id = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
            email = id;
            br.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        Optional<Member> findMember = memberService.findMember(email);
        if (findMember.isPresent()) { //멤버가 존재할 경우
            if (findMember.get().getType().equals(BASIC.getType())) { //베이직으로 로그인한 경우
                request.setAttribute("exception", BASIC_MEMBER_EXIST);
                throw new LoginException(BASIC_MEMBER_EXIST,
                        BASIC_MEMBER_EXIST.getCode(), BASIC_MEMBER_EXIST.getErrorMessage());
            }
        } else {
            Member member = new Member(email, password, KAKAO.getType());
            member.getRoles().add("USER");
            memberService.join(member);
        }

        AuthenticationManager authenticationManager = httpSecurity.getSharedObject(AuthenticationManager.class);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        return authentication;
    }

    //인증에 성공시 즉 객체 조회 성공시 진행되는 로직, 토큰을 발행하는 코드 작성
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

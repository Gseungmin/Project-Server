package personal.project.domain.dto;

import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import personal.project.web.filter.authentication.JwtAuthenticationFilter;
import personal.project.web.filter.authorization.JwtAuthorizationRsaFilter;
import personal.project.web.service.CustomUserDetailsService;
import personal.project.web.signature.RSASecuritySigner;

@EnableWebSecurity
public class OAuth2ResourceServer {

    @Autowired
    private RSASecuritySigner rsaSecuritySigner;

    @Autowired
    private RSAKey rsaKey;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //세션을 사용하지 않음

        http.authorizeRequests((requests) -> requests.antMatchers(
                "/login",
                "/auth/join").permitAll().anyRequest().authenticated());

        /**
         * 모든 과정을 시큐리티에 맡김
         * jwtDecoder가 생성되어 인가서버로부터 jwtSet을 가지고 옴
         * jwtSet안에서 토큰을 검증할 수 있는 Public 키를 가지고 옴
         * 그리고 선택된 Public 키를 가지고 검증
         */
        //사용자 정보 로드해서 객체 생성
        http.userDetailsService(userDetailsService);

        //인증 필터
        http.addFilterBefore(new JwtAuthenticationFilter(http, rsaSecuritySigner, rsaKey), UsernamePasswordAuthenticationFilter.class);
        //토큰 검증 필터
        http.addFilterBefore(new JwtAuthorizationRsaFilter(rsaKey), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    //패스워드 인코드 하지 않음
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}

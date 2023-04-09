package jinny.springboot.springkiwi.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final JwtTokenProvider jwtTokenProvider;

	/**
	 * HttpSecurity  앞 단에 적요되며, 인증과 인가가 모두 적용되기 전에 동작함.
	 * 인증, 인가를 무시하는 경로 설정에 사용
	 * @param webSecurity
	 * @throws Exception
	 */
	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
		webSecurity.ignoring().antMatchers("/v2/api-docs", "swagger-resources/**", "/swagger-ui.html", "webjars/**", "/swagger/**", "/sign-api/exception");
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.httpBasic().disable()
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers("sign-api/sign-in", "sign-api/sign-up", "sign-api/exception").permitAll()
				.antMatchers(HttpMethod.GET, "/product/**").permitAll()
				.antMatchers("**exception**").permitAll()
				.anyRequest().hasRole("ADMIN")
				.and()
				.exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())		 // 권한 확인 과정에서의 예외 처리
				.and()
				.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())  // 인증 과정에서의 예외 처리
				.and()
				.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

	}
}

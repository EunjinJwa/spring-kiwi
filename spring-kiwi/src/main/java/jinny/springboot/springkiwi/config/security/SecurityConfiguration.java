package jinny.springboot.springkiwi.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final JwtTokenProvider jwtTokenProvider;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.httpBasic().disable()
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers("/sign-api/sign-in", "/sign-api/sign-in", "/sign-api/exception").permitAll()	// 해당 경로 요청은 모두에게 권한 허용
				.antMatchers(HttpMethod.GET, "/product/**").permitAll()		// /product로 시작하는 GET 요청 권한 허용
				.antMatchers("**exception**").permitAll()
				.anyRequest().hasRole("ADMIN")
				.and()
				.exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())	// 권한 통과 못한경우 예외 전달
				.and()
				.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint()) // 인증 과정 예외 전달
				.and()
				.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

	}

	/**
	 * WebSecurity는 스프링 시큐리티의 영향권 밖임.
	 * 인증과 인가가 적용되기 전에 동작하는 설정.
	 * */
	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
		// 인증, 인가를 무시하는 경로 설정
		webSecurity.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/swagger/**", "/sign-api/exception");

		super.configure(webSecurity);
	}
}

package jinny.springboot.springkiwi.config.security;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final JwtTokenProvider jwtTokenProvider;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String token = jwtTokenProvider.resolveToken(request);
		logger.info("[doFilterInternal] token 추출 완료 {}", token);

		logger.info("[doFilterInternal] token 유효성 체크 시작");
		if (token != null && jwtTokenProvider.validateToken(token)) {
			Authentication authentication = jwtTokenProvider.getAuthentication(token);
			// SecurityContextHolder에 추가
			SecurityContextHolder.getContext().setAuthentication(authentication);
			logger.info("[doFilterInternal] SecurityContextHolder에 authentication 추가");
		}

		filterChain.doFilter(request, response);		// servlet 실행. 이후 로직은 서블릿 실행 후 실행됨.
	}
}

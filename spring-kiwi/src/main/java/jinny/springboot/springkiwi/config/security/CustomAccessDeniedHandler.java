package jinny.springboot.springkiwi.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Access 권한이 없는 리소스에 접근할 경우 처리 핸들러
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
		logger.info("[AccessDenied] 접근이 막혔을 경우 경로 리다이렉트", e);
		httpServletResponse.sendRedirect("/sign-api/exception");
	}


}

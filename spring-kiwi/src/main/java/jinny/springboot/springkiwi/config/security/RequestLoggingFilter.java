package jinny.springboot.springkiwi.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestLoggingFilter implements Filter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		logger.info("[filter] request : {}", request.getRequestURI());

		filterChain.doFilter(servletRequest, servletResponse);

		HttpServletResponse response = (HttpServletResponse) servletResponse;
	}
}

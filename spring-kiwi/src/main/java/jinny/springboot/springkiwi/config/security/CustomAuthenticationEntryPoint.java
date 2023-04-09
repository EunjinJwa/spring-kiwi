package jinny.springboot.springkiwi.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jinny.springboot.springkiwi.data.dto.EntryPointErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		logger.info("[AuthenticationEntryPoint commence] 인증 실패고 exception 발생 : {}", httpServletRequest.getRequestURI());

		ObjectMapper objectMapper = new ObjectMapper();
		EntryPointErrorResponse entryPointErrorResponse = new EntryPointErrorResponse();
		entryPointErrorResponse.setMsg("인증이 실패했습니다.");
		entryPointErrorResponse.setDesctiption(e.getMessage());

		httpServletResponse.setStatus(401);
		httpServletResponse.setContentType("application/json");
		httpServletResponse.setCharacterEncoding("utf-8");
		httpServletResponse.getWriter().write(objectMapper.writeValueAsString(entryPointErrorResponse));
	}
}

package jinny.springboot.springkiwi.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<Map<String, String>> handleException(RuntimeException e, HttpServletRequest request) {

		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		logger.error("Advice 내 handleException 호출, {}, {}", request.getRequestURI(), e.getMessage());

		Map<String, String> response = new HashMap<>();
		response.put("error type", httpStatus.getReasonPhrase());
		response.put("code", "400");
		response.put("message", e.getMessage());

		return new ResponseEntity<>(response, responseHeaders, httpStatus);
	}

	@ExceptionHandler(value = CustomException.class)
	public ResponseEntity<Map<String, String>> customHandleException(CustomException e, HttpServletRequest request) {

		HttpHeaders responseHeaders = new HttpHeaders();

		logger.error("Advice 내 customHandleException 호출, {}, {}", request.getRequestURI(), e.getMessage());

		Map<String, String> response = new HashMap<>();
		response.put("error type", e.getHttpStatus().getReasonPhrase());
		response.put("code", e.getExceptionCode());
		response.put("message", e.getMessage());

		return new ResponseEntity<>(response, responseHeaders, e.getHttpStatus());
	}
}

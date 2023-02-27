package jinny.springboot.springkiwi.controller;

import jinny.springboot.springkiwi.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exception")
public class ExceptionController {

	@GetMapping
	public String callRuntimeException() {
		throw new RuntimeException("throw RuntimeException 호출");
	}

	@GetMapping("/custom1")
	public String callCustomException() throws CustomException {
		throw new CustomException("Exception test", "PRODUCT", HttpStatus.FORBIDDEN);
	}
}

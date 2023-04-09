package jinny.springboot.springkiwi.controller;

import io.swagger.annotations.ApiParam;
import jinny.springboot.springkiwi.common.CommonResponse;
import jinny.springboot.springkiwi.data.dto.HttpExceptionResponse;
import jinny.springboot.springkiwi.data.dto.SignInResultDto;
import jinny.springboot.springkiwi.data.dto.SignUpResultDto;
import jinny.springboot.springkiwi.service.SignService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sign-api")
public class SignController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final SignService signService;

	@PostMapping(value = "/sign-in")
	public SignInResultDto signIn(
			@ApiParam(value = "ID", required = true) @RequestParam String id,
			@ApiParam(value = "Password", required = true) @RequestParam String password
	) throws RuntimeException {

		try {
			SignInResultDto signInResultDto = signService.signIn(id, password);

			if (signInResultDto.getCode() == CommonResponse.SUCCESS.getCode()) {
				logger.info("로그인 성공. id: {}, token: {}", id, signInResultDto.getToken());
			} else {
				throw new RuntimeException("로그인에 실패하였습니다.");
			}
			return signInResultDto;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@PostMapping(value = "sign-up")
	public SignUpResultDto signUp(
			@ApiParam(value = "ID", required = true) @RequestParam String id,
			@ApiParam(value = "Password", required = true) @RequestParam String password,
			@ApiParam(value = "이름", required = true) @RequestParam String name,
			@ApiParam(value = "권한", required = true) @RequestParam String role
	) {
		SignUpResultDto signUpResultDto = signService.signUp(id, password, name, role);

		return signUpResultDto;
	}

	@GetMapping(value = "/exception")
	public void exceptionTeset() throws RuntimeException {
		throw new RuntimeException("접근이 금지되었습니다.");
	}


	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<HttpExceptionResponse> ExceptionHandler(RuntimeException e) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		HttpExceptionResponse response = new HttpExceptionResponse(httpStatus.getReasonPhrase(), "400", e.getMessage());

		return new ResponseEntity<>(response, responseHeaders, httpStatus);
	}
}

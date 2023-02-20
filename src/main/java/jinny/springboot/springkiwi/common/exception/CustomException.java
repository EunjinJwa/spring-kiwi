package jinny.springboot.springkiwi.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends Exception {

	private String exceptionCode = "Product Custom";
	private HttpStatus httpStatus;

	public CustomException(String message, String exceptionCode, HttpStatus httpStatus) {
		super(exceptionCode + message);
		this.exceptionCode = exceptionCode;
		this.httpStatus = httpStatus;
	}




}

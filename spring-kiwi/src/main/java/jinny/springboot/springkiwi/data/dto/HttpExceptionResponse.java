package jinny.springboot.springkiwi.data.dto;

public class HttpExceptionResponse {

	private String errorType;
	private String code;
	private String message;

	public HttpExceptionResponse(String errorType, String code, String message) {
		this.errorType = errorType;
		this.code = code;
		this.message = message;
	}
}

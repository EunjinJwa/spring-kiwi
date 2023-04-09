package jinny.springboot.springkiwi.service;

import jinny.springboot.springkiwi.data.dto.SignInResultDto;
import jinny.springboot.springkiwi.data.dto.SignUpResultDto;

public interface SignService {

	SignUpResultDto signUp(String id, String password, String name, String role);

	SignInResultDto signIn(String id, String password) throws RuntimeException;
}

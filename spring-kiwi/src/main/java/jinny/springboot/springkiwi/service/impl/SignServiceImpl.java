package jinny.springboot.springkiwi.service.impl;

import jinny.springboot.springkiwi.common.CommonResponse;
import jinny.springboot.springkiwi.config.security.JwtTokenProvider;
import jinny.springboot.springkiwi.data.dto.SignInResultDto;
import jinny.springboot.springkiwi.data.dto.SignUpResultDto;
import jinny.springboot.springkiwi.data.entity.User;
import jinny.springboot.springkiwi.data.repository.UserRepository;
import jinny.springboot.springkiwi.service.SignService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class SignServiceImpl implements SignService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public final UserRepository userRepository;
	public final JwtTokenProvider jwtTokenProvider;
	public final PasswordEncoder passwordEncoder;

	@Override
	public SignUpResultDto signUp(String id, String password, String name, String role) {
		User user = User.builder()
				.uid(id)
				.name(name)
				.password(passwordEncoder.encode(password))
				.roles(Collections.singletonList(getRole(role)))
				.build();

		User savedUser = userRepository.save(user);
		SignUpResultDto signUpResultDto = new SignUpResultDto();

		if (!savedUser.getName().isEmpty()) {
			setSuccessResult(signUpResultDto);
		} else {
			setFailResult(signUpResultDto);
		}
		return signUpResultDto;
	}

	private void setSuccessResult(SignUpResultDto signUpResultDto) {
		signUpResultDto.setSuccess(true);
		signUpResultDto.setCode(CommonResponse.SUCCESS.getCode());
		signUpResultDto.setMsg(CommonResponse.SUCCESS.getMsg());
	}

	private void setFailResult(SignUpResultDto signUpResultDto) {
		signUpResultDto.setSuccess(false);
		signUpResultDto.setCode(CommonResponse.FAIL.getCode());
		signUpResultDto.setMsg(CommonResponse.FAIL.getMsg());
	}


	private String getRole(String role) {
		if (role.equalsIgnoreCase("admin")) {
			return "ROLE_ADMIN";
		}
		return "ROLE_USER";
	}


	@Override
	public SignInResultDto signIn(String id, String password) throws RuntimeException {
		User user = userRepository.getByUid(id);

		if (user == null) {
			throw new RuntimeException("유저 정보가 존재하지 않습니다.");
		}

		// password 비교 수행
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new RuntimeException("비밀번호가 일치하지 않습니다.");
		}

		SignInResultDto signInResultDto = SignInResultDto.builder()
				.token(jwtTokenProvider.createToken(user.getUid(), user.getRoles()))
				.build();

		setSuccessResult(signInResultDto);
		return signInResultDto;
	}

}

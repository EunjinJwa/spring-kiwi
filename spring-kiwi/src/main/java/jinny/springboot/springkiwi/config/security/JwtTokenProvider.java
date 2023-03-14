package jinny.springboot.springkiwi.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final UserDetailsService userDetailsService;

	@Value("${spring.jwt.secret}")
	private String secretKey = "secretKey";
	private final long tokenValidMillisecond = 1000L * 60 * 60;

	@PostConstruct
	protected void init() {
		logger.info("[init] JwtTokenProvider secretKey 초기화 시작");
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
		logger.info("[init] JwtTokenProvider secretKey 초기화 완료");
	}

	public String createToken(String userUid, List<String> roles) {
		logger.info("[createToken] 토큰 생성 시작");

		Claims claims = Jwts.claims().setSubject(userUid);		// Claims : JWT 토큰에 포함되는 정보 속성
		claims.put("roles", roles);

		Date now = new Date();

		String token = Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + tokenValidMillisecond))
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();

		logger.info("[createToken] 토큰 생성 완료");
		return token;
	}

	/**
	 * ==================================
	 * Request Token 검증
	 * ==================================
	 * */

	public String resolveToken(HttpServletRequest request) {
		logger.info("[resolveToken] HTTP 헤더에서 Token 값 추출");
		return request.getHeader("X-AUTH-TOKEN");
	}

	public boolean validateToken(String token) {
		logger.info("[validateToken] 토큰 유효 체크 시작");
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

			return !claims.getBody().getExpiration().before(new Date());
		} catch (Exception e) {
			logger.info("[validateToken] 토큰 유효성 체크 예외 발생");
			return false;
		}
	}
	public Authentication getAuthentication(String token) {
		logger.info("[getAuthentication] 토큰 인증 정보 조회 시작");
		UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUsername(token));
		logger.info("[getAuthentication] 토큰 인증 정보 조회 완료");

		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	private String getUsername(String token) {
		logger.info("[getUsername] 토큰 기반 회원 구별 정보 추출");
		String info = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
		logger.info("[getUsername] 토큰 기반 회원 구별 정보 추출 완료, info : {}", info);
		return info;
	}


}

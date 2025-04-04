package com.personal.ojh.auth;

import com.personal.ojh.auth.dto.AccessTokenResponseDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final AuthProperties authProps;

	public AccessTokenResponseDto authenticate(String rawPassword) {
		if (!authProps.getPassword().equals(rawPassword)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
		}
		return AccessTokenResponseDto.builder()
			.token(createToken())
			.build();
	}

	private String createToken() {
		Date now = new Date();
		Date expiry = new Date(now.getTime() + authProps.getTokenExpiration() * 1000);

		return Jwts.builder()
			.setSubject("single-user")  // 고정 유저
			.setIssuedAt(now)
			.setExpiration(expiry)
			.signWith(SignatureAlgorithm.HS256, authProps.getJwtSecret())
			.compact();
	}

	public void validateToken(String token) {
		try {
			Jwts.parser()
				.setSigningKey(authProps.getJwtSecret())
				.parseClaimsJws(token); // 유효성만 확인
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "토큰이 유효하지 않습니다.");
		}
	}
}
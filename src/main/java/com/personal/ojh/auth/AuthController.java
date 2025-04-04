package com.personal.ojh.auth;

import com.personal.ojh.auth.dto.AccessTokenRequestDto;
import com.personal.ojh.auth.dto.AccessTokenResponseDto;
import com.personal.ojh.common.dto.GlobalResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "인증", description = "Access Token 발급 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;

	@PostMapping("/token")
	@Operation(
		summary = "Access Token 발급",
		description = "비밀번호를 기반으로 Access Token을 발급합니다.",
		security = @SecurityRequirement(name = "")
	)
	public ResponseEntity<GlobalResponseDto<AccessTokenResponseDto>> getToken(@RequestBody AccessTokenRequestDto body) {
		return ResponseEntity.ok(GlobalResponseDto.ok(authService.authenticate(body.getPassword())));
	}
}
package com.personal.ojh.auth.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenRequestDto {

	// ❗️ 비밀번호를 기반으로 Access Token을 발급합니다.
	@NotNull(message = "비밀번호는 필수입니다.")
	private String password;

}

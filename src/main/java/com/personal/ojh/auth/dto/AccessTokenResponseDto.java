package com.personal.ojh.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccessTokenResponseDto {

	private String token;

}

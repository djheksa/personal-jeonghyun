package com.personal.ojh.users.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UsersResponseDto {

	private String name;
	private String email;
}

package com.personal.ojh.common.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GlobalResponseDto<T> {

	private int status;
	private String message;
	private T data;

	public static <T> GlobalResponseDto<T> ok(T data) {
		return GlobalResponseDto.<T>builder()
			.status(200)
			.message("요청이 성공적으로 처리되었습니다.")
			.data(data)
			.build();
	}

	public static <T> GlobalResponseDto<T> of(int status, String message, T data) {
		return GlobalResponseDto.<T>builder()
			.status(status)
			.message(message)
			.data(data)
			.build();
	}

	public static <T> GlobalResponseDto<T> error(String message, int status) {
		return GlobalResponseDto.<T>builder()
			.status(status)
			.message(message)
			.data(null)
			.build();
	}
}
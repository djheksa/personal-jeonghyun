package com.personal.ojh.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.personal.ojh.auth.dto.AccessTokenRequestDto;
import com.personal.ojh.common.dto.GlobalResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerTest {

	@Value("${AUTH_PASSWORD}")
	private String authPassword;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void getToken() {
		String url = "/auth/token";
		String password = authPassword;

		AccessTokenRequestDto requestDto = new AccessTokenRequestDto(password);

		// when
		GlobalResponseDto response = restTemplate.postForEntity(url, requestDto, GlobalResponseDto.class).getBody();

		// then
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
}
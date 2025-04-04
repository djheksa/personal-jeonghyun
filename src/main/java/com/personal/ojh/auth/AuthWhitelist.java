package com.personal.ojh.auth;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Getter
public enum AuthWhitelist {

	AUTH_TOKEN("/auth/token"),
	SWAGGER_UI("/swagger-ui/**"),
	SWAGGER_HTML("/swagger-ui.html"),
	SWAGGER_DOCS("/v3/api-docs/**"),
	SWAGGER_CUSTOM_UI("/docs"),
	SWAGGER_CUSTOM_UI_JSON("/docs-json"),
	SWAGGER_CUSTOM_UI_JSON_ALL("/docs-json/**");


	private final String path;

	AuthWhitelist(String path) {
		this.path = path;
	}

	// ✅ RequestMatcher 리스트 반환
	public static List<AntPathRequestMatcher> asRequestMatchers() {
		return Arrays.stream(values())
			.map(e -> new AntPathRequestMatcher(e.path))
			.toList();
	}

	// ✅ 바로 String[] 반환
	public static String[] getAllStringArray() {
		return java.util.Arrays.stream(values())
			.map(AuthWhitelist::getPath)
			.toArray(String[]::new);
	}
}

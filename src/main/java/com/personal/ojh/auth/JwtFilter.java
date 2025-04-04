package com.personal.ojh.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

	private final AuthService authService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		// 요청된 endpoint 출력
		log.info("Request URI: {}", request.getRequestURI());

		boolean isWhitelisted = AuthWhitelist.asRequestMatchers().stream()
			.anyMatch(matcher -> matcher.matches(request));

		if (isWhitelisted) {
			filterChain.doFilter(request, response);
			return;
		}

		String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return;
		}

		String token = authHeader.substring(7);

		try {
			authService.validateToken(token);
			filterChain.doFilter(request, response);  // 통과
		} catch (ResponseStatusException ex) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}
	}
}
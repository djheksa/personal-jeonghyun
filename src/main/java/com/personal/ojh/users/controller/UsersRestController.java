package com.personal.ojh.users.controller;

import com.personal.ojh.users.dto.UsersResponseDto;
import com.personal.ojh.users.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersRestController {

	private final UsersService usersService;

	@GetMapping("/temp")
	public String getUsers() {
		return "Users";
	}

	@GetMapping
	public ResponseEntity<UsersResponseDto> getUsersByEmail(HttpServletRequest request, @RequestParam String email) {
		return ResponseEntity.ok(usersService.getUsersByEmail(email));
	}
}

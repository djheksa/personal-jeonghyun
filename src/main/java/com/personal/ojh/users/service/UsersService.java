package com.personal.ojh.users.service;

import com.personal.ojh.users.dto.UsersResponseDto;
import com.personal.ojh.users.entity.Users;
import com.personal.ojh.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsersService {

	private final UsersRepository usersRepository;

	@Transactional(readOnly = true)
	public UsersResponseDto getUsersByEmail(String email) {
		Users users = usersRepository.findByEmail(email);

		return UsersResponseDto.builder()
			.name(users.getName())
			.email(users.getEmail())
			.build();
	}
}

package com.personal.ojh.users.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users",
	indexes = {
		@Index(name = "idx_name", columnList = "name"),
		@Index(name = "idx_email", columnList = "email", unique = true)
	})
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 기본 키 (자동 증가)

	@Column(nullable = false, length = 50)
	private String name; // 이름 (NULL 불가, 길이 제한)

	@Column(nullable = false, length = 100, unique = true)
	private String email; // 이메일 (NULL 불가, UNIQUE 설정)

	@Column(length = 20)
	private String phone; // 전화번호 (길이 제한)

	@Column(length = 255)
	private String address; // 주소 (길이 제한)

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdDate; // 생성일 (자동 저장)

	@UpdateTimestamp
	private LocalDateTime modifiedDate; // 수정일 (자동 업데이트)
}
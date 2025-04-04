package com.personal.ojh.common.exception;

import com.personal.ojh.common.dto.GlobalResponseDto;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<GlobalResponseDto<Void>> handleResponseStatus(ResponseStatusException ex) {
		log.warn("ResponseStatusException: {}", ex.getMessage());
		return ResponseEntity
			.status(ex.getStatusCode())
			.body(GlobalResponseDto.error(ex.getReason(), ex.getStatusCode().value()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<GlobalResponseDto<Void>> handleValidation(MethodArgumentNotValidException ex) {
		String message = ex.getBindingResult().getFieldErrors().stream()
			.findFirst()
			.map(err -> err.getField() + ": " + err.getDefaultMessage())
			.orElse("요청값이 유효하지 않습니다.");

		log.warn("Validation Failed: {}", message);

		return ResponseEntity
			.badRequest()
			.body(GlobalResponseDto.error(message, 400));
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<GlobalResponseDto<Void>> handleConstraint(ConstraintViolationException ex) {
		String message = ex.getConstraintViolations().stream()
			.map(cv -> cv.getPropertyPath() + ": " + cv.getMessage())
			.findFirst()
			.orElse("요청값이 유효하지 않습니다.");
		return ResponseEntity
			.badRequest()
			.body(GlobalResponseDto.error(message, 400));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<GlobalResponseDto<Void>> handleUnknown(Exception ex) {
		log.error("Unhandled Exception", ex);
		return ResponseEntity
			.status(500)
			.body(GlobalResponseDto.error("서버 내부 오류가 발생했습니다.", 500));
	}
}
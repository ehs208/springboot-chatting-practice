package com.example.realtime_chat.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.realtime_chat.global.response.GlobalResponseDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<GlobalResponseDto> handleException(Exception e) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		log.error(e.getMessage());
		return ResponseEntity.status(status).body(GlobalResponseDto.fail("서버 내부 오류", status));
	}
}

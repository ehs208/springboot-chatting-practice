package com.example.realtime_chat.global.response;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GlobalResponseDto<T> {
	private String message;
	private HttpStatus status;
	private Boolean isSuccess;
	private T data;

	@Builder
	public GlobalResponseDto(T data, String message, HttpStatus status, Boolean isSuccess) {
		this.data = data;
		this.message = message;
		this.status = status;
		this.isSuccess = isSuccess;
	}

	public static <T> GlobalResponseDto<T> success(T data) {
		return GlobalResponseDto.<T>builder()
			.data(data)
			.message("성공")
			.status(HttpStatus.OK)
			.isSuccess(true)
			.build();
	}

	public static GlobalResponseDto success() {
		return GlobalResponseDto.builder()
			.message("성공")
			.status(HttpStatus.OK)
			.isSuccess(true)
			.build();
	}

	public static GlobalResponseDto createSuccess() {
		return GlobalResponseDto.builder()
			.message("성공")
			.status(HttpStatus.CREATED)
			.isSuccess(true)
			.build();
	}

	public static GlobalResponseDto fail(String message, HttpStatus status) {
		return GlobalResponseDto.builder()
			.message(message)
			.status(status)
			.isSuccess(false)
			.build();
	}

}


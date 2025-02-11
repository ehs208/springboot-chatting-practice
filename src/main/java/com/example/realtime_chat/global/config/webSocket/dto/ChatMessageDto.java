package com.example.realtime_chat.global.config.webSocket.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatMessageDto {
	private String sender;
	private String content;
	private LocalDateTime timestamp;

	@Builder
	public ChatMessageDto(String sender, String content, LocalDateTime timestamp) {
		this.sender = sender;
		this.content = content;
		this.timestamp = timestamp;
	}

	public static ChatMessageDto createChatMessageDto(String sender, String content, LocalDateTime timestamp) {
		return ChatMessageDto.builder()
			.sender(sender)
			.content(content)
			.timestamp(timestamp)
			.build();
	}

}

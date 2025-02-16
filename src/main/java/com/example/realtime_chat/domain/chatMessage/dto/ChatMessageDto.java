package com.example.realtime_chat.domain.chatMessage.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatMessageDto {
	private String sender;
	private String content;
	private Long roomId;
	private LocalDateTime timestamp;

	@Builder
	public ChatMessageDto(String sender, String content, LocalDateTime timestamp, Long roomId) {
		this.roomId = roomId;
		this.sender = sender;
		this.content = content;
		this.timestamp = timestamp;
	}

	public static ChatMessageDto createChatMessageDto(String sender, String content, Long roomId,
		LocalDateTime timestamp) {
		return ChatMessageDto.builder()
			.sender(sender)
			.roomId(roomId)
			.content(content)
			.timestamp(timestamp)
			.build();
	}
}

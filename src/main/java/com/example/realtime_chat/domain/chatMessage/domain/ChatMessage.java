package com.example.realtime_chat.domain.chatMessage.domain;

import com.example.realtime_chat.domain.BaseTimeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class ChatMessage extends BaseTimeEntity {
	@Id
	@GeneratedValue
	private Long id;

	private String sender;

	private String content;

	@Builder
	private ChatMessage(String sender, String content) {
		this.sender = sender;
		this.content = content;
	}

	public static ChatMessage createChatMessage(String sender, String content) {
		return ChatMessage.builder()
			.sender(sender)
			.content(content)
			.build();
	}
}


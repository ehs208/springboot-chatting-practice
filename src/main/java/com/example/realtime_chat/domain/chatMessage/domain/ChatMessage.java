package com.example.realtime_chat.domain.chatMessage.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class ChatMessage {
	@Id
	@GeneratedValue
	private Long id;

	private String sender;

	private String content;

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime timestamp;

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


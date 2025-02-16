package com.example.realtime_chat.domain.chatMessage.service;

import org.springframework.stereotype.Service;

import com.example.realtime_chat.domain.chatMessage.domain.ChatMessage;
import com.example.realtime_chat.domain.chatMessage.domain.repository.ChatMessageRepository;
import com.example.realtime_chat.domain.chatMessage.dto.ChatMessageDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
	private final ChatMessageRepository chatMessageRepository;

	public ChatMessageDto saveMessage(ChatMessageDto message) {

		ChatMessage chatMessage = ChatMessage.createChatMessage(message.getSender(), message.getContent(),
			message.getRoomId());

		chatMessageRepository.save(chatMessage);

		return ChatMessageDto.createChatMessageDto(chatMessage.getSender(), chatMessage.getContent(),
			chatMessage.getRoomId(), chatMessage.getTimestamp());
	}
}

package com.example.realtime_chat.domain.chatMessage.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.example.realtime_chat.domain.chatMessage.dto.ChatMessageDto;
import com.example.realtime_chat.domain.chatMessage.service.ChatMessageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ChatMessageController {

	private final ChatMessageService chatMessageService;
	private final SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/chat/message")
	public void message(ChatMessageDto message) {
		ChatMessageDto savedMessage = chatMessageService.saveMessage(message);
		simpMessagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), savedMessage);

	}

}

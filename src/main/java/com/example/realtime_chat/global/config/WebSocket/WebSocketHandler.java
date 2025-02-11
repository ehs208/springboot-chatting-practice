package com.example.realtime_chat.global.config.WebSocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.realtime_chat.domain.chatMessage.domain.ChatMessage;
import com.example.realtime_chat.domain.chatMessage.domain.repository.ChatMessageRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {
	private final WebSocketService webSocketService;
	private final ChatMessageRepository chatMessageRepository;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String nickname = getNicknameFromSession(session);
		webSocketService.addUser(session, nickname);

		log.info("웹소켓 연결 시작: {}", session);
		webSocketService.broadcastMessage(session.getId(), nickname + "님이 입장하셨습니다.");
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String sessionId = session.getId();
		WebSocketUser user = webSocketService.getUser(sessionId);

		if (user != null) {
			webSocketService.broadcastMessage(sessionId, user.nickname + "님이 퇴장하셨습니다.");
		}

		webSocketService.removeUser(sessionId);
		log.info("웹소켓 연결 종료: {}", session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String sessionId = session.getId();
		WebSocketUser user = webSocketService.getUser(sessionId);
		String payload = message.getPayload();

		chatMessageRepository.save(ChatMessage.createChatMessage(user.nickname, payload));
		webSocketService.broadcastMessage(sessionId, user.nickname + ": " + payload);
	}

	private String getNicknameFromSession(WebSocketSession session) {
		return session.getUri().getQuery().split("nickname=")[1];
	}
}

package com.example.realtime_chat.global.config.WebSocket;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

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

	private static final ConcurrentHashMap<String, WebSocketUser> CLIENTS = new ConcurrentHashMap<>();

	private final ChatMessageRepository chatMessageRepository;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String nickname = getNicknameFromSession(session);
		WebSocketUser user = WebSocketUser.create(session, nickname);

		CLIENTS.put(session.getId(), user);

		log.info("웹소켓 연결 시작: {}", session);
		broadcastMessage(session.getId(), nickname + "님이 입장하셨습니다.");
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		WebSocketUser user = CLIENTS.get(session.getId());
		String nickname = getNicknameFromSession(session);

		if (user != null) {
			broadcastMessage(session.getId(), nickname + "님이 퇴장하셨습니다.");
		}

		CLIENTS.remove(session.getId());

		log.info("웹소켓 연결 종료: {}", session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String id = session.getId();
		WebSocketUser user = CLIENTS.get(id);
		String payload = message.getPayload();

		// 채팅 메시지 저장 (일반 채팅 메시지만 DB에 저장)
		chatMessageRepository.save(ChatMessage.createChatMessage(user.nickname, payload));

		broadcastMessage(id, user.nickname + ": " + payload);
	}

	private void broadcastMessage(String senderId, String message) throws IOException {
		TextMessage textMessage = new TextMessage(message);
		CLIENTS.entrySet().forEach(arg -> {
			if (!arg.getKey().equals(senderId)) {
				try {
					arg.getValue().session.sendMessage(textMessage);
				} catch (IOException e) {
					log.error("Error sending message", e);
				}
			}
		});
	}

	private String getNicknameFromSession(WebSocketSession session) {
		return session.getUri().getQuery().split("nickname=")[1];
	}

}

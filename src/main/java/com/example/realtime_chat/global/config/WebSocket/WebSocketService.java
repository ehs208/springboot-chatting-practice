package com.example.realtime_chat.global.config.WebSocket;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketService {
	private static final ConcurrentHashMap<String, WebSocketUser> CLIENTS = new ConcurrentHashMap<>();

	public boolean isDuplicateNickname(String nickname) {
		return CLIENTS.values().stream()
			.anyMatch(user -> user.nickname.equals(nickname));
	}

	public void addUser(WebSocketSession session, String nickname) {
		WebSocketUser user = WebSocketUser.create(session, nickname);
		CLIENTS.put(session.getId(), user);
	}

	public void removeUser(String sessionId) {
		CLIENTS.remove(sessionId);
	}

	public WebSocketUser getUser(String sessionId) {
		return CLIENTS.get(sessionId);
	}

	public void broadcastMessage(String senderId, String message) throws IOException {
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
}

package com.example.realtime_chat.global.config.WebSocket;

import org.springframework.web.socket.WebSocketSession;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WebSocketUser {
	WebSocketSession session;
	String nickname;

	private WebSocketUser(WebSocketSession session, String nickname) {
		this.session = session;
		this.nickname = nickname;
	}

	public static WebSocketUser create(WebSocketSession session, String nickname) {
		return new WebSocketUser(session, nickname);
	}
}

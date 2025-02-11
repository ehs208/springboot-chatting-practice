package com.example.realtime_chat.global.config.webSocket;

import org.springframework.web.socket.WebSocketSession;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WebSocketUser {
	private WebSocketSession session;
	private String nickname;

	private WebSocketUser(WebSocketSession session, String nickname) {
		this.session = session;
		this.nickname = nickname;
	}

	public static WebSocketUser create(WebSocketSession session, String nickname) {
		return new WebSocketUser(session, nickname);
	}
}

package com.example.realtime_chat.global.config.webSocket;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

	private final WebSocketService webSocketService;

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
		WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

		String nickname = extractNickname(request.getURI().getQuery());

		if (webSocketService.isDuplicateNickname(nickname)) {
			response.setStatusCode(HttpStatus.FORBIDDEN);
			return false;
		}

		attributes.put("nickname", nickname);
		return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
		WebSocketHandler wsHandler, Exception exception) {
	}

	private String extractNickname(String query) {
		if (query != null) {
			String[] params = query.split("&");
			for (String param : params) {
				if (param.startsWith("nickname=")) {
					return param.split("=")[1];
				}
			}
		}
		throw new IllegalArgumentException("닉네임 파라미터를 입력하세요");
	}
}

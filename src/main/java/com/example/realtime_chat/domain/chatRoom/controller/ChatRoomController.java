package com.example.realtime_chat.domain.chatRoom.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.realtime_chat.domain.chatRoom.dto.CreateChatRoomRequestDto;
import com.example.realtime_chat.domain.chatRoom.service.ChatRoomService;

import lombok.RequiredArgsConstructor;

@RestController()
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class ChatRoomController {

	private final ChatRoomService chatroomService;

	@PostMapping("")
	public ResponseEntity<Void> createChatRoom(@RequestBody CreateChatRoomRequestDto createChatRoomRequestDto) {
		chatroomService.createChatRoom(createChatRoomRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	// @GetMapping
	//
	// @GetMapping("/api/v1/rooms/{roomId}")
	//
	// @GetMapping("/api/v1/rooms/{roomId}/messages")
}

package com.example.realtime_chat.domain.chatRoom.service;

import org.springframework.stereotype.Service;

import com.example.realtime_chat.domain.chatRoom.domain.ChatRoom;
import com.example.realtime_chat.domain.chatRoom.domain.repository.ChatRoomRepository;
import com.example.realtime_chat.domain.chatRoom.dto.CreateChatRoomRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

	private final ChatRoomRepository chatRoomRepository;

	public void createChatRoom(CreateChatRoomRequestDto createChatRoomRequestDto) {
		ChatRoom chatRoom = ChatRoom.builder()
			.name(createChatRoomRequestDto.getName())
			.maxMembers(createChatRoomRequestDto.getMaxMembers())
			.build();

		chatRoomRepository.save(chatRoom);
	}

}

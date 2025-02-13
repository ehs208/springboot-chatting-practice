package com.example.realtime_chat.domain.chatRoom.dto;

import lombok.Getter;

@Getter
public class CreateChatRoomRequestDto {
	private String name;
	private Integer maxMembers;
}

package com.example.realtime_chat.domain.chatRoom.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.realtime_chat.domain.chatRoom.domain.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

}

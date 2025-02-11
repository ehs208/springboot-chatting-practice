package com.example.realtime_chat.domain.chatMessage.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.realtime_chat.domain.chatMessage.domain.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

}

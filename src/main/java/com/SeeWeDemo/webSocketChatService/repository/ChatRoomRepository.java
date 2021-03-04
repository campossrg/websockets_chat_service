package com.SeeWeDemo.webSocketChatService.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.SeeWeDemo.webSocketChatService.model.ChatRoom;

public interface ChatRoomRepository extends CrudRepository<ChatRoom, String> {
    Optional<ChatRoom> findBySenderIdAndRecipientId(Integer senderId, Integer recipientId);
}

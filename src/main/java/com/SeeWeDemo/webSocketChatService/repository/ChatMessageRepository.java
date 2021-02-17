package com.SeeWeDemo.webSocketChatService.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.SeeWeDemo.webSocketChatService.model.ChatMessage;
import com.SeeWeDemo.webSocketChatService.model.MessageStatus;

public interface ChatMessageRepository
        extends CrudRepository<ChatMessage, String> {

    long countBySenderIdAndRecipientIdAndStatus(
            String senderId, String recipientId, MessageStatus status);

    List<ChatMessage> findByChatId(String chatId);
}
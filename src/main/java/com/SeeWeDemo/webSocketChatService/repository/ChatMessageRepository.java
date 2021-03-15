package com.SeeWeDemo.webSocketChatService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.SeeWeDemo.webSocketChatService.model.ChatMessage;
import com.SeeWeDemo.webSocketChatService.model.MessageStatus;

public interface ChatMessageRepository
        extends CrudRepository<ChatMessage, Integer> {

    long countBySenderIdAndRecipientIdAndStatus(
            String senderId, String recipientId, MessageStatus status);

    List<ChatMessage> findByChatId(String cId);
    
    @Modifying
    @Query("update ChatMessage cm set cm.status = :status "
    		+ "where cm.senderId = :senderId "
    		+ "and cm.recipientId = :recipientId") 
    int updateMessageSetStatus(@Param("status") MessageStatus status,
    								@Param("senderId") Integer senderId,
    								@Param("recipientId") Integer recipientId);
}
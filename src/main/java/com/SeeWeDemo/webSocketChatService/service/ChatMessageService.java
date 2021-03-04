package com.SeeWeDemo.webSocketChatService.service;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SeeWeDemo.webSocketChatService.exception.ResourceNotFoundException;
import com.SeeWeDemo.webSocketChatService.model.ChatMessage;
import com.SeeWeDemo.webSocketChatService.model.MessageStatus;
import com.SeeWeDemo.webSocketChatService.repository.ChatMessageRepository;

@Service
public class ChatMessageService {
    @Autowired private ChatMessageRepository repository;
    @Autowired private ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage) {
        chatMessage.setStatus(MessageStatus.RECEIVED);
        repository.save(chatMessage);
        return chatMessage;
    }

    public long countNewMessages(String senderId, String recipientId) {
        return repository.countBySenderIdAndRecipientIdAndStatus(
                senderId, recipientId, MessageStatus.RECEIVED);
    }

    @Transactional
    public List<ChatMessage> findChatMessages(Integer senderId, Integer recipientId) {
        var chatId = chatRoomService.getChatId(senderId, recipientId, false);

        var messages =
                chatId.map(cId -> repository.findByChatId(cId)).orElse(new ArrayList<>());

        if(messages.size() > 0) {
        	try {
        		repository.updateMessageSetStatus(MessageStatus.DELIVERED, senderId, recipientId);
        	} catch (Exception e) {
        		e.getMessage();
			}
        }

        return messages;
    }

    public ChatMessage findById(String id) {
        return repository
                .findById(id)
                .map(chatMessage -> {
                    chatMessage.setStatus(MessageStatus.DELIVERED);
                    return repository.save(chatMessage);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("can't find message (" + id + ")"));
    }
}

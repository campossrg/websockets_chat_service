package com.SeeWeDemo.webSocketChatService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SeeWeDemo.webSocketChatService.model.ChatRoom;
import com.SeeWeDemo.webSocketChatService.repository.ChatRoomRepository;

@Service
public class ChatRoomService {

    @Autowired private ChatRoomRepository chatRoomRepository;

    public Optional<String> getChatId(
    		Integer senderId, Integer recipientId, boolean createIfNotExist) {

         return chatRoomRepository
                .findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom::getChatId)
                 .or(() -> {
                    if(!createIfNotExist) {
                        return  Optional.empty();
                    }
                    String chatId =
                            String.format("%s_%s", senderId, recipientId);

                    ChatRoom senderRecipient = new ChatRoom(chatId, recipientId, senderId);
                    ChatRoom recipientSender = new ChatRoom(chatId, senderId, recipientId);
                            
                    chatRoomRepository.save(senderRecipient);
                    chatRoomRepository.save(recipientSender);

                    return Optional.of(chatId);
                });               
    }
}

package com.SeeWeDemo.webSocketChatService.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.SeeWeDemo.webSocketChatService.model.ChatMessage;
import com.SeeWeDemo.webSocketChatService.model.ChatNotification;
import com.SeeWeDemo.webSocketChatService.model.Message;
import com.SeeWeDemo.webSocketChatService.model.MessageStatus;
import com.SeeWeDemo.webSocketChatService.model.OutputMessage;
import com.SeeWeDemo.webSocketChatService.service.ChatMessageService;
import com.SeeWeDemo.webSocketChatService.service.ChatRoomService;

@Controller
public class ChatController {

    @Autowired private SimpMessagingTemplate messagingTemplate;
    @Autowired private ChatMessageService chatMessageService;
    @Autowired private ChatRoomService chatRoomService;

    /*
    @MessageMapping("/ws")
    @SendTo("/user/messages")
    public OutputMessage send(Message message) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new OutputMessage(message.getFrom(), message.getText(), time);
    }
   
    @MessageMapping("/ws")
    public void processMessage(@Payload ChatMessage chatMessage) {
        var chatId = chatRoomService
                .getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chatMessage.setChatId(chatId.get());

        ChatMessage saved = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(),"/queue/messages",
                new ChatNotification(
                        saved.getId(),
                        saved.getSenderId(),
                        saved.getSenderName()));
    }
    
    @MessageMapping("/ws")
    @SendTo("/messages")
    public OutputMessage send(Message message) throws Exception {
    	
    	Date date = new Date();
    	ChatMessage chatMessage = new ChatMessage();
    	
    	ChatMessage saved = chatMessageService.save(chatMessage);
    	
    	String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new OutputMessage(message.getFrom(), message.getText(), time);
    }
	*/
    
    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        var chatId = chatRoomService
                .getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chatMessage.setChatId(chatId.get());

        ChatMessage saved = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId().toString(),"/queue/messages",
                new ChatNotification(
                        saved.getId().toString(),
                        saved.getSenderId().toString(),
                        saved.getSenderName()));
    }
        
    @GetMapping("/messages/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewMessages(
            @PathVariable String senderId,
            @PathVariable String recipientId) {

        return ResponseEntity
                .ok(chatMessageService.countNewMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessages ( @PathVariable Integer senderId,
                                                @PathVariable Integer recipientId) {
        return ResponseEntity
                .ok(chatMessageService.findChatMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage ( @PathVariable String id) {
        return ResponseEntity
                .ok(chatMessageService.findById(id));
    }
}

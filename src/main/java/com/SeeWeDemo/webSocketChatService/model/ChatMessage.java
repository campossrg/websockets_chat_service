package com.SeeWeDemo.webSocketChatService.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChatMessage {
	
   @Id 
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Integer id;
   private String chatId;
   private Integer senderId;
   private Integer recipientId;
   private String senderName;
   private String recipientName;
   private String content;
   private Date timestamp;
   private MessageStatus status;
   
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getChatId() {
	return chatId;
}
public void setChatId(String chatId) {
	this.chatId = chatId;
}
public Integer getSenderId() {
	return senderId;
}
public void setSenderId(Integer senderId) {
	this.senderId = senderId;
}
public Integer getRecipientId() {
	return recipientId;
}
public void setRecipientId(Integer recipientId) {
	this.recipientId = recipientId;
}
public String getSenderName() {
	return senderName;
}
public void setSenderName(String senderName) {
	this.senderName = senderName;
}
public String getRecipientName() {
	return recipientName;
}
public void setRecipientName(String recipientName) {
	this.recipientName = recipientName;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Date getTimestamp() {
	return timestamp;
}
public void setTimestamp(Date timestamp) {
	this.timestamp = timestamp;
}
public MessageStatus getStatus() {
	return status;
}
public void setStatus(MessageStatus status) {
	this.status = status;
}
   
}

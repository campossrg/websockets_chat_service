package com.SeeWeDemo.webSocketChatService.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChatRoom {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String chatId;
    private Integer senderId;
    private Integer recipientId;
    
    public ChatRoom() {};
    
	public ChatRoom(String chatId, Integer senderId, Integer recipientId) {
		super();
		this.chatId = chatId;
		this.senderId = senderId;
		this.recipientId = recipientId;
	}
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
}

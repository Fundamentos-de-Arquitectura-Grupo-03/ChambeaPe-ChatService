package com.digitaldart.chatservice.ChatMessage.service;

import com.digitaldart.chatservice.ChatMessage.dto.ChatMessage;

import java.util.List;

public interface ChatMessageService {
    ChatMessage saveMessage(ChatMessage message, String roomId);
    List<ChatMessage> getAllMessagesByRoomId(String roomId);
    ChatMessage getLastMessageByRoomId(String roomId);
}

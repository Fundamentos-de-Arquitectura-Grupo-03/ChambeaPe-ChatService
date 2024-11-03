package com.digitaldart.chatservice.ChatRoom.service;

import com.digitaldart.chatservice.ChatRoom.model.ChatRoomModel;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

public interface ChatRoomService {
    Optional<ChatRoomModel> getChatRoomById(String chatroomId);
    void saveChatRoom(ChatRoomModel chatRoomModel);
    List<String> getUsersIdByExistingChatroom(String userId);
    List<String> getAllUsersId();
    String generateChatRoomId(String userId, String id) throws NoSuchAlgorithmException;
}

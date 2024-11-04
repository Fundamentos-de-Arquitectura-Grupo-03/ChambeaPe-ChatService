package com.digitaldart.chatservice.ChatRoom.service.impl;

import com.digitaldart.chatservice.ChatRoom.model.ChatRoomModel;
import com.digitaldart.chatservice.ChatRoom.repository.ChatRoomRepository;
import com.digitaldart.chatservice.ChatRoom.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {
    @Autowired
    ChatRoomRepository chatRoomRepository;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Optional<ChatRoomModel> getChatRoomById(String chatroomId) {
        return chatRoomRepository.findByRoomId(chatroomId);
    }

    @Override
    public void saveChatRoom(ChatRoomModel chatRoomModel) {
        chatRoomRepository.save(chatRoomModel);
    }

    @Override
    public List<String> getUsersIdByExistingChatroom(String userId) {
        List<String> userIds = getAllUsersId();
        List<String> existingChatUserIds = new ArrayList<>();

        for (String id : userIds) {
            try{
                if(id.compareTo(userId) != 0){
                    String chatRoomId = generateChatRoomId(userId, id);
                    if (chatRoomRepository.existsById(chatRoomId)) {
                        existingChatUserIds.add(id);
                    }
                }
            }
            catch (NoSuchAlgorithmException e){
                System.out.println(e.getMessage());
            }
        }

        return existingChatUserIds;
    }

    @Override
    public List<String> getAllUsersId() {
        String url = "https://chambeapeapi-a4anbthqamgre7ce.eastus-01.azurewebsites.net/api/v1/users";
        List<Map<String, Object>> users = restTemplate.getForObject(url, List.class);

        List<String> userIds = new ArrayList<>();
        for (Map<String, Object> user : users) {
            userIds.add(user.get("id").toString());
        }

        return userIds;
    }

    @Override
    public String generateChatRoomId(String currentUserId, String otherUserId) throws NoSuchAlgorithmException {
        List<String> ids = new ArrayList<>();
        ids.add(currentUserId);
        ids.add(otherUserId);

        Collections.sort(ids);

        String combinedIds = ids.get(0) + "_" + ids.get(1);

        byte[] bytes = combinedIds.getBytes(StandardCharsets.UTF_8);

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(bytes);

        StringBuilder hexString = new StringBuilder();
        for (byte b : digest) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }
}

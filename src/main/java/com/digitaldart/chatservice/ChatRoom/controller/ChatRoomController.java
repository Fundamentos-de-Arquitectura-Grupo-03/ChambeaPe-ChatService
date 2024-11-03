package com.digitaldart.chatservice.ChatRoom.controller;

import com.digitaldart.chatservice.ChatRoom.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/chatroom")
public class ChatRoomController {
    @Autowired
    ChatRoomService chatRoomService;

    @GetMapping("/{userId}/users")
    public List<String> getExistingChatRoomsByUserId(@PathVariable String userId){
        return chatRoomService.getUsersIdByExistingChatroom(userId);
    }

}

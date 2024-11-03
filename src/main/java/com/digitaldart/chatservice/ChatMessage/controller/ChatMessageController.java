package com.digitaldart.chatservice.ChatMessage.controller;

import com.digitaldart.chatservice.ChatMessage.dto.ChatMessage;
import com.digitaldart.chatservice.ChatMessage.service.impl.ChatMessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatMessageController {
    @Autowired
    ChatMessageServiceImpl chatMessageService;

    //WEBSOCKETS
    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessage chat(@DestinationVariable String roomId, ChatMessage message){
        return chatMessageService.saveMessage(message, roomId);
    }

    //HTTP
    @GetMapping("/messages")
    public List<ChatMessage> getMessages(@RequestParam String roomId, @RequestParam(required = false) boolean latest){
        if(latest){
            return Collections.singletonList(chatMessageService.getLastMessageByRoomId(roomId));
        }
        else{
            return chatMessageService.getAllMessagesByRoomId(roomId);
        }
    }

}

package com.digitaldart.chatservice.ChatMessage.service.impl;

import com.digitaldart.chatservice.ChatMessage.dto.ChatMessage;
import com.digitaldart.chatservice.ChatMessage.model.ChatMessageModel;
import com.digitaldart.chatservice.ChatMessage.repository.ChatMessageRepository;
import com.digitaldart.chatservice.ChatMessage.service.ChatMessageService;
import com.digitaldart.chatservice.ChatRoom.model.ChatRoomModel;
import com.digitaldart.chatservice.ChatRoom.repository.ChatRoomRepository;
import com.digitaldart.chatservice.ChatRoom.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {
    @Autowired
    ChatMessageRepository chatMessageRepository;
    @Autowired
    ChatRoomService chatRoomService;
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Override
    public ChatMessage saveMessage(ChatMessage message, String roomId) {
        message.setTimestamp(getFormattedSentTimeMessage());
        ChatMessageModel savedMessage = chatMessageRepository.save(mapToModel(message, roomId));

        Optional<ChatRoomModel> chatRoomOptional = chatRoomService.getChatRoomById(roomId);
        ChatRoomModel chatRoom;

        chatRoom = chatRoomOptional.orElseGet(() -> ChatRoomModel.builder()
                .roomId(roomId)
                .messages(new ArrayList<>())
                .build());

        chatRoom.getMessages().add(savedMessage);
        chatRoomRepository.save(chatRoom);

        System.out.println(message);
        return message;
    }

    @Override
    public List<ChatMessage> getAllMessagesByRoomId(String roomId) {
        List<ChatMessageModel> messages = chatMessageRepository.getAllByChatroomId(roomId);
        return messages.stream().map((message)-> mapToDto(message)).toList();
    }

    @Override
    public ChatMessage getLastMessageByRoomId(String roomId) {
        Optional<ChatMessageModel> message = chatMessageRepository.findFirstByChatroomIdOrderByTimestampDesc(roomId);
        return message.isPresent() ?
                mapToDto(message.get()) :
                new ChatMessage("", "text", "0", LocalDateTime.now().toString());
    }

    private String getFormattedSentTimeMessage(){
        LocalDateTime timestamp = LocalDateTime.now();
        return timestamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    private ChatMessageModel mapToModel(ChatMessage message, String roomId){
        return ChatMessageModel.builder()
                .content(message.getContent())
                .chatroomId(roomId)
                .user(message.getUser())
                .type(message.getType())
                .timestamp(message.getTimestamp())
                .build();
    }

    private ChatMessage mapToDto(ChatMessageModel message){
        return ChatMessage.builder()
                .content(message.getContent())
                .type(message.getType())
                .user(message.getUser())
                .timestamp(message.getTimestamp())
                .build();
    }
}

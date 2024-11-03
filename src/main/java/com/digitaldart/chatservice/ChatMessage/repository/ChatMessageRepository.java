package com.digitaldart.chatservice.ChatMessage.repository;

import com.digitaldart.chatservice.ChatMessage.model.ChatMessageModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ChatMessageRepository extends MongoRepository<ChatMessageModel, ObjectId> {
    List<ChatMessageModel> getAllByChatroomId(String chatroomId);
    Optional<ChatMessageModel> findFirstByChatroomIdOrderByTimestampDesc(String chatroomId);
}

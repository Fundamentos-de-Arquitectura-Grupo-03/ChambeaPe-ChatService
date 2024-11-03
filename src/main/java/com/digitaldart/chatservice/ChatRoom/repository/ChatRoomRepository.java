package com.digitaldart.chatservice.ChatRoom.repository;

import com.digitaldart.chatservice.ChatRoom.model.ChatRoomModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoomModel, String> {
    Optional<ChatRoomModel> findByRoomId(String roomId);
    boolean existsByRoomId(String roomId);
}

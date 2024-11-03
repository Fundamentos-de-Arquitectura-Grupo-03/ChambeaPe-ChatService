package com.digitaldart.chatservice.ChatRoom.model;

import com.digitaldart.chatservice.ChatMessage.model.ChatMessageModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "room")
public class ChatRoomModel {
    @Id
    @Field("_id")
    private String roomId;

    @DBRef
    @Field("messages")
    private List<ChatMessageModel> messages;
}

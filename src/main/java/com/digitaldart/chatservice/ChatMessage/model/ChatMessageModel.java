package com.digitaldart.chatservice.ChatMessage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "message")
public class ChatMessageModel {
    @Id
    @Field("_id")
    private ObjectId id;

    @Field("chatroom_id")
    private String chatroomId;

    @Field("content")
    private String content;

    @Field("type")
    private String type;

    @Field("user_id")
    private String user;

    @Field("timestamp")
    private String timestamp;
}

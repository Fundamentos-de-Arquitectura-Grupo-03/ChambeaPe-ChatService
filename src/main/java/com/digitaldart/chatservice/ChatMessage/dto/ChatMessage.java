package com.digitaldart.chatservice.ChatMessage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    String content;
    String type;
    String user;
    String timestamp;
}

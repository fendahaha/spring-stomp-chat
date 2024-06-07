package com.example.springstompchat.controller;

import com.example.springstompchat.entity.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    private SimpMessagingTemplate template;

    @Autowired
    public MessageController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/room/{id}")
    public ChatMessage handle(ChatMessage msg, @DestinationVariable String id) {
        ChatMessage chatMessage = new ChatMessage(msg.user, msg.text);
        this.template.convertAndSend("/topic/room/" + id, chatMessage);
        return null;
    }
}

package com.dievintodev.websocket.controller;

import com.dievintodev.websocket.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController {


    @Autowired
    SimpMessagingTemplate messagingTemplate;

    /* group */
    @MessageMapping("/application")
    @SendTo("/all/messages")
    public Message sendMessage(@Payload Message message){
        return message;
    }


    // Mapped as /app/private
    @MessageMapping("/private")
    public void sendToSpecificUser(@Payload Message message) {
        messagingTemplate.convertAndSendToUser(message.getTo(), "/specific", message);
    }

}

package com.plataforma.portafolios.controller;

import com.plataforma.portafolios.dto.ChatMessage;
import com.plataforma.portafolios.model.Message;
import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.model.User;
import com.plataforma.portafolios.service.IChatService;
import com.plataforma.portafolios.service.IProfileService;
import com.plataforma.portafolios.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class WebSocketController {

    @Autowired
    private IProfileService profileServ;

    @Autowired
    private IChatService chatService;

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message chat(ChatMessage message) {
        Profile sender = profileServ.getEntity(message.getSenderProfileId());
        Profile receiver = profileServ.getEntity(message.getReceiverProfileId());
        Message newMessage = null;
        if(sender != null && receiver != null){
            newMessage = new Message(null, LocalDateTime.now(),sender,receiver,message.getContent(),null);
            chatService.saveChat(newMessage,sender,receiver);
        }
        return newMessage;
    }
}

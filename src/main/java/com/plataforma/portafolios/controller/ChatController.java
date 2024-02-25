package com.plataforma.portafolios.controller;

import com.plataforma.portafolios.model.Chat;
import com.plataforma.portafolios.model.Message;
import com.plataforma.portafolios.service.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@CrossOrigin("http://localhost:4200/")
public class ChatController {

    @Autowired
    private IChatService chatServ;
    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<Chat>>getProfileChats(@PathVariable Long profileId){
        List<Chat> chats = chatServ.getProfileChats(profileId);
        if(chats != null){
            return ResponseEntity.ok(chats);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sender/{senderId}/receiver/{receiverId}")
    public ResponseEntity<List<Message>> getChatMessages(){

    }
}

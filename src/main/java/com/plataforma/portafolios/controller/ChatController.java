package com.plataforma.portafolios.controller;

import com.plataforma.portafolios.model.Chat;
import com.plataforma.portafolios.model.Message;
import com.plataforma.portafolios.repository.IMessageRepository;
import com.plataforma.portafolios.service.IChatService;
import com.plataforma.portafolios.service.IMessageService;
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

    @Autowired
    private IMessageService messageServ;
    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<Chat>>getProfileChats(@PathVariable Long profileId){
        List<Chat> chats = chatServ.getProfileChats(profileId);
        if(chats != null){
            return ResponseEntity.ok(chats);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/profile1/{profileId1}/profile2/{profileId2}")
    public ResponseEntity<List<Message>> getChatMessages(@PathVariable Long profileId1,@PathVariable Long profileId2){
        return ResponseEntity.ok(messageServ.findMessagesByChat(profileId1,profileId2));
    }
}

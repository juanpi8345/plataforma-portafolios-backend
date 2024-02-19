package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.Chat;
import com.plataforma.portafolios.model.Message;
import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.repository.IChatRepository;
import com.plataforma.portafolios.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService implements IChatService {
    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private IChatRepository chatRepo;

    @Override
    public void saveChat(Message message, Profile profile1, Profile profile2) {
        if (profile1 != null && profile2 != null) {
            Chat chat = chatRepo.findByFirstUsernameAndSecondUsername(profile1.getName(), profile2.getName());
            if (chat == null) {
                chat = new Chat();
                chat.setFirstUsername(profile1.getName());
                chat.setSecondUsername(profile2.getName());
                List<Message> messages = new ArrayList<>();
                messages.add(message);
                chat.setMessageList(messages);
            } else {
                chat.getMessageList().add(message);
            }
            chatRepo.save(chat);
        }
    }
}

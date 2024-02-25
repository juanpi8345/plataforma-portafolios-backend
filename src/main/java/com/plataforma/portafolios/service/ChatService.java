package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.Chat;
import com.plataforma.portafolios.model.Message;
import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.repository.IChatRepository;
import com.plataforma.portafolios.repository.IProfileRepository;
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

    @Autowired
    private IProfileRepository profileRepo;

    @Override
    public void saveChat(Message message, Profile profile1, Profile profile2) {
        if (profile1 != null && profile2 != null) {
            Chat chat = chatRepo.findByProfile1AndProfile2(profile1, profile2);
            if (chat == null) {
                chat = new Chat();
                chat.setProfile1(profile1);
                chat.setProfile2(profile2);
                List<Message> messages = new ArrayList<>();
                message.setChat(chat);
                messages.add(message);
                chat.setMessageList(messages);
            } else {
                message.setChat(chat);
                chat.getMessageList().add(message);
            }
            chatRepo.save(chat);
        }
    }

    @Override
    public List<Chat> getProfileChats(Long profileId) {
        Profile profile = profileRepo.findById(profileId).orElse(null);
        if(profile!= null){
            return chatRepo.findChatsOfProfile(profile);
        }
        return null;

    }
}

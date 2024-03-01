package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.Message;
import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.repository.IChatRepository;
import com.plataforma.portafolios.repository.IMessageRepository;
import com.plataforma.portafolios.repository.IProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService implements IMessageService{

    @Autowired
    private IChatRepository chatRepo;

    @Autowired
    private IMessageRepository messageRepo;

    @Autowired
    private IProfileRepository profileRepo;

    @Override
    public List<Message> findMessagesByChat(Long profileId1, Long profileId2) {
        Profile profile1 = profileRepo.findById(profileId1).orElse(null);
        Profile profile2 = profileRepo.findById(profileId2).orElse(null);
        if(profile1 != null && profile2 != null){
            return messageRepo.findChatMessages(profile1,profile2);
        }

        return null;
    }
}

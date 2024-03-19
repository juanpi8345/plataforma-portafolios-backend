package com.plataforma.portafolios.service;

import com.plataforma.portafolios.exceptions.EntitiesNotFoundException;
import com.plataforma.portafolios.exceptions.EntityNotFoundException;
import com.plataforma.portafolios.model.Message;
import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.repository.IChatRepository;
import com.plataforma.portafolios.repository.IMessageRepository;
import com.plataforma.portafolios.repository.IProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService implements IMessageService{

    @Autowired
    private IChatRepository chatRepo;

    @Autowired
    private IMessageRepository messageRepo;

    @Autowired
    private IProfileRepository profileRepo;

    @Override
    public List<Message> findMessagesByChat(Long profileId1, Long profileId2) throws EntityNotFoundException,
            EntitiesNotFoundException {
        Optional<Profile> profile1 = profileRepo.findById(profileId1);
        Optional<Profile> profile2 = profileRepo.findById(profileId2);
        if(!profile1.isPresent()|| !profile2.isPresent())
            throw new EntityNotFoundException("Profile not available");
        List<Message> messages = messageRepo.findChatMessages(profile1.get(),profile2.get());
        if(messages.isEmpty())
            throw new EntitiesNotFoundException("There are not messages in this chat");

        return messages;
    }
}

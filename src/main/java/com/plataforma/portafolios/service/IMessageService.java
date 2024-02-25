package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.Message;

import java.util.List;

public interface IMessageService {

    // i dont search messages by chatid due to the convenience of the frontend
    List<Message> findMessagesByChat(Long profileId1, Long profileId2);
}

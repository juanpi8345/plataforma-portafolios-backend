package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.Message;
import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.model.User;

import java.util.List;

public interface IChatService {
    void saveChat(Message message, Profile profile1, Profile profile2);
}

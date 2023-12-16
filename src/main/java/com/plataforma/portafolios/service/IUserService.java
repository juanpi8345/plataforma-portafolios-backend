package com.plataforma.portafolios.service;

import com.plataforma.portafolios.dto.UserDTO;
import com.plataforma.portafolios.model.User;

public interface IUserService {
    void saveUser(User user);

    User getUserById(Long id);

    User getUser(String username);

    User getUserByEmail(String email);
}

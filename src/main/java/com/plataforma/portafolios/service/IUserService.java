package com.plataforma.portafolios.service;

import com.plataforma.portafolios.dto.UserDTO;
import com.plataforma.portafolios.model.User;

import java.security.Principal;

public interface IUserService {
    void saveUser(User user);

    User getUserById(Long id);

    User getUser(String username);

    User getLogedUser(Principal principal);

    User getUserByEmail(String email);
}

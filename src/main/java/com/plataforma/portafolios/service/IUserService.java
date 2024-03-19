package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.User;

import java.security.Principal;

public interface IUserService {
    User getLoggedUser(Principal principal);

}

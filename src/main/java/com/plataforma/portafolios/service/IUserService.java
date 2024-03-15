package com.plataforma.portafolios.service;

import com.plataforma.portafolios.dto.UserDTO;
import com.plataforma.portafolios.model.Skill;
import com.plataforma.portafolios.model.User;
import com.plataforma.portafolios.util.IGenericCrud;

import java.security.Principal;
import java.util.List;

public interface IUserService {
    User getLogedUser(Principal principal);

}

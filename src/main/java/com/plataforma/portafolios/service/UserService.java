package com.plataforma.portafolios.service;

import com.plataforma.portafolios.config.security.SecurityBeansInjector;
import com.plataforma.portafolios.dto.UserDTO;
import com.plataforma.portafolios.exceptions.UserNotLoggedException;
import com.plataforma.portafolios.model.Skill;
import com.plataforma.portafolios.model.User;
import com.plataforma.portafolios.repository.IUserRepository;
import com.plataforma.portafolios.util.Role;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getLoggedUser(Principal principal) throws UserNotLoggedException {
        User user = userRepo.findByUsername(principal.getName());
        if(user == null)
            throw new UserNotLoggedException("User not logged");
        return user;
    }
}

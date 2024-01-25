package com.plataforma.portafolios.service;

import com.plataforma.portafolios.dto.AuthenticationRequest;
import com.plataforma.portafolios.dto.AuthenticationResponse;
import com.plataforma.portafolios.dto.UserDTO;
import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.model.User;
import com.plataforma.portafolios.repository.IUserRepository;
import com.plataforma.portafolios.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUsername());
        String token=jwtService.getToken(user);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse register(UserDTO user) {
        User userToRegister = null;
        if (userRepository.findByUsername(user.getUsername()) == null) {
            userToRegister = new User();
            userToRegister.setUsername(user.getUsername());
            userToRegister.setEmail(user.getEmail());
            userToRegister.setPassword(passwordEncoder.encode(user.getPassword()));
            if (user.getRole() == Role.EMPLOYEE) {
                userToRegister.setRole(Role.EMPLOYEE);
                Profile pr = new Profile();
                pr.setName(user.getUsername());
                userToRegister.setProfile(pr);
            }
            else
                userToRegister.setRole(Role.EMPLOYEER);
            userRepository.save(userToRegister);
        }
        if(userToRegister!= null)
            return new AuthenticationResponse(jwtService.getToken(userToRegister));
        else
            return null;
    }
}

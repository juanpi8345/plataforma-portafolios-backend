package com.plataforma.portafolios.controller;


import com.plataforma.portafolios.dto.AuthenticationRequest;
import com.plataforma.portafolios.dto.AuthenticationResponse;
import com.plataforma.portafolios.dto.UserDTO;
import com.plataforma.portafolios.model.User;
import com.plataforma.portafolios.repository.IUserRepository;
import com.plataforma.portafolios.service.AuthenticationService;
import com.plataforma.portafolios.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:4200/")

public class AuthController {
    @Autowired
    private AuthenticationService authService;
    @Autowired
    private IUserService userServ;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDTO user){
        if(authService.register(user) != null)
            return ResponseEntity.ok(authService.register(user));
        return ResponseEntity.status(409).build();
    }
    @GetMapping("/get")
    public User getLogedUser(Principal principal){
        return userServ.getLogedUser(principal);
    }
}

package com.plataforma.portafolios.controller;


import com.plataforma.portafolios.dto.AuthenticationRequest;
import com.plataforma.portafolios.dto.AuthenticationResponse;
import com.plataforma.portafolios.dto.UserDTO;
import com.plataforma.portafolios.exceptions.BadCredentialsException;
import com.plataforma.portafolios.exceptions.EntityAlreadyExists;
import com.plataforma.portafolios.exceptions.EntityNotFoundException;
import com.plataforma.portafolios.exceptions.InvalidEntityException;
import com.plataforma.portafolios.model.User;
import com.plataforma.portafolios.repository.IUserRepository;
import com.plataforma.portafolios.service.AuthenticationService;
import com.plataforma.portafolios.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request
                                                        , BindingResult bindingResult)
            throws EntityNotFoundException, BadCredentialsException, InvalidEntityException {
        if(bindingResult.hasErrors())
            throw new InvalidEntityException("Incomplete fields");
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserDTO user, BindingResult bindingResult
    ) throws EntityAlreadyExists,InvalidEntityException {
        if(bindingResult.hasErrors())
            throw new InvalidEntityException("Incomplete or invalid fields");
        authService.register(user);
        return ResponseEntity.ok().body("User has been registered!");
    }
    @GetMapping("/get")
    public ResponseEntity<User> getLoggedUser(Principal principal){
        return ResponseEntity.ok(userServ.getLoggedUser(principal));
    }
}

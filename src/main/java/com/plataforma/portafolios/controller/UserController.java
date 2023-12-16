package com.plataforma.portafolios.controller;

import com.plataforma.portafolios.model.User;
import com.plataforma.portafolios.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userServ;
    @GetMapping("/get/{userId}")
    public ResponseEntity<User> getUser(@PathVariable  Long userId){
        User user = userServ.getUserById(userId);
        if(user != null){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
}

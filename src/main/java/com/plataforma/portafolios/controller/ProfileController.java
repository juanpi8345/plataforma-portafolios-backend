package com.plataforma.portafolios.controller;

import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private IUserService userServ;

    @PutMapping("/edit/user/{userId}")
    public ResponseEntity<Profile> editProfile(@RequestBody Profile profile){
        return null;
    }
}

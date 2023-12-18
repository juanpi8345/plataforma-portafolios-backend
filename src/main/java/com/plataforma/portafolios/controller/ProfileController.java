package com.plataforma.portafolios.controller;

import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.model.Project;
import com.plataforma.portafolios.model.Skill;
import com.plataforma.portafolios.model.User;
import com.plataforma.portafolios.service.IProfileService;
import com.plataforma.portafolios.service.IProjectService;
import com.plataforma.portafolios.service.ISkillService;
import com.plataforma.portafolios.service.IUserService;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/profile")
@Validated
public class ProfileController {
    @Autowired
    private IUserService userServ;

    @Autowired
    private IProfileService profileServ;

    @PostMapping("/create/user/{userId}")
    public ResponseEntity<Profile> createProfile(@PathVariable Long userId){
        User user = userServ.getUserById(userId);
        if(user != null){
            Profile pr = new Profile();
            pr.setName(user.getUsername());
            user.setProfile(pr);
            userServ.saveUser(user);
            return ResponseEntity.ok(pr);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add/image/profile/{profileId}")
    public ResponseEntity<String> uploadImage(@PathVariable Long profileId, @RequestParam MultipartFile imageFile){
        profileServ.uploadImage(profileId,imageFile);
        return ResponseEntity.ok("Image uploaded");
    }


}

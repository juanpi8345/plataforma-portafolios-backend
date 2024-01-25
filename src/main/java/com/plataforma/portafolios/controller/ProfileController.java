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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/profile")
@CrossOrigin("http://localhost:4200/")
@Validated
public class ProfileController {
    @Autowired
    private IUserService userServ;
    @Autowired
    private IProfileService profileServ;

    @GetMapping("/get/image")
    public ResponseEntity<byte[]> getProfileImage(Principal principal) {
        Profile profile = userServ.getLogedUser(principal).getProfile();

        if (profile == null || profile.getImage() == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(profile.getImage());
    }

    @PostMapping("/add/image")
    public ResponseEntity<?> uploadImage(Principal principal, @RequestParam MultipartFile image) throws IOException {
        Profile profile = userServ.getLogedUser(principal).getProfile();

        if (profile == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el perfil asociado al usuario.");
        if (image.isEmpty())
            return ResponseEntity.badRequest().body("La imagen está vacía.");

        String contentType = image.getContentType();
        if (contentType == null || !contentType.startsWith("image")) {
            return ResponseEntity.badRequest().body("El archivo no es una imagen válida.");
        }
        profile.setImage(image.getBytes());
        profileServ.saveProfile(profile);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit/name")
    public ResponseEntity<?> editName(Principal principal, @RequestParam String name){
        Profile profile = userServ.getLogedUser(principal).getProfile();
        if(profile != null){
            profile.setName(name);
            profileServ.saveProfile(profile);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/edit/occupation")
    public ResponseEntity<?> editOccupation(Principal principal, @RequestParam String occupations){
        Profile profile = userServ.getLogedUser(principal).getProfile();
        if(profile != null){
            profile.setOccupations(occupations);
            profileServ.saveProfile(profile);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/edit/description")
    public ResponseEntity<?> editDescription(Principal principal, @RequestParam String description){
        Profile profile = userServ.getLogedUser(principal).getProfile();
        if(profile != null){
            profile.setDescription(description);
            profileServ.saveProfile(profile);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }




}

package com.plataforma.portafolios.controller;

import com.plataforma.portafolios.util.Profile;
import com.plataforma.portafolios.model.Skill;
import com.plataforma.portafolios.service.IProfileService;
import com.plataforma.portafolios.service.ISkillService;
import com.plataforma.portafolios.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private ISkillService skillServ;

    @GetMapping("/get/image")
    public ResponseEntity<byte[]> getProfileImage(Principal principal) {
        Profile profile = userServ.getLogedUser(principal).getProfile();

        if (profile == null || profile.getImage() == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(profile.getImage());
    }

    @GetMapping("/get/employers")
    public ResponseEntity<Page<Profile>> getEmployersBySkills(@RequestParam List<String> skillsStr, @RequestParam(name = "page", defaultValue = "0") int page){
        List<Skill> skills = new ArrayList<Skill>();
        for(String skill : skillsStr){
            Skill skillFound = skillServ.getSkillByTitle(skill);
            if(skillFound != null)
                skills.add(skillFound);
        }
        return ResponseEntity.ok(profileServ.findBySkillsIn(skills,page,10));
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

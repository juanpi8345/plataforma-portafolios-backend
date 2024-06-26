package com.plataforma.portafolios.controller;

import com.plataforma.portafolios.exceptions.EntitiesNotFoundException;
import com.plataforma.portafolios.exceptions.EntityNotFoundException;
import com.plataforma.portafolios.exceptions.UserNotLoggedException;
import com.plataforma.portafolios.model.Employee;
import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.model.Project;
import com.plataforma.portafolios.service.IProfileService;
import com.plataforma.portafolios.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/profile")
@CrossOrigin("http://localhost:4200/")
@Valid
public class ProfileController {
    @Autowired
    private IUserService userServ;

    @Autowired
    private IProfileService profileServ;

    @GetMapping("/get/{profileId}")
    public ResponseEntity<Profile> getProfile(@PathVariable Long profileId) throws EntityNotFoundException {
        return ResponseEntity.ok(profileServ.getEntity(profileId));
    }

    @GetMapping("/get/image")
    public ResponseEntity<byte[]> getProfileImage(Principal principal) throws UserNotLoggedException {
        Profile profile = userServ.getLoggedUser(principal).getProfile();
        if (profile.getImage() == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(profile.getImage());
    }


    @GetMapping("/{profileId}/get/image")
    public ResponseEntity<byte[]> getProfileImage(@PathVariable Long profileId) throws EntityNotFoundException {
        Profile profile = profileServ.getEntity(profileId);
        if (profile == null || profile.getImage() == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(profile.getImage());
    }

    @GetMapping("/get/recommended")
    public <E> ResponseEntity<List<E>> getRecommendedProfiles(Principal principal) throws EntitiesNotFoundException, UserNotLoggedException {
        Profile profile = userServ.getLoggedUser(principal).getProfile();
        return ResponseEntity.ok(profileServ.getRecommendedProfiles(profile));
    }

    @PutMapping("/edit/occupation")
    public ResponseEntity<?> editOccupation(Principal principal, @RequestParam String occupations) throws UserNotLoggedException {
        Profile profile = userServ.getLoggedUser(principal).getProfile();
        profile.setOccupations(occupations);
        profileServ.saveEntity(profile);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add/image")
    public ResponseEntity<?> uploadImage(Principal principal, @RequestParam MultipartFile file) throws IOException, EntityNotFoundException, UserNotLoggedException {
        Profile profile = userServ.getLoggedUser(principal).getProfile();
        if (file.isEmpty())
            return ResponseEntity.badRequest().body("Image is empty.");

        profileServ.uploadImage(profile.getProfileId(),file);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit/name")
    public ResponseEntity<?> editName(Principal principal, @RequestParam String name) throws UserNotLoggedException {
        Profile profile = userServ.getLoggedUser(principal).getProfile();
        profile.setName(name);
        profileServ.saveEntity(profile);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit/description")
    public ResponseEntity<?> editDescription(Principal principal, @RequestParam String description) throws UserNotLoggedException {
        Profile profile = userServ.getLoggedUser(principal).getProfile();
        profile.setDescription(description);
        profileServ.saveEntity(profile);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/image")
    public ResponseEntity<?> deleteImage(Principal principal) throws IOException, EntityNotFoundException, UserNotLoggedException {
        Profile profile = userServ.getLoggedUser(principal).getProfile();
        profileServ.deleteImage(profile);
        return ResponseEntity.ok().build();
    }

}

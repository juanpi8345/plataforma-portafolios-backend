package com.plataforma.portafolios.controller;

import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.model.Skill;
import com.plataforma.portafolios.service.IProfileService;
import com.plataforma.portafolios.service.ISkillService;
import com.plataforma.portafolios.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@Validated
@RequestMapping("/skills")
@CrossOrigin("http://localhost:4200/")
public class SkillController {
    @Autowired
    private IProfileService profileServ;
    @Autowired
    private ISkillService skillServ;

    @Autowired
    private IUserService userServ;

    @GetMapping("/getAll")
    public ResponseEntity<List<Skill>> getAllSkills(Principal principal){
        Profile profile = userServ.getLogedUser(principal).getProfile();
        if(profile != null)
            return ResponseEntity.ok(skillServ.getAll());
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/get")
    public ResponseEntity<List<Skill>> getProfileSkills(Principal principal){
        Profile profile = userServ.getLogedUser(principal).getProfile();
        if(profile != null)
            return ResponseEntity.ok(profile.getSkills());
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Skill>> searchSkills(Principal principal, @RequestParam String query){
        Profile profile = userServ.getLogedUser(principal).getProfile();
        if(profile != null)
            return ResponseEntity.ok(skillServ.getSkillContaining(query));
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Skill> addSkill(@Valid @RequestParam String title, Principal principal){
        Profile pr = userServ.getLogedUser(principal).getProfile();
        Skill sk = skillServ.getSkillByTitle(title);
        if(pr != null && sk != null) {
            if(!pr.getSkills().contains(sk)){
                pr.getSkills().add(sk);
                sk.getProfiles().add(pr);
                profileServ.saveProfile(pr);
            }
            return ResponseEntity.ok(sk);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/edit")
    public ResponseEntity<Skill> editSkill(@Valid @RequestBody Skill skillRequest, Principal principal){
        Profile profile = userServ.getLogedUser(principal).getProfile();
        Skill skill = skillServ.getSkill(skillRequest.getSkillId());
        if(skill!= null){
            skill.setTitle(skillRequest.getTitle());
            skill.setImage(skillRequest.getImage());
            skillServ.saveSkill(skill,profile.getProfileId());
            return ResponseEntity.ok(skill);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{skillId}")
    public ResponseEntity<?> deleteSkill(@PathVariable Long skillId, Principal principal){
        Profile profile = userServ.getLogedUser(principal).getProfile();
        Skill skill = skillServ.getSkill(skillId);
        if(profile != null && skill!=null){
            profile.getSkills().remove(skill);
            skill.getProfiles().remove(profile);
            profileServ.saveProfile(profile);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

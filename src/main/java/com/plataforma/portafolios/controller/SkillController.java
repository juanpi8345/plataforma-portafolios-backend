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
public class SkillController {
    @Autowired
    private IProfileService profileServ;
    @Autowired
    private ISkillService skillServ;

    @Autowired
    private IUserService userServ;

    @GetMapping("/get")
    public ResponseEntity<List<Skill>> getProfileSkills(Principal principal){
        Profile profile = userServ.getLogedUser(principal).getProfile();
        if(profile != null)
            return ResponseEntity.ok(profile.getSkills());
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Skill> addSkill(@Valid @RequestBody Skill skill, Principal principal){
        Profile pr = userServ.getLogedUser(principal).getProfile();
        boolean exists = false;
        if(skillServ.getSkillByTitle(skill.getTitle()) != null)
            exists = true;
        if(pr != null){
            pr.getSkills().add(skill);
            skill.getProfiles().add(pr);
            if(!exists)
                profileServ.saveProfile(pr);
            return ResponseEntity.ok(skill);
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

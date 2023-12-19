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

import java.util.List;

@RestController
@Validated
@RequestMapping("/skills")
public class SkillController {
    @Autowired
    private IProfileService profileServ;
    @Autowired
    private ISkillService skillServ;

    @GetMapping("/get/profile/{profileId}")
    public ResponseEntity<List<Skill>> getProfileSkills(@PathVariable Long profileId){
        Profile profile = profileServ.getProfile(profileId);
        if(profile != null)
            return ResponseEntity.ok(profile.getSkills());
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add/profile/{profileId}")
    public ResponseEntity<Skill> addSkill(@Valid @RequestBody Skill skill, @PathVariable Long profileId){
        Profile pr = profileServ.getProfile(profileId);
        if(pr != null){
            pr.getSkills().add(skill);
            skill.getProfiles().add(pr);
            profileServ.saveProfile(pr);
            return ResponseEntity.ok(skill);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/edit/profile/{profileId}")
    public ResponseEntity<Skill> editSkill(@PathVariable Long profileId, @Valid @RequestBody Skill skillRequest){
        Profile profile = profileServ.getProfile(profileId);
        Skill skill = skillServ.getSkill(skillRequest.getSkillId());
        if(skill!= null){
            skill.setTitle(skillRequest.getTitle());
            skill.setImage(skillRequest.getImage());
            skillServ.saveSkill(skill,profileId);
            return ResponseEntity.ok(skill);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{skillId}/profile/{profileId}")
    public ResponseEntity<?> deleteSkill(@PathVariable Long skillId, @PathVariable Long profileId){
        Profile profile = profileServ.getProfile(profileId);
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

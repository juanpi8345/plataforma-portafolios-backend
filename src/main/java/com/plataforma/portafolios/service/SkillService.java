package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.model.Skill;
import com.plataforma.portafolios.repository.IProfileRepository;
import com.plataforma.portafolios.repository.ISkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService implements ISkillService {

    @Autowired
    private ISkillRepository skillRepo;

    @Autowired
    private IProfileRepository profileRepo;

    @Override
    public void saveSkill(Skill skill, Long profileId) {
        Profile profile = profileRepo.findById(profileId).orElse(null);
        if (profile != null) {
            Skill existingSkill = skillRepo.findByTitle(skill.getTitle());
            if (existingSkill == null)
                skillRepo.save(skill);
            else
                skill = existingSkill;
            skill.getProfiles().add(profile);
            skillRepo.save(skill);
            profile.getSkills().add(skill);
            profileRepo.save(profile);
        }
    }

    @Override
    public void deleteSkill(Long skillId) {
        skillRepo.deleteById(skillId);
    }

    @Override
    public Skill getSkill(Long skillId) {
        return skillRepo.findById(skillId).orElse(null);
    }

    @Override
    public Skill getSkillByTitle(String title) {
        return skillRepo.findByTitle(title);
    }

    @Override
    public void editSkill(Skill skillRequest) {
        Skill skill = skillRepo.findById(skillRequest.getSkillId()).orElse(null);
        if(skill != null){
            skill.setImage(skillRequest.getImage());
            skill.setTitle(skillRequest.getTitle());
            skillRepo.save(skill);
        }
    }
}

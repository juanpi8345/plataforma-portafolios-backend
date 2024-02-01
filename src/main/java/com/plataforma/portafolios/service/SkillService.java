package com.plataforma.portafolios.service;

import com.plataforma.portafolios.util.Profile;
import com.plataforma.portafolios.model.Skill;
import com.plataforma.portafolios.repository.IProfileRepository;
import com.plataforma.portafolios.repository.ISkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService implements ISkillService {

    @Autowired
    private ISkillRepository skillRepo;

    @Autowired
    private IProfileRepository profileRepo;

    @Override
    public void saveSkill(Skill skill, Long profileId) {
        Profile profile = profileRepo.findById(profileId).orElse(null);
        Skill skill1 = skillRepo.findByTitle(skill.getTitle());
        if (profile != null && skill!=null) {
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
    public List<Skill> getSkillContaining(String query) {
        return skillRepo.findByTitleContaining(query);
    }

    @Override
    public Skill getSkill(Long skillId) {
        return skillRepo.findById(skillId).orElse(null);
    }

    @Override
    public List<Skill> getAll() {
        return skillRepo.findAll();
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

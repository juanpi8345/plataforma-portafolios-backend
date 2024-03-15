package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.Employee;
import com.plataforma.portafolios.model.Employer;
import com.plataforma.portafolios.repository.IEmployeeRepository;
import com.plataforma.portafolios.repository.IEmployerRepository;
import com.plataforma.portafolios.repository.IProfileRepository;
import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.model.Skill;
import com.plataforma.portafolios.repository.ISkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SkillService implements ISkillService {

    @Autowired
    private ISkillRepository skillRepo;
    @Autowired
    private IProfileRepository profileRepo;
    @Autowired
    private IEmployerRepository employerRepo;

    @Autowired
    private IEmployeeRepository employeeRepo;

    @Override
    public List<Skill> getSkillContaining(String query) {
        return skillRepo.findByTitleContaining(query);
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> skills = skillRepo.findAll();
        skills.sort((s1, s2) -> s1.getTitle().compareToIgnoreCase(s2.getTitle()));
        return skills;
    }

    @Override
    public Skill getSkillByTitle(String title) {
        return skillRepo.findByTitle(title);
    }

    @Override
    public Skill getEntity(Long skillId) {
        return skillRepo.findById(skillId).orElse(null);
    }

    @Override
    public void saveEntity(Skill skill) {
        Skill existingSkill = skillRepo.findByTitle(skill.getTitle());
        if(existingSkill!=null)
            skillRepo.save(skill);
    }

    @Override
    public void deleteEntity(Long skillId) {
        skillRepo.deleteById(skillId);
    }

    @Override
    public void editEntity(Skill skillRequest) {
        Skill skill = skillRepo.findById(skillRequest.getSkillId()).orElse(null);
        if(skill != null){
            skill.setImage(skillRequest.getImage());
            skill.setTitle(skillRequest.getTitle());
            skillRepo.save(skill);
        }
    }
}

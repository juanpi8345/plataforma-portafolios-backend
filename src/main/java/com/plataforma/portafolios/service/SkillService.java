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
    public void saveSkill(Skill skill, Long profileId) {
        Profile profile = profileRepo.findById(profileId).orElse(null);
        Skill existingSkill = skillRepo.findByTitle(skill.getTitle());
        if(profile instanceof Employee employee && profile != null && existingSkill!=null){
            skill.getEmployees().add(employee);
            skillRepo.save(skill);
            employee.getSkills().add(skill);
            employeeRepo.save(employee);
        }
        else if(profile instanceof Employer employer && profile != null && existingSkill!=null){
            skill.getEmployers().add(employer);
            skillRepo.save(skill);
            employer.getSkillsSearched().add(skill);
            employerRepo.save(employer);
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

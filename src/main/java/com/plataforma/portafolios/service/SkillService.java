package com.plataforma.portafolios.service;

import com.plataforma.portafolios.exceptions.EntitiesNotFoundException;
import com.plataforma.portafolios.exceptions.EntityNotFoundException;
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
import java.util.Optional;

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
    public List<Skill> getAll() throws EntitiesNotFoundException {
        List<Skill> skills = skillRepo.findAll();
        if(skills.isEmpty())
            throw new EntitiesNotFoundException("There are not skills to show");
        skills.sort((s1, s2) -> s1.getTitle().compareToIgnoreCase(s2.getTitle()));
        return skills;
    }

    @Override
    public Skill getSkillByTitle(String title) throws EntityNotFoundException {
        Skill skill = skillRepo.findByTitle(title);
        if(skill == null)
            throw new EntityNotFoundException("Skill with title '"+title+"' does not exist");
        return skill;
    }

    @Override
    public Skill getEntity(Long skillId) throws EntityNotFoundException {
        Optional<Skill> skill = skillRepo.findById(skillId);
        if(!skill.isPresent())
            throw new EntityNotFoundException("Skill with id: "+skill.get().getSkillId()+" does not exist");
        return skill.get();
    }

    @Override
    public void saveEntity(Skill skill) {
        Skill existingSkill = skillRepo.findByTitle(skill.getTitle());
        if(existingSkill!=null)
            skillRepo.save(skill);
    }

    @Override
    public void deleteEntity(Long skillId) throws EntityNotFoundException {
        Optional<Skill> skill = skillRepo.findById(skillId);
        if(!skill.isPresent())
            throw new EntityNotFoundException("Skill with id: "+skill.get().getSkillId()+" does not exist");
        skillRepo.deleteById(skillId);
    }

    @Override
    public void editEntity(Skill skillRequest) throws EntityNotFoundException {
        Optional<Skill> skill = skillRepo.findById(skillRequest.getSkillId());
        if(!skill.isPresent())
            throw new EntityNotFoundException("Skill with id: "+skill.get().getSkillId()+" does not exist");

        skill.get().setImage(skillRequest.getImage());
        skill.get().setTitle(skillRequest.getTitle());
        skillRepo.save(skill.get());

    }
}

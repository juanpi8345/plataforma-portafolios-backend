package com.plataforma.portafolios.service;

import com.plataforma.portafolios.exceptions.EntitiesNotFoundException;
import com.plataforma.portafolios.model.Employee;
import com.plataforma.portafolios.model.Employer;
import com.plataforma.portafolios.model.Skill;
import com.plataforma.portafolios.repository.IEmployerRepository;
import com.plataforma.portafolios.util.IGenericProfileSkills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerService {
    @Autowired
    private IEmployerRepository employerRepo;

    public Page<Employer> findBySkillsIn(List<Skill> skills, int page, int size) throws EntitiesNotFoundException {
        Page<Employer> employers = this.searchSkills.findBySkillsIn(skills,page,size);
        if(employers.isEmpty())
            throw new EntitiesNotFoundException("Employers are not available");
        return employers;
    }

    IGenericProfileSkills<Employer> searchSkills = (List<Skill> skills, int page, int size) -> {
        Pageable pageable = PageRequest.of(page, size);
        return employerRepo.findBySearchedSkills(skills,pageable);
    };



}

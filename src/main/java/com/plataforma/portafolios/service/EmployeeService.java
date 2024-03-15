package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.Employee;
import com.plataforma.portafolios.model.Skill;
import com.plataforma.portafolios.repository.IEmployeeRepository;
import com.plataforma.portafolios.util.IGenericProfileSkills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private IEmployeeRepository employeeRepo;

    public Page<Employee> findBySkillsIn(List<Skill> skills, int page, int size){
        return this.searchSkills.findBySkillsIn(skills,page,size);
    }

    public IGenericProfileSkills<Employee> searchSkills = (List<Skill> skills, int page, int size) ->{
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepo.findBySkills(skills,pageable);
    };


}

package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.Employee;
import com.plataforma.portafolios.model.Skill;
import com.plataforma.portafolios.repository.IEmployeeRepository;
import com.plataforma.portafolios.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class EmployeeService implements  IEmployeeService{
    @Autowired
    private IEmployeeRepository employeeRepo;

    @Override
    public Page<Employee> findBySkillsIn(List<Skill> skills, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepo.findBySkills(skills,pageable);
    }


}

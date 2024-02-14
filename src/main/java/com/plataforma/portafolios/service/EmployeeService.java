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

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private ISkillService skillServ;

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepo.save(employee);
    }

    @Override
    public void editEmployee(Employee employee) {
        Employee em = employeeRepo.findById(employee.getProfileId()).orElse(null);
        if(em != null){
            em.setImage(employee.getImage());
            em.setName(employee.getName());
            em.setSkills(employee.getSkills());
            em.setProjects(employee.getProjects());
            employeeRepo.save(employee);
        }
    }

    @Override
    public Employee getEmployee(Long profileId) {
        return employeeRepo.findById(profileId).orElse(null);
    }

    @Override
    public Page<Employee> findBySkillsIn(List<Skill> skills, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        return employeeRepo.findBySkills(skills,pageable);
    }

    public void uploadImage(Long profileId, MultipartFile imageFile) {
        Employee employee = employeeRepo.findById(profileId).orElse(null);
        if (employee != null) {
            try {
                byte[] imageData = imageFile.getBytes();
                employee.setImage(imageData);
                employeeRepo.save(employee);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

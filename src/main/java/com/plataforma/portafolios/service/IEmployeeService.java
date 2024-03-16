package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.Employee;
import com.plataforma.portafolios.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IEmployeeService {

    void saveEmployee(Employee employee);
    void editEmployee(Employee employee);
    Employee getEmployee(Long profileId);

    Page<Employee> findBySkillsIn(List<Skill> skills, int page, int size);

}

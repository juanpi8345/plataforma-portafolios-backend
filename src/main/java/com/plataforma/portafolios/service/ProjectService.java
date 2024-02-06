package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.Employee;
import com.plataforma.portafolios.repository.IEmployeeRepository;
import com.plataforma.portafolios.model.Project;
import com.plataforma.portafolios.repository.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService implements IProjectService {
    @Autowired
    private IEmployeeRepository employeeRepo;
    @Autowired
    private IProjectRepository projectRepo;

    @Override
    public void saveProject(Project project, Long profileId) {
        Employee employee = employeeRepo.findById(profileId).orElse(null);
        if(employee != null && project != null){
            project.setEmployee(employee);
            projectRepo.save(project);
            employee.getProjects().add(project);
            employeeRepo.save(employee);
        }
    }

    @Override
    public void deleteProject(Long projectId) {
        projectRepo.deleteById(projectId);
    }

    @Override
    public Project getProject(Long projectId) {
        return projectRepo.findById(projectId).orElse(null);
    }

    @Override
    public void editProject(Project project) {
        Project pr = projectRepo.findById(project.getProjectId()).orElse(null);
        if(pr != null){
            pr.setName(project.getName());
            pr.setDescription(project.getDescription());
            pr.setStart(project.getStart());
            pr.setEnd(project.getEnd());
            pr.setImage(project.getImage());
            projectRepo.save(pr);
        }
    }
}

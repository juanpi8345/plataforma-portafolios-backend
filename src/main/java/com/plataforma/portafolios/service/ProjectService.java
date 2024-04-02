package com.plataforma.portafolios.service;

import com.plataforma.portafolios.exceptions.EntityNotFoundException;
import com.plataforma.portafolios.model.Employee;
import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.repository.IEmployeeRepository;
import com.plataforma.portafolios.model.Project;
import com.plataforma.portafolios.repository.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    private IProjectRepository projectRepo;

    @Override
    public Project getEntity(Long projectId) {
        return projectRepo.findById(projectId).orElse(null);
    }

    @Override
    public void saveEntity(Project project) {
        if(project != null){
            projectRepo.save(project);
        }
    }

    @Override
    public void deleteEntity(Long projectId) {
        projectRepo.deleteById(projectId);
    }

    @Override
    public void editEntity(Project project) {
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

    @Override
    public void uploadImage(Long id, MultipartFile imageFile) throws IOException, EntityNotFoundException {
        Project project = projectRepo.findById(id).orElse(null);
        if(project == null)
            throw new EntityNotFoundException("Project with id: "+id + " does not exist");
        project.setImage(imageFile.getBytes());
        projectRepo.save(project);
    }

    @Override
    public void deleteImage(Project project)  throws EntityNotFoundException {
        if(projectRepo.findById(project.getProjectId()).isEmpty())
            throw new EntityNotFoundException("project with id "+project.getProjectId() + " does not exist");
        project.setImage(null);
        projectRepo.save(project);
    }
}

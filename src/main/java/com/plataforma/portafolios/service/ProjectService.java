package com.plataforma.portafolios.service;

import com.plataforma.portafolios.util.Profile;
import com.plataforma.portafolios.model.Project;
import com.plataforma.portafolios.repository.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService implements IProjectService {
    @Autowired
    private IProfileRepository profileRepo;
    @Autowired
    private IProjectRepository projectRepo;
    @Override
    public void saveProject(Project project, Long profileId) {
        Profile profile = profileRepo.findById(profileId).orElse(null);
        if(profile != null && project != null){
            project.setProfile(profile);
            projectRepo.save(project);
            profile.getProjects().add(project);
            profileRepo.save(profile);
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

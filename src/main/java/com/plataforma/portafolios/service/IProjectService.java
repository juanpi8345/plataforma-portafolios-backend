package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.Project;

public interface IProjectService {

    void saveProject(Project project, Long profileId);
    void deleteProject(Long projectId);

    Project getProject(Long projectId);

    void editProject(Project project);


}

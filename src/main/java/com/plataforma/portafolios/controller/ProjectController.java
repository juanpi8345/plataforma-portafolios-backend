package com.plataforma.portafolios.controller;

import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.model.Project;
import com.plataforma.portafolios.service.IProfileService;
import com.plataforma.portafolios.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/projects")
@Validated
public class ProjectController {
    @Autowired
    private IProfileService profileServ;
    @Autowired
    private IProjectService projectServ;

    @GetMapping("/get/profile/{profileId}")
    public ResponseEntity<List<Project>> getUserProjects(@PathVariable Long profileId){
        Profile profile = profileServ.getProfile(profileId);
        if(profile!=null)
            return ResponseEntity.ok(profile.getProjects());
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/get/profile/{profileId}/project/{projectId}")
    public ResponseEntity<Project> getUserProject(@PathVariable Long profileId, @PathVariable Long projectId){
        Profile profile = profileServ.getProfile(profileId);
        Project project = projectServ.getProject(projectId);

        if(profile!=null && project != null){

        }
        return null;
    }


}

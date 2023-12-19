package com.plataforma.portafolios.controller;

import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.model.Project;
import com.plataforma.portafolios.service.IProfileService;
import com.plataforma.portafolios.service.IProjectService;
import com.plataforma.portafolios.service.IUserService;
import jakarta.validation.Valid;
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

    @Autowired
    private IUserService userServ;

    @GetMapping("/get}")
    public ResponseEntity<List<Project>> getUserProjects(Principal principal){
        Profile profile = userServ.getLogedUser(principal).getProfile();
        if(profile!=null)
            return ResponseEntity.ok(profile.getProjects());
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/get/project/{projectId}")
    public ResponseEntity<Project> getUserProject(@PathVariable Long projectId,Principal principal){
        Profile profile = userServ.getLogedUser(principal).getProfile();
        Project project = projectServ.getProject(projectId);
        if(profile!=null && project != null){
            return ResponseEntity.ok(project);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Project> addProject(@Valid @RequestBody Project project,Principal principal){
        Profile profile = userServ.getLogedUser(principal).getProfile();
        if(profile!=null){
            profile.getProjects().add(project);
            project.setProfile(profile);
            profileServ.saveProfile(profile);
            return ResponseEntity.ok(project);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/edit")
    public ResponseEntity<Project> editProject(@RequestBody Project projectRequest, Principal principal) {
        Project project = projectServ.getProject(projectRequest.getProjectId());
        Profile profile = userServ.getLogedUser(principal).getProfile();
        if(project!= null && profile != null){
            project.setName(projectRequest.getName());
            project.setStart(projectRequest.getStart());
            project.setEnd(projectRequest.getEnd());
            project.setDescription(projectRequest.getDescription());
            project.setImage(projectRequest.getImage());
            profileServ.saveProfile(profile);
            return ResponseEntity.ok(project);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/project/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId, Principal principal){
        Project project = projectServ.getProject(projectId);
        Profile profile = userServ.getLogedUser(principal).getProfile();
        if(project != null && profile!=null){
            profile.getProjects().remove(project);
            projectServ.deleteProject(projectId);
            profileServ.saveProfile(profile);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

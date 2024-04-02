package com.plataforma.portafolios.controller;

import com.plataforma.portafolios.dto.ProjectDTO;
import com.plataforma.portafolios.exceptions.EntitiesNotFoundException;
import com.plataforma.portafolios.exceptions.EntityNotFoundException;
import com.plataforma.portafolios.exceptions.UserNotLoggedException;
import com.plataforma.portafolios.model.*;
import com.plataforma.portafolios.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin("http://localhost:4200/")
@Validated
public class EmployeeController {
    @Autowired
    private IUserService userServ;

    @Autowired
    private EmployeeService employeeServ;

    @Autowired
    private EmployerService employerServ;

    @Autowired
    private ISkillService skillServ;

    @Autowired
    private IProfileService profileServ;

    @Autowired
    private IProjectService projectServ;

    // this is to allow a employee to see al the employers with the skills they search
    @GetMapping("/get/employers")
    public ResponseEntity<Page<Employer>> getEmployersBySkills(@RequestParam List<String> skillsStr,
                                                               @RequestParam(name = "page", defaultValue = "0") int page)
                                                                throws EntityNotFoundException, EntitiesNotFoundException {
        List<Skill> skills = new ArrayList<Skill>();
        for(String skill : skillsStr){
            Skill skillFound = skillServ.getSkillByTitle(skill);
            if(skillFound != null)
                skills.add(skillFound);
        }
        return ResponseEntity.ok(employerServ.findBySkillsIn(skills,page,10));
    }


    // this is to allow a employee to see a employer profile.
    @GetMapping("/get/employer/{profileId}")
    public ResponseEntity<Employer> getEmployer(@PathVariable Long profileId) throws EntityNotFoundException {
        return ResponseEntity.ok((Employer) profileServ.getEntity(profileId));
    }

    @GetMapping("/get/project/{projectId}")
    public ResponseEntity<Project> getUserProject(@PathVariable Long projectId) throws EntityNotFoundException, UserNotLoggedException {
        Project project = projectServ.getEntity(projectId);
        return ResponseEntity.ok(project);
    }

    @GetMapping("/get/project/{projectId}/image")
    public ResponseEntity<byte[]> getProjectImage(@PathVariable Long projectId) throws EntityNotFoundException {
        Project project = projectServ.getEntity(projectId);
        return ResponseEntity.ok(project.getImage());
    }

    @PostMapping("/project/{projectId}/add/image")
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile file,
                                         @PathVariable Long projectId) throws IOException, EntityNotFoundException, UserNotLoggedException {
        if (file.isEmpty())
            return ResponseEntity.badRequest().body("Image is empty.");
        Project project = projectServ.getEntity(projectId);
        projectServ.uploadImage(project.getProjectId(),file);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add/skill")
    public ResponseEntity<Skill> addSkill(@RequestParam String title, Principal principal) throws EntityNotFoundException, UserNotLoggedException {
        Profile pr = userServ.getLoggedUser(principal).getProfile();
        Skill sk = skillServ.getSkillByTitle(title);
        if(pr instanceof Employee em && sk != null) {
            if(!em.getSkills().contains(sk)){
                em.getSkills().add(sk);
                sk.getEmployees().add(em);
                profileServ.saveEntity(em);
            }
            return ResponseEntity.ok(sk);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add/project")
    public ResponseEntity<Project> addProject(@RequestBody @Valid ProjectDTO project, Principal principal) throws IOException, UserNotLoggedException {
        Profile profile = userServ.getLoggedUser(principal).getProfile();
        if(profile!=null && project != null){
            Project pr = Project.builder()
                    .start(project.getStart())
                    .end(project.getEnd())
                    .description(project.getDescription())
                    .name(project.getName()).build();

            Employee employee = (Employee) profile;
            employee.getProjects().add(pr);
            profileServ.saveEntity(employee);
            pr.setEmployee(employee);
            projectServ.saveEntity(pr);
            return ResponseEntity.ok(pr);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/edit/project")
    public ResponseEntity<Project> editProject(@RequestBody Project projectRequest, Principal principal) throws EntityNotFoundException, UserNotLoggedException {
        Project project = projectServ.getEntity(projectRequest.getProjectId());
        Profile profile = userServ.getLoggedUser(principal).getProfile();
        projectServ.editEntity(project);
        return ResponseEntity.ok(project);

    }

    @DeleteMapping("/delete/project/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId, Principal principal) throws EntityNotFoundException, UserNotLoggedException {
        Project project = projectServ.getEntity(projectId);
        Profile profile = userServ.getLoggedUser(principal).getProfile();
        Employee employee = (Employee)profile;
        employee.getProjects().remove(project);
        projectServ.deleteEntity(projectId);
        profileServ.saveEntity(employee);
        return ResponseEntity.ok().build();


    }

    @DeleteMapping("/delete/skill/{skillId}")
    public ResponseEntity<?> deleteSkill(@PathVariable Long skillId, Principal principal) throws EntityNotFoundException, UserNotLoggedException {
        Profile profile = userServ.getLoggedUser(principal).getProfile();
        Skill skill = skillServ.getEntity(skillId);
        if(profile instanceof Employee em && skill!=null){
            em.getSkills().remove(skill);
            skill.getEmployees().remove(em);
            profileServ.saveEntity(em);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/project/{projectId}/image")
    public ResponseEntity<?> deleteProjectImage(@PathVariable Long projectId) throws EntityNotFoundException {
        Project project = projectServ.getEntity(projectId);
        projectServ.deleteImage(project);
        return ResponseEntity.ok().build();
    }

}

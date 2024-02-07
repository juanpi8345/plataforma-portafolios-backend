package com.plataforma.portafolios.controller;

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
@RequestMapping("/employee/")
@CrossOrigin("http://localhost:4200/")
@Validated
public class EmployeeController {
    @Autowired
    private IUserService userServ;
    @Autowired
    private IEmployeeService employeeServ;

    @Autowired
    private IEmployerService employerServ;

    @Autowired
    private ISkillService skillServ;

    @Autowired
    private IProjectService projectServ;

    @GetMapping("/get/employers")
    public ResponseEntity<Page<Employer>> getEmployersBySkills(@RequestParam List<String> skillsStr, @RequestParam(name = "page", defaultValue = "0") int page){
        List<Skill> skills = new ArrayList<Skill>();
        for(String skill : skillsStr){
            Skill skillFound = skillServ.getSkillByTitle(skill);
            if(skillFound != null)
                skills.add(skillFound);
        }
        return ResponseEntity.ok(employerServ.findBySkillsIn(skills,page,10));
    }

    @GetMapping("/getProject/{projectId}")
    public ResponseEntity<Project> getUserProject(@PathVariable Long projectId, Principal principal){
        Profile profile = userServ.getLogedUser(principal).getProfile();
        Project project = projectServ.getProject(projectId);
        if(profile!=null && project != null){
            return ResponseEntity.ok(project);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/addSkill")
    public ResponseEntity<Skill> addSkill(@Valid @RequestParam String title, Principal principal){
        Profile pr = userServ.getLogedUser(principal).getProfile();
        Skill sk = skillServ.getSkillByTitle(title);
        if(pr instanceof Employee em && sk != null) {
            if(!em.getSkills().contains(sk)){
                em.getSkills().add(sk);
                sk.getEmployees().add(em);
                employeeServ.saveEmployee(em);
            }
            return ResponseEntity.ok(sk);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/addProject")
    public ResponseEntity<Project> addProject(@Valid @RequestBody Project project,Principal principal){
        Profile profile = userServ.getLogedUser(principal).getProfile();
        if(profile!=null){
            Employee employee = (Employee) profile;
            employee.getProjects().add(project);
            project.setEmployee(employee);
            employeeServ.saveEmployee(employee);
            return ResponseEntity.ok(project);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/editProject")
    public ResponseEntity<Project> editProject(@RequestBody Project projectRequest, Principal principal) {
        Project project = projectServ.getProject(projectRequest.getProjectId());
        Profile profile = userServ.getLogedUser(principal).getProfile();
        if(project!= null && profile != null){
            Employee employee = (Employee) profile;
            project.setName(projectRequest.getName());
            project.setStart(projectRequest.getStart());
            project.setEnd(projectRequest.getEnd());
            project.setDescription(projectRequest.getDescription());
            project.setImage(projectRequest.getImage());
            employeeServ.saveEmployee(employee);
            return ResponseEntity.ok(project);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteProject/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId, Principal principal){
        Project project = projectServ.getProject(projectId);
        Profile profile = userServ.getLogedUser(principal).getProfile();
        if(project != null && profile!=null){
            Employee employee = (Employee)profile;
            employee.getProjects().remove(project);
            projectServ.deleteProject(projectId);
            employeeServ.saveEmployee(employee);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteSkill/{skillId}")
    public ResponseEntity<?> deleteSkill(@PathVariable Long skillId, Principal principal){
        Profile profile = userServ.getLogedUser(principal).getProfile();
        Skill skill = skillServ.getSkill(skillId);
        if(profile instanceof Employee em && skill!=null){
            em.getSkills().remove(skill);
            skill.getEmployees().remove(em);
            employeeServ.saveEmployee(em);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}

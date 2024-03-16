package com.plataforma.portafolios.controller;

import com.plataforma.portafolios.model.Employee;
import com.plataforma.portafolios.model.Employer;
import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.service.*;
import com.plataforma.portafolios.model.Skill;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employer")
@CrossOrigin("http://localhost:4200/")
@Validated
public class EmployerController {
    @Autowired
    private IUserService userServ;
    @Autowired
    private EmployeeService employeeServ;
    @Autowired
    private EmployerService employerServ;

    @Autowired
    private IProfileService profileServ;

    @Autowired
    private ISkillService skillServ;

    // this is to allow a employer  to see al the employees with the skills they search
    @GetMapping("/get/employees")
    public ResponseEntity<Page<Employee>> getEmployeesBySkills(@RequestParam List<String> skillsStr, @RequestParam(name = "page", defaultValue = "0") int page){
        List<Skill> skills = new ArrayList<Skill>();
        for(String skill : skillsStr){
            Skill skillFound = skillServ.getSkillByTitle(skill);
            if(skillFound != null)
                skills.add(skillFound);
        }
        return ResponseEntity.ok(employeeServ.findBySkillsIn(skills,page,10));
    }

    // this is to allow a employee to see a employer profile.
    @GetMapping("/get/employee/{profileId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long profileId){
        return ResponseEntity.ok((Employee) profileServ.getEntity(profileId));
    }


    @PostMapping("/add/searchedSkill")
    public ResponseEntity<Skill> addSearchedSkill(@Valid @RequestParam String title, Principal principal){
        Profile pr = userServ.getLogedUser(principal).getProfile();
        Skill sk = skillServ.getSkillByTitle(title);
        if(pr instanceof Employer em && sk != null) {
            if(!em.getSearchedSkills().contains(sk)){
                em.getSearchedSkills().add(sk);
                sk.getEmployers().add(em);
                profileServ.saveEntity(em);
            }
            return ResponseEntity.ok(sk);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/edit/searching")
    public ResponseEntity<Employer> editSearching(@RequestParam String newSearching, Principal principal){
        Profile profile = userServ.getLogedUser(principal).getProfile();
        if(profile!=null){
            Employer emp = (Employer) profile;
            emp.setSearching(newSearching);
            profileServ.saveEntity(emp);
            return ResponseEntity.ok(emp);
         }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/deleteSkill/{skillId}")
    public ResponseEntity<?> deleteSearchedSkill(@PathVariable Long skillId, Principal principal){
        Profile profile = userServ.getLogedUser(principal).getProfile();
        Skill skill = skillServ.getEntity(skillId);
        if(profile instanceof Employer em && skill!=null){
            em.getSearchedSkills().remove(skill);
            skill.getEmployers().remove(em);
            profileServ.saveEntity(em);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }




}

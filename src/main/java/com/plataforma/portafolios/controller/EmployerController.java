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
    private IEmployeeService employeeServ;

    @Autowired
    private IEmployerService employerServ;

    @Autowired
    private ISkillService skillServ;

    @PostMapping("/add/searchedSkill")
    public ResponseEntity<Skill> addSearchedSkill(@Valid @RequestParam String title, Principal principal){
        Profile pr = userServ.getLogedUser(principal).getProfile();
        Skill sk = skillServ.getSkillByTitle(title);
        if(pr instanceof Employer em && sk != null) {
            if(!em.getSkillsSearched().contains(sk)){
                em.getSkillsSearched().add(sk);
                sk.getEmployers().add(em);
                employerServ.saveEmployer(em);
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
            employerServ.saveEmployer(emp);
            return ResponseEntity.ok(emp);
         }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/deleteSkill/{skillId}")
    public ResponseEntity<?> deleteSearchedSkill(@PathVariable Long skillId, Principal principal){
        Profile profile = userServ.getLogedUser(principal).getProfile();
        Skill skill = skillServ.getSkill(skillId);
        if(profile instanceof Employer em && skill!=null){
            em.getSkillsSearched().remove(skill);
            skill.getEmployers().remove(em);
            employerServ.saveEmployer(em);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }




}

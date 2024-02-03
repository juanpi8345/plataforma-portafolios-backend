package com.plataforma.portafolios.controller;

import com.plataforma.portafolios.model.Employer;
import com.plataforma.portafolios.service.*;
import com.plataforma.portafolios.util.Profile;
import com.plataforma.portafolios.model.Skill;

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
@RequestMapping("/profile")
@CrossOrigin("http://localhost:4200/")
@Validated
public class ProfileController {
    @Autowired
    private IUserService userServ;
    @Autowired
    private IEmployeeService employeeServ;

    @Autowired
    private IEmployerService employerServ;

    @Autowired
    private ISkillService skillServ;

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


}

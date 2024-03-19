package com.plataforma.portafolios.controller;

import com.plataforma.portafolios.exceptions.EntitiesNotFoundException;
import com.plataforma.portafolios.model.Employee;
import com.plataforma.portafolios.model.Employer;
import com.plataforma.portafolios.service.*;
import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.model.Skill;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@Validated
@RequestMapping("/skills")
@CrossOrigin("http://localhost:4200/")
public class SkillController {
    @Autowired
    private ISkillService skillServ;
    @Autowired
    private IUserService userServ;
    @GetMapping("/getAll")
    public ResponseEntity<List<Skill>> getAllSkills(Principal principal) throws EntitiesNotFoundException {
        Profile profile = userServ.getLoggedUser(principal).getProfile();
        if(profile != null)
            return ResponseEntity.ok(skillServ.getAll());
        return ResponseEntity.notFound().build();
    }

}

package com.plataforma.portafolios.controller;

import com.plataforma.portafolios.service.IProfileService;
import com.plataforma.portafolios.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
@Validated
public class ProjectController {
    @Autowired
    private IProfileService profileServ;
    @Autowired
    private IProjectService projectServ;

    @PostMapping
}

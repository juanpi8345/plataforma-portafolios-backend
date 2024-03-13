package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.Employer;
import com.plataforma.portafolios.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IEmployerService {
    void saveEmployer(Employer employer);

    Employer getEmployer(Long profileId);

    Page<Employer> findBySkillsIn(List<Skill> skills, int page, int size);

}

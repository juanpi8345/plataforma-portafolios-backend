package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.Employer;
import com.plataforma.portafolios.model.Skill;
import com.plataforma.portafolios.util.Profile;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IEmployerService {
    void saveEmployer(Employer employer);
    void editEmployer(Employer employer);
    Employer getEmployer(Long profileId);

    Page<Employer> findBySkillsIn(List<Skill> skills, int page, int size);

    void uploadImage(Long profileId, MultipartFile imageFile);
}

package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.model.Project;
import com.plataforma.portafolios.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProfileService {
    void saveProfile(Profile profile);
    void editProfile(Profile profile);
    Profile getProfile(Long profileId);

    Page<Profile> findBySkillsIn(List<Skill> skills, int page, int size);

    void uploadImage(Long profileId, MultipartFile imageFile);
}

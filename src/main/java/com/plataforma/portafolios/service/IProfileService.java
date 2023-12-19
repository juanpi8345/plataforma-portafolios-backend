package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.model.Project;
import com.plataforma.portafolios.model.Skill;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProfileService {
    void saveProfile(Profile profile);
    void editProfile(Profile profile);
    Profile getProfile(Long profileId);

    void uploadImage(Long profileId, MultipartFile imageFile);
}

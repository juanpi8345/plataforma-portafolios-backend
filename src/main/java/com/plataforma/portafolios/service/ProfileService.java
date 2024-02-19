package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.repository.IProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProfileService implements IProfileService{
    @Autowired
    private IProfileRepository profileRepo;

    @Override
    public void saveProfile(Profile profile) {
        profileRepo.save(profile);
    }

    @Override
    public void uploadImage(Long profileId, MultipartFile imageFile) {

    }

    @Override
    public Profile getProfile(Long profileId) {
        return profileRepo.findById(profileId).orElse(null);
    }
}

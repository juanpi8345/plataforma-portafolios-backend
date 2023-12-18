package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.model.User;
import com.plataforma.portafolios.repository.IProfileRepository;
import com.plataforma.portafolios.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService implements IProfileService {

    @Autowired
    private IProfileRepository profileRepo;

    @Autowired
    private IUserRepository userRepo;

    @Override
    public void saveProfile(Profile profile, Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if(user != null){
            profileRepo.save(profile);
            user.setProfile(profile);
        }
    }

    @Override
    public void editProfile(Profile profile) {
        Profile pr = profileRepo.findById(profile.getProfileId()).orElse(null);
        if(pr != null){
            pr.setImage(profile.getImage());
            pr.setName(profile.getName());
            pr.setSkills(profile.getSkills());
            pr.setProjects(pr.getProjects());
            profileRepo.save(profile);
        }
    }

    @Override
    public Profile getProfile(Long profileId) {
        return profileRepo.findById(profileId).orElse(null);
    }
}

package com.plataforma.portafolios.service;

import com.plataforma.portafolios.util.Profile;
import com.plataforma.portafolios.model.Skill;
import com.plataforma.portafolios.repository.IProfileRepository;
import com.plataforma.portafolios.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProfileService implements IProfileService {

    @Autowired
    private IProfileRepository profileRepo;

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private ISkillService skillServ;

    @Override
    public void saveProfile(Profile profile) {
            profileRepo.save(profile);
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

    @Override
    public Page<Profile> findBySkillsIn(List<Skill> skills, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return profileRepo.findBySkills(skills, pageRequest);
    }

    public void uploadImage(Long profileId, MultipartFile imageFile) {
        Profile profile = profileRepo.findById(profileId).orElse(null);
        if (profile != null) {
            try {
                byte[] imageData = imageFile.getBytes();
                profile.setImage(imageData);
                profileRepo.save(profile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

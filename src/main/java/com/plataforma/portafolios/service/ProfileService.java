package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.Employee;
import com.plataforma.portafolios.model.Employer;
import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.model.Skill;
import com.plataforma.portafolios.repository.IEmployeeRepository;
import com.plataforma.portafolios.repository.IEmployerRepository;
import com.plataforma.portafolios.repository.IProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Random;

@Service
public class ProfileService implements IProfileService{
    @Autowired
    private IProfileRepository profileRepo;

    @Autowired
    private IEmployerRepository employerRepo;

    @Autowired
    private IEmployeeRepository employeeRepo;

    @Override
    public void saveProfile(Profile profile) {
        profileRepo.save(profile);
    }

    @Override
    public Profile getProfile(Long profileId) {
        return profileRepo.findById(profileId).orElse(null);
    }

    //skills could be employees' skills or searched skills in case of employers
    @Override
    public <E> List<E> getRecommendedProfiles(Profile profile) {
        List<E> profileList;
        int count = 0;
        Random random = new Random();
        int skillsSize = 0;
        if(profile instanceof Employer){
            Employer employer = (Employer) profile;
            skillsSize = employer.getSearchedSkills().size();
            profileList = (List<E>)  employeeRepo.findBySkills(employer.getSearchedSkills().get(random.nextInt(skillsSize))
            ,employer.getSearchedSkills().get(random.nextInt(skillsSize)),employer.getSearchedSkills().get(random.nextInt(skillsSize)));
        }else{
            Employee employee = (Employee) profile;
            skillsSize = employee.getSkills().size();
            profileList = (List<E>)  employerRepo.findBySkills(employee.getSkills().get(random.nextInt(skillsSize))
                            ,employee.getSkills().get(random.nextInt(skillsSize)),employee.getSkills().get(random.nextInt(skillsSize)));
        }
        return profileList;
    }

    @Override
    public void uploadImage(Long profileId, MultipartFile imageFile) throws IOException {
        Profile profile = profileRepo.findById(profileId).orElse(null);
        profile.setImage(imageFile.getBytes());
        profileRepo.save(profile);
    }
}

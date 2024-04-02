package com.plataforma.portafolios.service;

import com.plataforma.portafolios.exceptions.EntitiesNotFoundException;
import com.plataforma.portafolios.exceptions.EntityNotFoundException;
import com.plataforma.portafolios.model.Employee;
import com.plataforma.portafolios.model.Employer;
import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.model.Skill;
import com.plataforma.portafolios.repository.IEmployeeRepository;
import com.plataforma.portafolios.repository.IEmployerRepository;
import com.plataforma.portafolios.repository.IProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class ProfileService implements IProfileService{
    @Autowired
    private IProfileRepository profileRepo;

    @Autowired
    private IEmployerRepository employerRepo;

    @Autowired
    private IEmployeeRepository employeeRepo;


    //skills could be employees' skills or searched skills in case of employers
    @Override
    public <E> List<E> getRecommendedProfiles(Profile profile) throws EntitiesNotFoundException {
        List<E> profileList;
        int count = 0;
        Random random = new Random();
        int skillsSize = 0;
        if(profile instanceof Employer){
            Employer employer = (Employer) profile;
            if(employer.getSearchedSkills().isEmpty())
                throw new EntitiesNotFoundException("Employer is not searching any skills");
            skillsSize = employer.getSearchedSkills().size();
            profileList = (List<E>)  employeeRepo.findBySkills(employer.getSearchedSkills().get(random.nextInt(skillsSize))
            ,employer.getSearchedSkills().get(random.nextInt(skillsSize)),employer.getSearchedSkills().get(random.nextInt(skillsSize)));
        }else{
            Employee employee = (Employee) profile;
            if(employee.getSkills().isEmpty())
                throw new EntitiesNotFoundException("Employee does not have any skill");
            skillsSize = employee.getSkills().size();
            profileList = (List<E>)  employerRepo.findBySkills(employee.getSkills().get(random.nextInt(skillsSize))
                            ,employee.getSkills().get(random.nextInt(skillsSize)),employee.getSkills().get(random.nextInt(skillsSize)));
        }
        if(profileList.isEmpty())
            throw new EntitiesNotFoundException("There are not recommended profiles");
        return profileList;
    }

    @Override
    public void uploadImage(Long profileId, MultipartFile imageFile) throws IOException,EntityNotFoundException {
        Profile profile = profileRepo.findById(profileId).orElse(null);
        if(profile == null)
            throw new EntityNotFoundException("Profile with id "+profile.getProfileId() + " does not exist");
        profile.setImage(imageFile.getBytes());
        profileRepo.save(profile);
    }

    @Override
    public void deleteImage(Profile profile) throws EntityNotFoundException {
        if(profileRepo.findById(profile.getProfileId()).isEmpty())
            throw new EntityNotFoundException("Profile with id "+profile.getProfileId() + " does not exist");
        profile.setImage(null);
        profileRepo.save(profile);
    }

    @Override
    public Profile getEntity(Long id) throws EntityNotFoundException {
        Optional<Profile> profile = profileRepo.findById(id);
        if(!profile.isPresent())
            throw new EntityNotFoundException("Profile not available");
        return profile.get();
    }

    @Override
    public void saveEntity(Profile profile) {
        if(profile != null)
            profileRepo.save(profile);
    }

    @Override
    public void deleteEntity(Long id) throws EntityNotFoundException {
        Optional<Profile> profile = profileRepo.findById(id);
        if(!profile.isPresent())
            throw new EntityNotFoundException("Profile not found");
        profileRepo.deleteById(id);
    }

    @Override
    public void editEntity(Profile profile) throws EntityNotFoundException {
        Optional<Profile> pr = profileRepo.findById(profile.getProfileId());
        if(!pr.isPresent())
            throw new EntityNotFoundException("Profile not found");
        this.saveEntity(profile);
    }
}

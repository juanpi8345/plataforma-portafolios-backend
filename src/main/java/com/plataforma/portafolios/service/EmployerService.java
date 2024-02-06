package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.Employer;
import com.plataforma.portafolios.model.Skill;
import com.plataforma.portafolios.repository.IEmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class EmployerService  implements  IEmployerService{

    @Autowired
    private IEmployerRepository employerRepo;

    @Override
    public void saveEmployer(Employer employer) {
        employerRepo.save(employer);
    }

    @Override
    public void editEmployer(Employer employer) {
        Employer em = employerRepo.findById(employer.getProfileId()).orElse(null);
        if(em != null){
            em.setImage(employer.getImage());
            em.setName(employer.getName());
            em.setSkillsSearched(employer.getSkillsSearched());
            em.setDescription(em.getDescription());
            em.setSearching(em.getSearching());
            employerRepo.save(employer);
        }
    }

    @Override
    public Employer getEmployer(Long profileId) {
        return employerRepo.findById(profileId).orElse(null);
    }

    @Override
    public Page<Employer> findBySkillsIn(List<Skill> skills, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return employerRepo.findBySkillsSearched(skills,pageable);
    }

    @Override
    public void uploadImage(Long profileId, MultipartFile imageFile) {
        Employer employer = employerRepo.findById(profileId).orElse(null);
        if (employer != null) {
            try {
                byte[] imageData = imageFile.getBytes();
                employer.setImage(imageData);
                employerRepo.save(employer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

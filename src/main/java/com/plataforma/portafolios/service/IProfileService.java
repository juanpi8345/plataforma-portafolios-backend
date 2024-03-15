package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.Employer;
import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.model.Skill;
import com.plataforma.portafolios.util.IGenericOperations;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface IProfileService extends IGenericOperations<Profile,Long> {

    void uploadImage(Long profileId, MultipartFile imageFile) throws IOException;

    //this method will return recommended users, like employees or employers
    //E will be employers or employees
    //skills could be employees' skills or searched skills in case of employers
    <E> List<E> getRecommendedProfiles(Profile profile);




}

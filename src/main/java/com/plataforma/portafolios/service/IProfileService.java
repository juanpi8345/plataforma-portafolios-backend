package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.util.IGenericCrud;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProfileService extends IGenericCrud<Profile,Long> {

    void uploadImage(Long profileId, MultipartFile imageFile) throws IOException;

    //this method will return recommended users, like employees or employers
    //E will be employers or employees
    //skills could be employees' skills or searched skills in case of employers
    <E> List<E> getRecommendedProfiles(Profile profile);




}

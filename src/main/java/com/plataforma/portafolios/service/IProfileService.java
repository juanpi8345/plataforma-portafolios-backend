package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.Profile;

public interface IProfileService {
    void saveProfile(Profile profile, Long userId);
    void editProfile(Profile profile);
    Profile getProfile(Long profileId);
}

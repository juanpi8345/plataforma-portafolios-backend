package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.Skill;

public interface ISkillService {

    void saveSkill(Skill skill, Long profileId);
    void deleteSkill(Long skillId);

    Skill getSkill(Long skillId);

    Skill getSkillByTitle(String title);
    void editSkill(Skill skillRequest);
}

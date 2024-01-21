package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.Skill;

import java.util.List;

public interface ISkillService {

    void saveSkill(Skill skill, Long profileId);
    void deleteSkill(Long skillId);

    List<Skill> getSkillContaining(String query);

    Skill getSkill(Long skillId);

    List<Skill> getAll();

    Skill getSkillByTitle(String title);
    void editSkill(Skill skillRequest);
}

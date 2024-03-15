package com.plataforma.portafolios.service;

import com.plataforma.portafolios.model.Skill;
import com.plataforma.portafolios.util.IGenericCrud;

import java.util.List;

public interface ISkillService extends IGenericCrud<Skill,Long> {

    List<Skill> getSkillContaining(String query);
    List<Skill> getAll();
    Skill getSkillByTitle(String title);
}

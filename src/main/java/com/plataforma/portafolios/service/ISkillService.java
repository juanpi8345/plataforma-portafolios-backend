package com.plataforma.portafolios.service;

import com.plataforma.portafolios.exceptions.EntitiesNotFoundException;
import com.plataforma.portafolios.exceptions.EntityNotFoundException;
import com.plataforma.portafolios.model.Skill;

import java.util.List;

public interface ISkillService extends IGenericService<Skill,Long> {

    List<Skill> getSkillContaining(String query) throws EntityNotFoundException;
    List<Skill> getAll() throws EntitiesNotFoundException;
    Skill getSkillByTitle(String title) throws EntityNotFoundException;
}

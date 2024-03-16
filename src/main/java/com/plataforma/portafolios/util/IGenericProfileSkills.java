package com.plataforma.portafolios.util;

import com.plataforma.portafolios.model.Employer;
import com.plataforma.portafolios.model.Skill;
import org.springframework.data.domain.Page;

import java.util.List;

@FunctionalInterface
public interface IGenericProfileSkills <E>{
    Page<E> findBySkillsIn(List<Skill> skills, int page, int size);
}

package com.plataforma.portafolios.repository;

import com.plataforma.portafolios.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISkillRepository extends JpaRepository<Skill,Long> {
    Skill findByTitle(String title);
    List<Skill> findByTitleContaining(String query);
}

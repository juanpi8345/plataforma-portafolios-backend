package com.plataforma.portafolios.repository;

import com.plataforma.portafolios.model.Employer;
import com.plataforma.portafolios.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEmployerRepository extends JpaRepository<Employer,Long> {
    @Query("SELECT p FROM Profile p JOIN p.skills s WHERE s IN :skills")
    Page<Employer> findBySkillsSearched(@Param("skills") List<Skill> skills, Pageable pageable);
}

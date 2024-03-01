package com.plataforma.portafolios.repository;

import com.plataforma.portafolios.model.Employee;
import com.plataforma.portafolios.model.Employer;
import com.plataforma.portafolios.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEmployerRepository extends JpaRepository<Employer,Long> {
    @Query("SELECT e FROM Employer e JOIN e.searchedSkills s WHERE s IN :skills")
    Page<Employer> findBySearchedSkills(@Param("skills") List<Skill> skills, Pageable pageable);

    @Query("SELECT e FROM Employer e WHERE :skill1 MEMBER OF e.skills OR :skill2 MEMBER OF e.skills OR :skill3 MEMBER OF e.skills")
    List<Employer> findBySkills(@Param("skill1") Skill skill1, @Param("skill2") Skill skill2, @Param("skill3") Skill skill3);
}

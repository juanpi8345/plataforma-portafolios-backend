package com.plataforma.portafolios.repository;


import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.model.Skill;
import com.plataforma.portafolios.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProfileRepository extends JpaRepository<Profile,Long> {

    @Query("SELECT p FROM Profile p WHERE :skills MEMBER OF p.skills")
    Page<Profile> findByAllSkills(@Param("skills") List<Skill> skills, Pageable pageable);

}


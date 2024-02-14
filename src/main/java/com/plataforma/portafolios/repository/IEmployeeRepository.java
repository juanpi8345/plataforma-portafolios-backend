package com.plataforma.portafolios.repository;

import com.plataforma.portafolios.model.Employee;
import com.plataforma.portafolios.model.Employer;
import com.plataforma.portafolios.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee,Long> {

    @Query("SELECT e FROM Employee e JOIN e.skills s WHERE s IN :skills")
    Page<Employee> findBySkills(@Param("skills") List<Skill> skills, Pageable pageable);
}

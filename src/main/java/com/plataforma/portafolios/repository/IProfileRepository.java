package com.plataforma.portafolios.repository;


import com.plataforma.portafolios.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfileRepository extends JpaRepository<Profile,Long> {
}

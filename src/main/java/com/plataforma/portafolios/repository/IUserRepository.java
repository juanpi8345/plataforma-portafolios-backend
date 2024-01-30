package com.plataforma.portafolios.repository;

import com.plataforma.portafolios.model.Skill;
import com.plataforma.portafolios.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findByUsernameAndPassword(String username, String password);


}


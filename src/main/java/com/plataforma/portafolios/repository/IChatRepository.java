package com.plataforma.portafolios.repository;

import com.plataforma.portafolios.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IChatRepository extends JpaRepository<Chat,Long> {
    Chat findByFirstUsernameAndSecondUsername(String firstUsername, String secondUsername);

}

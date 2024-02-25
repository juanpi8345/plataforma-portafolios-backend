package com.plataforma.portafolios.repository;

import com.plataforma.portafolios.model.Chat;
import com.plataforma.portafolios.model.Message;
import com.plataforma.portafolios.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMessageRepository extends JpaRepository<Message,Long> {

    @Query("SELECT m FROM Message m WHERE (:profile1 = m.sender AND :profile2 = m.receiver) OR (:profile1 = m.receiver AND :profile2 = m.sender)")
    List<Message> findChatMessages(@Param("profile1") Profile profile1, @Param("profile2") Profile profile2);

}

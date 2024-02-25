package com.plataforma.portafolios.repository;

import com.plataforma.portafolios.model.Chat;
import com.plataforma.portafolios.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IChatRepository extends JpaRepository<Chat,Long> {
    @Query("SELECT c FROM Chat c WHERE (:profile1 = c.profile1 AND :profile2 = c.profile2) OR (:profile1 = c.profile2 AND :profile2 = c.profile1)")
    Chat findByProfile1AndProfile2(@Param("profile1") Profile profile1, @Param("profile2") Profile profile2);

    @Query("SELECT c FROM Chat c WHERE :profile = c.profile1 OR :profile = c.profile2")
    List<Chat> findChatsOfProfile(@Param("profile") Profile profile);

}

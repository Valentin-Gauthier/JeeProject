package com.JEE_Project.JEE_Project.Repositories;

import com.JEE_Project.JEE_Project.Models.Programme;
import com.JEE_Project.JEE_Project.Models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoProgramme extends JpaRepository<Programme, Long> {

    // Recuperer les Programme d'un Utilisateur
    @Query("SELECT p FROM Programme p LEFT JOIN FETCH p.activites WHERE p.utilisateur = :utilisateur")
    List<Programme> findProgrammes(@Param("utilisateur")Utilisateur utilisateur);

    // Supprime un programme
    @Modifying
    @Query("DELETE FROM Programme p WHERE p = :programme")
    void deleteProgramme(@Param("programme") Programme programme);

    // Recuperer un Programme avec son Id
    Programme findProgrammeByProgrammeId(long programmeId);
}

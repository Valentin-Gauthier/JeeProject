package com.JEE_Project.JEE_Project.Repositories;

import com.JEE_Project.JEE_Project.Models.Programme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoProgramme extends JpaRepository<Programme, Long> {

    // Supprime un programme
    @Modifying
    @Query("DELETE FROM Programme WHERE programmeId = :programmeId")
    void deleteProgramme(@Param("programmeId") long programmeId);

    // Recuperer les programmes de l'Utilisateur avec les activites
    @Query("SELECT p, a FROM Programme p LEFT JOIN Programme_Activite pa ON p.programmeId = pa.programmeId LEFT JOIN Activite a ON pa.activiteId = a.activiteId WHERE p.utilisateurId = :id")
    List<Object[]> findProgrammeWithActivite(@Param("id") long id);

}

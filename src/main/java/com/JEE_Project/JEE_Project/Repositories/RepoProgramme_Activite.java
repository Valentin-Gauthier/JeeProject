package com.JEE_Project.JEE_Project.Repositories;

import com.JEE_Project.JEE_Project.Models.Programme_Activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoProgramme_Activite extends JpaRepository<Programme_Activite, Long> {

    // Supprimer l'activité d'un programme
    @Modifying
    @Query("DELETE FROM Programme_Activite WHERE programmeId = :programmeId AND activiteId = :activiteId")
    void deleteActiviteFromProgramme(@Param("programmeId") long programmeId, @Param("activiteId") long activiteId);

    // Supprime toutes les activites d'un programme
    @Modifying
    @Query("DELETE FROM Programme_Activite WHERE programmeId = :programmeId")
    void deleteAllActiviteFromProgramme(@Param("programmeId") long programmeId);
}

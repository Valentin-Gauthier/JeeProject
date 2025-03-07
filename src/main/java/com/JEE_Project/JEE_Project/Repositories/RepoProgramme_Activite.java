package com.JEE_Project.JEE_Project.Repositories;

import com.JEE_Project.JEE_Project.Models.Activite;
import com.JEE_Project.JEE_Project.Models.Programme;
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
    @Query("DELETE FROM Programme_Activite pa WHERE pa.programme = :programme AND pa.activite = :activite")
    void deleteActiviteFromProgramme(@Param("programme") Programme programme, @Param("activite") Activite activite);

    // Supprime toutes les activites d'un programme
    @Modifying
    @Query("DELETE FROM Programme_Activite pa WHERE pa.programme = :programme")
    void deleteAllActiviteFromProgramme(@Param("programme") Programme programme);

}

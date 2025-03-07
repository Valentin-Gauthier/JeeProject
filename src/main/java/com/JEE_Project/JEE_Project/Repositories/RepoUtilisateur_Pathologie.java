package com.JEE_Project.JEE_Project.Repositories;

import com.JEE_Project.JEE_Project.Models.Pathologie;
import com.JEE_Project.JEE_Project.Models.Utilisateur;
import com.JEE_Project.JEE_Project.Models.Utilisateur_Pathologie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoUtilisateur_Pathologie extends JpaRepository<Utilisateur_Pathologie, Long> {


    // Recuperer les Pathologies d'un Utilisateur
    @Query("SELECT up FROM Utilisateur_Pathologie up WHERE up.utilisateur = :utilisateur")
    List<Utilisateur_Pathologie> findPathologies(@Param("utilisateur") Utilisateur utilisateur);


    // Supprimer la pathologie associé aux pathologie de l'utilisateur
    @Modifying
    @Query("DELETE FROM Utilisateur_Pathologie WHERE utilisateur = :utilisateur AND pathologie = :pathologie")
    void removePahtologieForUtilisateur(@Param("pathologie") Pathologie pathologie,@Param("utilisateur") Utilisateur utilisateur);
}


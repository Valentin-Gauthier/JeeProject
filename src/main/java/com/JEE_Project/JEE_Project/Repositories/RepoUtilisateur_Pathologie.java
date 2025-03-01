package com.JEE_Project.JEE_Project.Repositories;

import com.JEE_Project.JEE_Project.Models.Utilisateur_Pathologie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoUtilisateur_Pathologie extends JpaRepository<Utilisateur_Pathologie, Long> {

    // Ajoute la pathologie aux pathologies associé à l'utilisateur
    @Modifying
    @Query("INSERT INTO Utilisateur_Pathologie(utilisateurId, pathologieId) VALUES (:utilisateurId, :pathologieId)")
    void addPathologieForUtilisateur(@Param("pathologieId")long pathologieId, @Param("utilisateurId") long utilisateurId);

    // Supprimer la pathologie associé aux pathologie de l'utilisateur
    @Modifying
    @Query("DELETE FROM Utilisateur_Pathologie WHERE utilisateurId = :utilisateurId AND pathologieId = :pathologieId")
    void removePahtologieForUtilisateur(@Param("pathologieId") long pathologieId,@Param("utilisateurId") long utilisateurId);
}

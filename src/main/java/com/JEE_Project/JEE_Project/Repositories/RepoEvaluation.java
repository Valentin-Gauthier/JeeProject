package com.JEE_Project.JEE_Project.Repositories;

import com.JEE_Project.JEE_Project.Models.Activite;
import com.JEE_Project.JEE_Project.Models.Evaluation;
import com.JEE_Project.JEE_Project.Models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoEvaluation  extends JpaRepository<Evaluation, Long> {

    // Recuperer les Evaluations d'un Utilisateur
    @Query("SELECT e FROM Evaluation e WHERE e.utilisateur = :utilisateur")
    List<Evaluation> findEvaluationByUtilisateur(@Param("utilisateur") Utilisateur utilisateur);

    // Verifier si une Evaluation exist deja ou non
    @Query("SELECT COUNT(e) FROM Evaluation e WHERE e.utilisateur = :utilisateur AND e.activite = :activite")
    int findExistingEvaluation(@Param("utilisateur") Utilisateur utilisateur, @Param("activite") Activite activite);

    //Modifie la note d'un Utilisateur pour une Evaluation d'Activite
    @Modifying
    @Query("UPDATE Evaluation e SET e.note = :note WHERE e.utilisateur = :utilisateur AND e.activite = :activite")
    void update(@Param("utilisateur") Utilisateur utilisateur, @Param("activite") Activite activite, @Param("note") double note);
}

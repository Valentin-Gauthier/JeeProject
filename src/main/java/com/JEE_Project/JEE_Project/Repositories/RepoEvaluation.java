package com.JEE_Project.JEE_Project.Repositories;

import com.JEE_Project.JEE_Project.Models.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoEvaluation extends JpaRepository<Evaluation, Long> {

    // Recupere la moyenne d'une Activite
    @Query("SELECT AVG(e.note) FROM Evaluation e WHERE e.activiteId = :activiteId")
    Double findMoyenneFromActivite(@Param("activiteId") long activiteId);

    // Recupere le nombre d'Evaluation d'un Utilisateur pour une Activite
    @Query("SELECT COUNT(e) FROM Evaluation e WHERE e.utilisateurId = :utilisateurId AND e.activiteId = :activiteId")
    int findExistingEvaluation(@Param("utilisateurId")long utilisateurId, @Param("activiteId")long activiteId);

    //Modifie la note d'un Utilisateur pour une Evaluation d'Activite
    @Modifying
    @Query("UPDATE Evaluation e SET e.note = :note WHERE e.utilisateurId = :utilisateurId AND e.activiteId = :activiteId")
    void update(@Param("utilisateurId") long utilisateurid, @Param("activiteId") long activiteId, @Param("note") double note);
}

package com.JEE_Project.JEE_Project.Repositories;

import com.JEE_Project.JEE_Project.Models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoUtilisateur extends JpaRepository<Utilisateur, Long> {

    Utilisateur findByEmail(String email);

    @Query("SELECT DISTINCT u FROM Utilisateur u LEFT JOIN FETCH u.evaluations WHERE u.utilisateurId = :utilisateurId")
    Utilisateur findUtilisateurEvaluations(@Param("utilisateurId") long utilisateurId);
}

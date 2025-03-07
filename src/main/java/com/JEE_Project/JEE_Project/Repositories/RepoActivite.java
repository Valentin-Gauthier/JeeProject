package com.JEE_Project.JEE_Project.Repositories;

import com.JEE_Project.JEE_Project.Models.Activite;
import com.JEE_Project.JEE_Project.Models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoActivite extends JpaRepository<Activite, Long> {


    // Rechercher les Activites en fonction de leur nom/description ou nom/description des pathologies associe
    @Query("SELECT DISTINCT a FROM Activite a LEFT JOIN a.pathologies ap LEFT JOIN ap.pathologie p WHERE a.nom LIKE %:search% OR a.description LIKE %:search% OR p.nom LIKE %:search% OR p.description LIKE %:search%")
    List<Activite> findActiviteFromSearch(@Param("search") String search);

    // Recherche une Activite via son Id
    Activite findActiviteByActiviteId(long activiteId);


    // Verifie si l'Utilisateur possede l'activite dans ses programmes
    @Query("SELECT COUNT(a) FROM Activite a INNER JOIN Programme_Activite pa ON pa.activite = a INNER JOIN Programme p ON p = pa.programme WHERE p.utilisateur = :utilisateur AND a = :activite")
    int findExistingActivite(@Param("utilisateur")Utilisateur utilisateur, @Param("activite") Activite activite);
}

package com.JEE_Project.JEE_Project.Repositories;

import com.JEE_Project.JEE_Project.Models.Activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoActivite extends JpaRepository<Activite, Long> {

    // Recuperer les Activites avec leurs Pathologies
    @Query("SELECT a, p FROM Activite a LEFT JOIN Activite_Pathologie ap ON a.activiteId = ap.activiteId LEFT JOIN Pathologie p ON ap.pathologieId = p.pathologieId WHERE a.nom LIKE %:name% OR a.description LIKE %:name% OR p.nom LIKE %:name% OR p.description LIKE %:name%")
    List<Object[]> findAllActiviteWithPathologies(@Param("name") String name);

    // Recuperer une Activite par son id avec ses Pathologies
    @Query("SELECT a, p FROM Activite a LEFT JOIN Activite_Pathologie ap ON a.activiteId = ap.activiteId LEFT JOIN Pathologie p ON ap.pathologieId = p.pathologieId WHERE a.activiteId = :activiteId")
    List<Object[]> findActiviteWithPathologies(long activiteId);

    // Recuperer toutes les Activites d'un Utilisateur
    @Query("SELECT a FROM Activite a INNER JOIN Programme_Activite pa ON pa.activiteId = a.activiteId INNER JOIN Programme p ON pa.programmeId = p.programmeId WHERE p.utilisateurId = :utilisateurId AND a.activiteId = :activiteId")
    List<Activite> findActivitesFromUtilisateurId(@Param("utilisateurId") long utilisateurId, @Param("activiteId") long activiteId);
}

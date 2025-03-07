package com.JEE_Project.JEE_Project.Repositories;

import com.JEE_Project.JEE_Project.Models.Pathologie;
import com.JEE_Project.JEE_Project.Models.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoPathologie extends JpaRepository<Pathologie, Long> {


    Pathologie findPathologiesByPathologieId(long pathologieId);

    // Retourne une liste des pathologies qui ont dans leurs nom ou description "name"
    @Query("SELECT p FROM Pathologie p WHERE p.nom LIKE %:name% OR p.description LIKE %:name%")
    Page<Pathologie> findByName(@Param("name") String name, @Param("pageable") Pageable pageable);

    // Retourne les Pathologies de l'utilisateur
    @Query("SELECT p FROM Pathologie p INNER JOIN Utilisateur_Pathologie up ON up.pathologie = p WHERE up.utilisateur = :utilisateur")
    List<Pathologie> findPathologiesFromUtilisateur(@Param("utilisateur")Utilisateur utilisateur);
}

package com.JEE_Project.JEE_Project.Repositories;

import com.JEE_Project.JEE_Project.Models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoUtilisateur extends JpaRepository<Utilisateur, Long> {

    Utilisateur findByEmail(String email);
}

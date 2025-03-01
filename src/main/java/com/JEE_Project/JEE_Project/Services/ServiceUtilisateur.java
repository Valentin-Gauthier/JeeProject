package com.JEE_Project.JEE_Project.Services;

import com.JEE_Project.JEE_Project.Models.Utilisateur;
import com.JEE_Project.JEE_Project.Repositories.RepoUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceUtilisateur {

    @Autowired
    private RepoUtilisateur repoUtilisateur;

    // Recuperer un Utilisateur via son mail (Connexion)
    public Utilisateur findByEmail(String email) {
        return repoUtilisateur.findByEmail(email);
    }

    // Creer un Utilisateur (Inscription)
    @Transactional
    public void saveUtilisateur(Utilisateur utilisateur) {
        repoUtilisateur.save(utilisateur);
    }
}

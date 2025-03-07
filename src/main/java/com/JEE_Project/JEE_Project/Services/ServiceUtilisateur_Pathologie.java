package com.JEE_Project.JEE_Project.Services;

import com.JEE_Project.JEE_Project.Models.Pathologie;
import com.JEE_Project.JEE_Project.Models.Utilisateur;
import com.JEE_Project.JEE_Project.Models.Utilisateur_Pathologie;
import com.JEE_Project.JEE_Project.Repositories.RepoUtilisateur_Pathologie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ServiceUtilisateur_Pathologie {

    @Autowired
    private RepoUtilisateur_Pathologie repoUtilisateur_Pathologie;


    @Transactional
    public void addPathologieForUtilisateur(Utilisateur_Pathologie utilisateurPathologie) {
        repoUtilisateur_Pathologie.save(utilisateurPathologie);
    }
    @Transactional
    public void removePahtologieForUtilisateur(Pathologie pathologie, Utilisateur utilisateur) {
        repoUtilisateur_Pathologie.removePahtologieForUtilisateur(pathologie, utilisateur);
    }

    // Recuperer les Pathologie d'un Utilisateur
    public List<Utilisateur_Pathologie> getPathologies(Utilisateur utilisateur) {
        return repoUtilisateur_Pathologie.findPathologies(utilisateur);
    }
}

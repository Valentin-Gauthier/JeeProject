package com.JEE_Project.JEE_Project.Services;

import com.JEE_Project.JEE_Project.Repositories.RepoUtilisateur_Pathologie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ServiceUtilisateur_Pathologie {

    @Autowired
    private RepoUtilisateur_Pathologie repoUtilisateur_Pathologie;


    @Transactional
    public void addPathologieForUtilisateur(long pathologieId, long utilisateurId) {
        repoUtilisateur_Pathologie.addPathologieForUtilisateur(pathologieId, utilisateurId);
    }
    @Transactional
    public void removePahtologieForUtilisateur(long pathologieId, long utilisateurId) {
        repoUtilisateur_Pathologie.removePahtologieForUtilisateur(pathologieId, utilisateurId);
    }
}

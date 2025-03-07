package com.JEE_Project.JEE_Project.Services;

import com.JEE_Project.JEE_Project.Models.Activite;
import com.JEE_Project.JEE_Project.Models.Utilisateur;
import com.JEE_Project.JEE_Project.Repositories.RepoActivite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceActivite {

    @Autowired
    private RepoActivite repoActivite;

    // Verifie si l'Utilisateur possede l'activite dans ses programmes
    public boolean ActiviteExist(Utilisateur utilisateur, Activite activite) {
        int exist =  repoActivite.findExistingActivite(utilisateur, activite);
        return (exist != 0);
    }

    //Retourne une liste d'Activites d'apres une Recherche
    public List<Activite> getActivitesFromSearch(String search){
        return repoActivite.findActiviteFromSearch(search);
    }

    // Retourne une Activite d'apres son Id
    public Activite getActiviteById(long id){
        return repoActivite.findActiviteByActiviteId(id);
    }

}

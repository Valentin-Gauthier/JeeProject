package com.JEE_Project.JEE_Project.Services;

import com.JEE_Project.JEE_Project.Models.Pathologie;
import com.JEE_Project.JEE_Project.Models.Utilisateur;
import com.JEE_Project.JEE_Project.Repositories.RepoPathologie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicePathologie {

    @Autowired
    private RepoPathologie repoPathologie;

    // Recuperer une Pathologie avec son Id
    public Pathologie getPathologie(long pathologieId) {
        return repoPathologie.findPathologiesByPathologieId(pathologieId);
    }

    // Recuperer les 5 Pathologies qui correspond à la recherche
    public List<Pathologie> getAllPathologiesByName(String name) {
        // limite le nombre de resultat
        Pageable pageable = PageRequest.of(0, 5);
        Page<Pathologie> page = repoPathologie.findByName(name, pageable);
        return page.getContent();
    }

    // Recuperer les Pathologies associé à un Utilisateur
    public List<Pathologie> getPahtologiesByUtilisateurId(Utilisateur utilisateur) {
        return repoPathologie.findPathologiesFromUtilisateur(utilisateur);
    }
}

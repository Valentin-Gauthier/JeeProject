package com.JEE_Project.JEE_Project.Services;

import com.JEE_Project.JEE_Project.Models.Programme;
import com.JEE_Project.JEE_Project.Models.Utilisateur;
import com.JEE_Project.JEE_Project.Repositories.RepoProgramme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceProgramme {

    @Autowired
    private RepoProgramme repoProgramme;

    // Recuperer les Programmes d'un Utilisateur
    public List<Programme> getProgrammes(Utilisateur utilisateur){
        return repoProgramme.findProgrammes(utilisateur);
    }

    // Recuperer un Programme avec son Id
    public Programme getProgramme(long programmeId) {
        return repoProgramme.findProgrammeByProgrammeId(programmeId);
    }

    // Creer un Programme pour un Utilisateur
    @Transactional
    public void createProgramme(Programme programme) {
        repoProgramme.save(programme);
    }

    // Supprimer un Programme
    @Transactional
    public void deleteProgramme(Programme programme){
        repoProgramme.deleteProgramme(programme);
    }
}

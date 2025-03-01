package com.JEE_Project.JEE_Project.Services;

import com.JEE_Project.JEE_Project.Models.Activite;
import com.JEE_Project.JEE_Project.Models.Programme;
import com.JEE_Project.JEE_Project.Repositories.RepoActivite;
import com.JEE_Project.JEE_Project.Repositories.RepoProgramme;
import com.JEE_Project.JEE_Project.Utils.ProgrammeWithActivites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServiceProgramme {

    @Autowired
    private RepoProgramme repoProgramme;

    @Transactional
    public void deleteProgramme(long programmeId) {
        repoProgramme.deleteProgramme(programmeId);
    }

    @Transactional
    public void createProgramme(long utilisateurId){
        repoProgramme.save(new Programme(utilisateurId));
    }

    // Retourner un programmes avec toutes les activités incluse dedans
    public List<ProgrammeWithActivites> getAllProgrammes(long id) {

        Map<Long, ProgrammeWithActivites> programmesMap = new HashMap<>();

        //Recuperer les données
        List<Object[]> results = repoProgramme.findProgrammeWithActivite(id);

        for (Object[] row : results) {
            Programme programme = (Programme) row[0];
            Activite activite = (Activite) row[1];


            long programmeId = programme.getProgrammeId();

            // Vérifier si le programme existe déjà dans la Map
            if (programmesMap.containsKey(programmeId)) {
                programmesMap.get(programmeId).getActivites().add(activite);
            } else {
                ProgrammeWithActivites p = new ProgrammeWithActivites(programmeId);
                if(activite != null) {
                    p.addActivite(activite);
                }
                programmesMap.put(programmeId, p);
            }
        }
        return new ArrayList<>(programmesMap.values());
    }

}

package com.JEE_Project.JEE_Project.Services;

import com.JEE_Project.JEE_Project.Models.Activite;
import com.JEE_Project.JEE_Project.Models.Pathologie;
import com.JEE_Project.JEE_Project.Repositories.RepoActivite;
import com.JEE_Project.JEE_Project.Repositories.RepoEvaluation;
import com.JEE_Project.JEE_Project.Utils.ActiviteWithPathologies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServiceActivite {

    @Autowired
    private RepoActivite repoActivite;
    @Autowired
    private RepoEvaluation repoEvaluation;

    public List<Activite> getActivitesFromUtilisateurId(long utilisateurId, long activiteId) {
        return repoActivite.findActivitesFromUtilisateurId(utilisateurId, activiteId);
    }

    public ActiviteWithPathologies getActiviteByIdWithPathologies(long activiteId) {

        List<Object[]> results = repoActivite.findActiviteWithPathologies(activiteId);

        ActiviteWithPathologies activite = new ActiviteWithPathologies();

        for (Object[] row : results) {
           Activite a = (Activite) row[0];
           Pathologie p = (Pathologie) row[1];

           activite.setActivite(a);
           if(p != null) {
               activite.getPathologies().add(p);
           }
        }
        Double moyenne = repoEvaluation.findMoyenneFromActivite(activite.getActivite().getActiviteId());
        if (moyenne != null) {
            moyenne = Math.round(moyenne * 10.0) / 10.0;
            activite.setMoyenne(moyenne);
        }

        return activite;
    }

    public List<ActiviteWithPathologies> findActivites(String recherche) {

        Map<Long, ActiviteWithPathologies> activitesMap = new HashMap<>();

        // Recuperer les données
        List<Object[]> results = repoActivite.findAllActiviteWithPathologies(recherche);

        // Assembler les données
        for (Object[] row : results) {
            // Recuperer nos Objets
            Activite activite = (Activite) row[0];
            Pathologie pathologie = (Pathologie) row[1];

            // si deja dans la map on ajoute la pathologie
            Long activiteId = activite.getActiviteId();
            if(activitesMap.containsKey(activiteId) && activitesMap.get(activiteId).getPathologies() != null) {
                activitesMap.get(activiteId).getPathologies().add(pathologie);
            } else {
                // ajoute dans la map
                ActiviteWithPathologies AwP = new ActiviteWithPathologies();
                AwP.setActivite(activite);
                if(pathologie != null) {
                    AwP.getPathologies().add(pathologie);
                }
                activitesMap.put(activiteId, AwP);
            }
        }
        List<ActiviteWithPathologies> activitesWithPathologies = new ArrayList<>(activitesMap.values());

        for (ActiviteWithPathologies activite : activitesWithPathologies) {
            Double moyenne = repoEvaluation.findMoyenneFromActivite(activite.getActivite().getActiviteId());
            if (moyenne != null) {
                moyenne = Math.round(moyenne * 10.0) / 10.0;
                activite.setMoyenne(moyenne);
            }
        }
        return activitesWithPathologies;
    }
}

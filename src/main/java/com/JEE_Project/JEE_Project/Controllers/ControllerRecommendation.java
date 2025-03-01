package com.JEE_Project.JEE_Project.Controllers;

import com.JEE_Project.JEE_Project.Models.Pathologie;
import com.JEE_Project.JEE_Project.Models.Utilisateur;
import com.JEE_Project.JEE_Project.Services.ServiceActivite;
import com.JEE_Project.JEE_Project.Services.ServicePathologie;
import com.JEE_Project.JEE_Project.Utils.ActiviteWithPathologies;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/Recommendations")
public class ControllerRecommendation {

    @Autowired
    private ServicePathologie servicePathologie;
    @Autowired
    private ServiceActivite serviceActivite;

    @GetMapping("")
    public String afficherRecommendation(Model model, HttpSession session) {
        // Recuperer les pathologies de l'Utilisateur
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

        List<Pathologie> utilisateurPathologies = servicePathologie.getPahtologiesByUtilisateurId(utilisateur.getUtilisateurId());

        // Rechercher les activités pour calculer un score pour la recommendation
        List<ActiviteWithPathologies> allActivites = serviceActivite.findActivites("");
        Map<ActiviteWithPathologies, Integer> activitesScore = new HashMap<>();
        for(ActiviteWithPathologies activite : allActivites) {
            int score = 0;
            for(Pathologie activitePathologie : activite.getPathologies()) {
                if(utilisateurPathologies.contains(activitePathologie)) {
                    score += 1;
                }
            }
            activitesScore.put(activite, score);
        }

        // Trier les activites en fonction des scores les plus eleves
        List<ActiviteWithPathologies> recommendations = allActivites.stream()
                // garde uniquement les activitées ayant un score > 0
                .filter(a -> activitesScore.get(a) > 0)
                // classe en ordre decroissant
                .sorted((a1, a2) -> activitesScore.get(a2) - activitesScore.get(a1))
                // convertis en list
                .collect(Collectors.toList());

        model.addAttribute("recommendations", recommendations);
        return "Recommendations";
    }
}

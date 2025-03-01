package com.JEE_Project.JEE_Project.Controllers;

import com.JEE_Project.JEE_Project.Models.Pathologie;
import com.JEE_Project.JEE_Project.Models.Utilisateur;
import com.JEE_Project.JEE_Project.Services.ServicePathologie;
import com.JEE_Project.JEE_Project.Services.ServiceProgramme_Activite;
import com.JEE_Project.JEE_Project.Services.ServiceUtilisateur_Pathologie;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/Profil")
public class ControllerProfil {

    @Autowired
    private ServicePathologie servicePathologies;
    @Autowired
    private ServiceUtilisateur_Pathologie serviceUtilisateur_Pathologie;

    @GetMapping({"", "/pathologie"})
    public String afficherProfil(@RequestParam(value = "pathologieName", defaultValue = "") String name, Model model, HttpSession httpSession) {
        // Recuperer l'utilisateur
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");

        if(utilisateur != null) {

            // Recuperer les Pahtologies de l'utilisateur
            List<Pathologie> pathologiesUtilisateur = servicePathologies.getPahtologiesByUtilisateurId(utilisateur.getUtilisateurId());
            model.addAttribute("pathologiesUtilisateur", pathologiesUtilisateur);

            // Afficher des Pathologies Rechercher
            List<Pathologie> pathologiesRechercher = servicePathologies.getAllPathologiesByName(name);
            model.addAttribute("pathologies", pathologiesRechercher);
        }
        return "Profil";
    }
    // Ajouter la pathologie aux pathologies associé a l'utilisateur
    @PostMapping("/addPathologie")
    public String addPahtologie(@RequestParam("pathologieId") long pathologieId, HttpSession httpSession) {
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        serviceUtilisateur_Pathologie.addPathologieForUtilisateur(pathologieId, utilisateur.getUtilisateurId());
        return "redirect:/Profil";
    }
    // Supprimer la pathologie des pathologies associé à l'utilisateur
    @PostMapping("/deletePathologie")
    public String deletePathologie(@RequestParam("pathologieId") long pathologieId, HttpSession httpSession) {
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        serviceUtilisateur_Pathologie.removePahtologieForUtilisateur(pathologieId, utilisateur.getUtilisateurId());
        return "redirect:/Profil";
    }



}

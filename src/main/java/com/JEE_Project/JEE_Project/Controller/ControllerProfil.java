package com.JEE_Project.JEE_Project.Controller;

import com.JEE_Project.JEE_Project.Models.Pathologie;
import com.JEE_Project.JEE_Project.Models.Utilisateur;
import com.JEE_Project.JEE_Project.Models.Utilisateur_Pathologie;
import com.JEE_Project.JEE_Project.Services.ServicePathologie;
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
            List<Pathologie> pathologiesUtilisateur = servicePathologies.getPahtologiesByUtilisateurId(utilisateur);
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
        Pathologie pathologie = servicePathologies.getPathologie(pathologieId);

        Utilisateur_Pathologie utilisateurPathologie = new Utilisateur_Pathologie();
        utilisateurPathologie.setUtilisateur(utilisateur);
        utilisateurPathologie.setPathologie(pathologie);

        // verifier que l'Utilisateur ne possede pas deja la Pathologie
        boolean alreadyExists = false;
        for(Utilisateur_Pathologie up : utilisateur.getPathologies()){
            if (up.getPathologie().getPathologieId() == pathologieId) {
                alreadyExists = true;
                break;
            }
        }
        if(!alreadyExists) {
            serviceUtilisateur_Pathologie.addPathologieForUtilisateur(utilisateurPathologie);
            // Actualiser l'Utilisateur
            utilisateur.getPathologies().add(utilisateurPathologie);
        }
        return "redirect:/Profil";
    }
    // Supprimer la pathologie des pathologies associé à l'utilisateur
    @PostMapping("/deletePathologie")
    public String deletePathologie(@RequestParam("pathologieId") long pathologieId, HttpSession httpSession) {
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        Pathologie pathologie = servicePathologies.getPathologie(pathologieId);

        serviceUtilisateur_Pathologie.removePahtologieForUtilisateur(pathologie, utilisateur);

        // Actualiser l'Utilisateur
        utilisateur.getPathologies().removeIf(up -> up.getPathologie().getPathologieId() == pathologieId);

        return "redirect:/Profil";
    }



}

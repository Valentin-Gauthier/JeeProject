package com.JEE_Project.JEE_Project.Controller;

import com.JEE_Project.JEE_Project.Models.Activite;
import com.JEE_Project.JEE_Project.Models.Programme;
import com.JEE_Project.JEE_Project.Models.Utilisateur;
import com.JEE_Project.JEE_Project.Services.ServiceActivite;
import com.JEE_Project.JEE_Project.Services.ServiceProgramme;
import com.JEE_Project.JEE_Project.Services.ServiceProgramme_Activite;
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
@RequestMapping("/Programs")
public class ControllerPrograms {

    @Autowired
    private ServiceProgramme serviceProgrammes;
    @Autowired
    private ServiceProgramme_Activite serviceProgramme_Activite;
    @Autowired
    private ServiceActivite serviceActivite;

    @GetMapping("")
    public String afficherPrograms(Model model, HttpSession session) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

        // Recuperer les programmes de l'utilisateur
        List<Programme> programmes = serviceProgrammes.getProgrammes(utilisateur);
        model.addAttribute("programmes", programmes);

        return "Programs";
    }

    // Supprime une Activite d'un Programme
    @PostMapping("/deleteActiviteFromProgramme")
    public String deleteActiviteFromProgramme(@RequestParam("programmeId") long programmeId, @RequestParam("activiteId") long activiteId) {

        Programme programme = serviceProgrammes.getProgramme(programmeId);
        Activite activite = serviceActivite.getActiviteById(activiteId);

        serviceProgramme_Activite.deleteActiviteFromProgramme(programme, activite);
        return "redirect:/Programs";
    }

    // Supprime un Programme
    @PostMapping("/deleteProgramme")
    public String deleteProgramme(@RequestParam("programmeId") long programmeId, HttpSession httpSession) {

        Programme programme = serviceProgrammes.getProgramme(programmeId);

        serviceProgramme_Activite.deleteAllActiviteFromProgramme(programme);
        serviceProgrammes.deleteProgramme(programme);

        //Actualiser l'Utilisateur
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        utilisateur.getProgrammes().removeIf(p -> p.getProgrammeId() == programmeId);

        return "redirect:/Programs";
    }

    // Creer un nouveau Programme associe a l'utilisateur
    @PostMapping("/createProgramme")
    public String createProgramme(HttpSession httpSession) {
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");

        Programme programme = new Programme();
        programme.setUtilisateur(utilisateur);

        serviceProgrammes.createProgramme(programme);

        // Actualiser l'Utilisateur
        utilisateur.getProgrammes().add(programme);

        return "redirect:/Programs";
    }
}

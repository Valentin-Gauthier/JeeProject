package com.JEE_Project.JEE_Project.Controllers;

import com.JEE_Project.JEE_Project.Models.Utilisateur;
import com.JEE_Project.JEE_Project.Services.ServiceProgramme;
import com.JEE_Project.JEE_Project.Services.ServiceProgramme_Activite;
import com.JEE_Project.JEE_Project.Utils.ProgrammeWithActivites;
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

    @GetMapping("")
    public String afficherPrograms(Model model, HttpSession session) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

        // Recuperer les programmes de l'utilisateur
        List<ProgrammeWithActivites> programmes = serviceProgrammes.getAllProgrammes(utilisateur.getUtilisateurId());
        model.addAttribute("programmes", programmes);

        return "Programs";
    }

    // Supprime une Activite d'un Programme
    @PostMapping("/deleteActiviteFromProgramme")
    public String deleteActiviteFromProgramme(@RequestParam("programmeId") long programmeId, @RequestParam("activiteId") long activiteId) {
        serviceProgramme_Activite.deleteActiviteFromProgramme(programmeId, activiteId);
        return "redirect:/Programs";
    }

    // Supprime un Programme
    @PostMapping("/deleteProgramme")
    public String deleteProgramme(@RequestParam("programmeId") long programmeId) {
        serviceProgramme_Activite.deleteAllActiviteFromProgramme(programmeId);
        serviceProgrammes.deleteProgramme(programmeId);
        return "redirect:/Programs";
    }

    // Creer un nouveau Programme associe a l'utilisateur
    @PostMapping("/createProgramme")
    public String createProgramme(HttpSession httpSession) {
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        serviceProgrammes.createProgramme(utilisateur.getUtilisateurId());
        return "redirect:/Programs";
    }
}

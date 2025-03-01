package com.JEE_Project.JEE_Project.Controllers;

import com.JEE_Project.JEE_Project.Models.Activite;
import com.JEE_Project.JEE_Project.Models.Evaluation;
import com.JEE_Project.JEE_Project.Models.Programme_Activite;
import com.JEE_Project.JEE_Project.Models.Utilisateur;
import com.JEE_Project.JEE_Project.Services.ServiceActivite;
import com.JEE_Project.JEE_Project.Services.ServiceEvaluation;
import com.JEE_Project.JEE_Project.Services.ServiceProgramme;
import com.JEE_Project.JEE_Project.Services.ServiceProgramme_Activite;
import com.JEE_Project.JEE_Project.Utils.ActiviteWithPathologies;
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
@RequestMapping("/Activity")
public class ControllerActivity {

    @Autowired
    private ServiceActivite serviceActivite;
    @Autowired
    private ServiceEvaluation serviceEvaluation;
    @Autowired
    private ServiceProgramme serviceProgramme;
    @Autowired
    private ServiceProgramme_Activite serviceProgramme_Activite;

    @GetMapping("")
    public String afficherActivity(@RequestParam("id") long activiteId, Model model, HttpSession session) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
        ActiviteWithPathologies activite = serviceActivite.getActiviteByIdWithPathologies(activiteId);
        model.addAttribute("activite", activite);

        // Recuperer les programmes de l'utilisateur
        if(session.getAttribute("utilisateur") != null) {
            List<ProgrammeWithActivites> programmes = serviceProgramme.getAllProgrammes(utilisateur.getUtilisateurId());
            model.addAttribute("programmes", programmes);

            // Verifier si l'Utilisateur à cette Activite
            List<Activite> utilisateurActivite = serviceActivite.getActivitesFromUtilisateurId(utilisateur.getUtilisateurId(), activiteId);
            if(utilisateurActivite != null && !utilisateurActivite.isEmpty()) {
                model.addAttribute("utilisateurActivite", utilisateurActivite);
            }
        }
        return "Activity";
    }

    // Ajouter une note
    @PostMapping("/addEvaluation")
    public String addEvaluation(@RequestParam("note") double note,@RequestParam("activiteId") long activiteId ,HttpSession session, Model model) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
        // Verifier la note
        if( note >= 0 && note <= 5) {
            note = Math.round(note * 10.0) / 10.0; // mettre un seul chiffre apres la virgule
            Evaluation evaluation = new Evaluation(utilisateur.getUtilisateurId(), activiteId, note);
            // verifier si une note existe deja pour cette utilisateur sur cette activite
            boolean exist = serviceEvaluation.evaluationExist(utilisateur.getUtilisateurId(), activiteId);
            if(exist) {
                serviceEvaluation.updateEvaluation(evaluation);
            }else {
                serviceEvaluation.addEvaluation(evaluation);
            }
        } else {
            model.addAttribute("erreur", "Le note n'est pas conforme");
        }
        return "redirect:/Activity?id=" + activiteId;
    }

    // Ajouter une activité à un programme
    @PostMapping("/addActiviteForProgramme")
    public String addActiviteForProgramme(@RequestParam("programmeId") long programmeId,@RequestParam("activiteId") long acitiviteId) {
        Programme_Activite programmesActivite = new Programme_Activite(programmeId, acitiviteId);
        serviceProgramme_Activite.addActiviteForProgramme(programmesActivite);
        return "redirect:/Activity?id=" + acitiviteId;
    }
}

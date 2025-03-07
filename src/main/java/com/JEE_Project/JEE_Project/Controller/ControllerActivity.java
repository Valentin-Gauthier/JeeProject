package com.JEE_Project.JEE_Project.Controller;

import com.JEE_Project.JEE_Project.Models.*;
import com.JEE_Project.JEE_Project.Services.ServiceActivite;
import com.JEE_Project.JEE_Project.Services.ServiceEvaluation;
import com.JEE_Project.JEE_Project.Services.ServiceProgramme;
import com.JEE_Project.JEE_Project.Services.ServiceProgramme_Activite;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/Activity")
@SessionAttributes("utilisateur")
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

        Activite activite = serviceActivite.getActiviteById(activiteId);
        model.addAttribute("activite", activite);

        // Recuperer l'utilisateur afin de savoir si il à l'activite et si il l'a deja noté
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

        if(utilisateur != null) {
            for (Evaluation evaluation : utilisateur.getEvaluations()) {
                if(evaluation.getActivite().getActiviteId() == activite.getActiviteId()) {
                    model.addAttribute("evaluation", evaluation);
                }
            }
            boolean utilisateurActivite = serviceActivite.ActiviteExist(utilisateur, activite);
            if(utilisateurActivite) {
                model.addAttribute("utilisateurActivite", activite);
            }
        }
        return "Activity";
    }

    // Ajouter une note
    @PostMapping("/addEvaluation")
    public String addEvaluation(@RequestParam("note") double note,@RequestParam("activiteId") long activiteId ,HttpSession session, Model model) {

        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
        Activite activite = serviceActivite.getActiviteById(activiteId);

        // Verifier la note
        if( note >= 0 && note <= 5) {
            // Normalement pas necessaire
            note = Math.round(note * 10.0) / 10.0; // mettre un seul chiffre apres la virgule

            Evaluation evaluation = new Evaluation();
            evaluation.setUtilisateur(utilisateur);
            evaluation.setActivite(activite);
            evaluation.setNote(note);

            // verifier si une note existe deja pour cette utilisateur sur cette activite
            boolean exist = serviceEvaluation.evaluationExist(evaluation);

            if(exist) {
                // Si elle exist on modifie la note
                serviceEvaluation.updateEvaluation(evaluation);
                for (Evaluation eval : utilisateur.getEvaluations()) {
                    if(eval.getActivite().getActiviteId() == activite.getActiviteId()) {
                        eval.setNote(note);
                    }
                }
            }else {
                // Si elle n'existe pas on ajoute une nouvelle Evaluation
                serviceEvaluation.addEvaluation(evaluation);
                utilisateur.getEvaluations().add(evaluation);
            }
        } else {
            model.addAttribute("erreur", "Le note n'est pas conforme");
        }
        return "redirect:/Activity?id=" + activiteId;
    }

    // Ajouter une activité à un programme
    @PostMapping("/addActiviteForProgramme")
    public String addActiviteForProgramme(@RequestParam("programmeId") long programmeId,@RequestParam("activiteId") long acitiviteId) {

        Activite activite = serviceActivite.getActiviteById(acitiviteId);
        Programme programme = serviceProgramme.getProgramme(programmeId);

        Programme_Activite pa = new Programme_Activite(programme,activite);

        serviceProgramme_Activite.addActiviteForProgramme(pa);

        return "redirect:/Activity?id=" + acitiviteId;
    }
}

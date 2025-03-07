package com.JEE_Project.JEE_Project.Controller;

import com.JEE_Project.JEE_Project.Models.Evaluation;
import com.JEE_Project.JEE_Project.Models.Programme;
import com.JEE_Project.JEE_Project.Models.Utilisateur;
import com.JEE_Project.JEE_Project.Models.Utilisateur_Pathologie;
import com.JEE_Project.JEE_Project.Services.ServiceEvaluation;
import com.JEE_Project.JEE_Project.Services.ServiceProgramme;
import com.JEE_Project.JEE_Project.Services.ServiceUtilisateur;
import com.JEE_Project.JEE_Project.Services.ServiceUtilisateur_Pathologie;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/Login")
public class ControllerLogin {

    @Autowired
    private ServiceUtilisateur serviceUtilisateur;
    @Autowired
    private ServiceEvaluation serviceEvaluation;
    @Autowired
    private ServiceProgramme serviceProgramme;
    @Autowired
    private ServiceUtilisateur_Pathologie serviceUtilisateur_Pathologie;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @GetMapping("")
    public String afficherLogin(){
        return "Login";
    }

    // Connexion
    @PostMapping("/Connexion")
    public String connexion(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {

        Utilisateur utilisateur = serviceUtilisateur.findByEmail(email);
        if (utilisateur != null && encoder.matches(password, utilisateur.getPassword())) {
            // Charger l'utilisateur avec toutes ses données
            List<Evaluation> evals = serviceEvaluation.getEvaluations(utilisateur);
            List<Programme> programmes = serviceProgramme.getProgrammes(utilisateur);
            List<Utilisateur_Pathologie> pathologies = serviceUtilisateur_Pathologie.getPathologies(utilisateur);
            utilisateur.setEvaluations(evals);
            utilisateur.setProgrammes(programmes);
            utilisateur.setPathologies(pathologies);

            session.setAttribute("utilisateur", utilisateur);
            return "redirect:/Home";
        }else{
            model.addAttribute("message", "Email ou mot de passe incorrect !");
            return "Login";
        }
    }

    @GetMapping("/Deconnexion")
    public String deconnexion(HttpSession session){
        session.invalidate();
        return "redirect:/Home";
    }
}

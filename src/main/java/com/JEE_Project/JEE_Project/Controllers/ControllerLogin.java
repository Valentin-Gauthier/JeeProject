package com.JEE_Project.JEE_Project.Controllers;

import com.JEE_Project.JEE_Project.Models.Utilisateur;
import com.JEE_Project.JEE_Project.Services.ServiceUtilisateur;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Login")
public class ControllerLogin {

    @Autowired
    private ServiceUtilisateur serviceUtilisateur;
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

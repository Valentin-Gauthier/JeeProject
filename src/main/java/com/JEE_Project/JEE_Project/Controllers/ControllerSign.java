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

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/Sign")
public class ControllerSign {

    @Autowired
    private ServiceUtilisateur serviceUtilisateur;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    @GetMapping("")
    public String afficherSign(){
        return "Sign";
    }

    @PostMapping("/Inscription")
    public String traiterInscription(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom,@RequestParam("age") int age, @RequestParam("genre") String genre, @RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session){
        try {
            // ---  Verifier les données ---
            // erreurs
            Map<String, String> erreurs = new HashMap<>();
            // nom ->
            if (nom.isEmpty() || nom.length() > 50) {
                erreurs.put("e_nom", "Le nom est obligatoire et doit contenir maximum 50 caractères");
            }
            if (prenom.isEmpty() || prenom.length() > 50) {
                erreurs.put("e_prenom", "Le prénom est obligatoire et doit contenir maximum 50 caractères");
            }
            if (age <= 0) {
                erreurs.put("e_age", "Vous devez entrer un âge valide");
            }
            if (genre.isEmpty() || !genre.equals("Homme") && !genre.equals("Femme") && !genre.equals("Autre")) {
                erreurs.put("e_genre", "Le genre est obligatoire");
            }

            if (email.isEmpty()) {
                erreurs.put("e_email", "L'email est obligatoire ");
            }
            //verifier que l'email est unique
            Utilisateur utilisateurExistant = serviceUtilisateur.findByEmail(email);

            if (utilisateurExistant != null) {
                String emailExist = utilisateurExistant.getEmail();
                if (emailExist != null && !emailExist.isEmpty()) {
                    erreurs.put("e_email", "Cet email est déjà utilisé !");
                }
            }
            if (password.isEmpty()) {
                erreurs.put("e_password", "Un mot de passe est obligatoire");
            }

            // Si il y a des erreurs on retourne sur inscription avec les erreurs qui correspondent
            if (!erreurs.isEmpty()) {
                model.addAttribute("erreurs", erreurs);
                return "Sign";
            } else {
                // sinon on ajoute l'utilisateur
                Utilisateur utilisateur = new Utilisateur(nom, prenom, age, genre, email, password);
                // Hasher le mdp
                utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));


                serviceUtilisateur.saveUtilisateur(utilisateur);

                // enrregistre dans une session
                session.setAttribute("utilisateur", utilisateur);
                // rediriger sur la page
                return "redirect:/Home";
            }

        } catch (Exception e) {
            model.addAttribute("errorMessage", "Une erreur est survenue. Veuillez réessayer.");
            return "Sign";
        }
    }
}

package com.JEE_Project.JEE_Project.Controller;

import com.JEE_Project.JEE_Project.Models.Activite;
import com.JEE_Project.JEE_Project.Services.ServiceActivite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/Home")
public class ControllerHome {

    @Autowired
    private ServiceActivite serviceActivite;

    @GetMapping({"", "/Search"})
    public String afficherHome(@RequestParam(value = "recherche", defaultValue = "") String recherche, Model model) {

        List<Activite> activites = serviceActivite.getActivitesFromSearch(recherche);
        model.addAttribute("activites", activites);

        return "Home";
    }
}

package com.JEE_Project.JEE_Project.Utils;

import com.JEE_Project.JEE_Project.Models.Activite;

import java.util.ArrayList;
import java.util.List;

public class ProgrammeWithActivites {

    private long programmesId;
    private List<Activite> activites = new ArrayList<>();
    private double moyenne;

    public ProgrammeWithActivites() {}

    public ProgrammeWithActivites(long id) {
        this.programmesId = id;
    }

    // Ajouter une activite a la liste
    public void addActivite(Activite activite) {
        this.activites.add(activite);
    }

    public List<Activite> getActivites() { return activites; }
    public long getProgrammesId() { return programmesId; }
    public double getMoyenne() { return moyenne; }

    public void setActivites(List<Activite> activites) { this.activites = activites; }
    public void setMoyenne(double moyenne) { this.moyenne = moyenne; }
}

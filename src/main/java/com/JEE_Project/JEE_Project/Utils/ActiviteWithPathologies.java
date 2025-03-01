package com.JEE_Project.JEE_Project.Utils;

import com.JEE_Project.JEE_Project.Models.Activite;
import com.JEE_Project.JEE_Project.Models.Pathologie;

import java.util.ArrayList;
import java.util.List;

public class ActiviteWithPathologies {

    private Activite activite;
    private List<Pathologie> pathologies = new ArrayList<>();
    private double moyenne = 0.0;

    public ActiviteWithPathologies() {}


    public Activite getActivite() { return activite; }
    public List<Pathologie> getPathologies() { return pathologies; }
    public double getMoyenne() { return moyenne; }

    public void setActivite(Activite activite) { this.activite = activite; }
    public void setPathologies(List<Pathologie> pathologies) { this.pathologies = pathologies; }
    public void setMoyenne(double moyenne) { this.moyenne = moyenne; }
}

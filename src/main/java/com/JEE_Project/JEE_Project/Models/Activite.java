package com.JEE_Project.JEE_Project.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Activites")
public class Activite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long activiteId;
    private String nom;
    private String description;
    private String address;


    @OneToMany(mappedBy = "activite")
    private List<Evaluation> evaluations;

    @OneToMany(mappedBy = "activite")
    private List<Programme_Activite> programmes;

    @OneToMany(mappedBy = "activite")
    private List<Activite_Pathologie> pathologies;


    public Activite() {}

    public Activite(long activiteId, String nom, String description, String address) {
        this.activiteId = activiteId;
        this.nom = nom;
        this.description = description;
        this.address = address;
    }

    @Transient //
    public double getMoyenne(){
        if (evaluations == null || evaluations.isEmpty()){
            return 0.0;
        }
        double moyenne = evaluations.stream()
                .mapToDouble(Evaluation::getNote)
                .average()
                .orElse(0.0);

        return Math.round(moyenne * 10.0) / 10.0;
    }

    public long getActiviteId() { return this.activiteId; }
    public String getNom() { return this.nom; }
    public String getDescription() { return this.description; }
    public String getAddress() { return this.address; }
    public List<Evaluation> getEvaluations() { return this.evaluations; }
    public List<Programme_Activite> getProgrammes() { return this.programmes; }
    public List<Activite_Pathologie> getPathologies() { return this.pathologies; }

    public void setActiviteId(long activiteId) { this.activiteId = activiteId; }
    public void setNom(String nom) { this.nom = nom; }
    public void setDescription(String description) { this.description = description; }
    public void setAddress(String address) { this.address = address; }
    public void setEvaluations(List<Evaluation> evaluations) { this.evaluations = evaluations; }
    public void setProgrammes(List<Programme_Activite> programmes) { this.programmes = programmes; }
    public void setActivites(List<Activite_Pathologie> pathologies) { this.pathologies = pathologies; }
}

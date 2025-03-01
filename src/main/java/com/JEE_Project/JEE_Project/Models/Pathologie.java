package com.JEE_Project.JEE_Project.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Pathologies")
public class Pathologie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pathologieId;
    private String nom;
    private String description;

    public Pathologie() {}

    public Pathologie(long pathologieId, String nom, String description) {
        this.pathologieId = pathologieId;
        this.nom = nom;
        this.description = description;
    }

    public long getPathologieId() { return pathologieId; }
    public String getNom() { return nom; }
    public String getDescription() { return description; }

    public  void setPathologieId(long pathologieId) { this.pathologieId = pathologieId; }
    public  void setNom(String nom) { this.nom = nom; }
    public  void setDescription(String description) { this.description = description; }
}

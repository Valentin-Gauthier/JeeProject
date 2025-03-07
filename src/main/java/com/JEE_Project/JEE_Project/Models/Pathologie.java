package com.JEE_Project.JEE_Project.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Pathologies")
public class Pathologie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pathologieId;

    private String nom;
    private String description;

    @OneToMany(mappedBy = "pathologie", cascade = CascadeType.ALL)
    private List<Utilisateur_Pathologie> pathologiesUtilisateur;

    @OneToMany(mappedBy = "pathologie", cascade = CascadeType.ALL)
    private List<Activite_Pathologie> pathologiesActivite;

    public Pathologie() {}

    public Pathologie(long pathologieId, String nom, String description) {
        this.pathologieId = pathologieId;
        this.nom = nom;
        this.description = description;
    }

    public long getPathologieId() { return pathologieId; }
    public String getNom() { return nom; }
    public String getDescription() { return description; }
    public List<Utilisateur_Pathologie> getPathologies() {return pathologiesUtilisateur;}
    public List<Activite_Pathologie> getPathologiesActivite() {return pathologiesActivite;}

    public  void setPathologieId(long pathologieId) { this.pathologieId = pathologieId; }
    public  void setNom(String nom) { this.nom = nom; }
    public  void setDescription(String description) { this.description = description; }
    public void setPathologiesUtilisateur(List<Utilisateur_Pathologie> pathologies) {this.pathologiesUtilisateur = pathologies;}
    public void setPathologiesActivite(List<Activite_Pathologie> pathologies) {this.pathologiesActivite = pathologies;}

}

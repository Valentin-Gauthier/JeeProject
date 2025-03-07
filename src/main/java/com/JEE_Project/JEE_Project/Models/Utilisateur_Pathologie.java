package com.JEE_Project.JEE_Project.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Utilisateur_Pathologie")
public class Utilisateur_Pathologie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "utilisateurId", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "pathologieId", nullable = false)
    private Pathologie pathologie;


    public long getId() { return id; }
    public Utilisateur getUtilisateur() { return utilisateur; }
    public Pathologie getPathologie() { return pathologie; }

    public void setId(long id) { this.id = id; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }
    public void setPathologie(Pathologie pathologie) { this.pathologie = pathologie; }
}

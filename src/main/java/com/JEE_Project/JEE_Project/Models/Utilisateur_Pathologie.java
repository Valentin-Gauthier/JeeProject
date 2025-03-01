package com.JEE_Project.JEE_Project.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Utilisateur_Pathologie")
public class Utilisateur_Pathologie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long utilisateurId;
    private long pathologieId;

    public long getId() { return id; }
    public long getUtilisateurId() { return utilisateurId; }
    public long getPathologieId() { return pathologieId; }

    public void setId(long id) { this.id = id; }
    public void setUtilisateurId(long id) { this.utilisateurId = id; }
    public void setPathologieId(long id) { this.pathologieId = id; }
}

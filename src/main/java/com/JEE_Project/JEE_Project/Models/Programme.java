package com.JEE_Project.JEE_Project.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Programmes")
public class Programme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long programmeId;
    private long utilisateurId;

    public Programme() {}

    public Programme(long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public long getProgrammeId() { return programmeId; }
    public long getUtilisateurId() { return utilisateurId; }

    public void setProgrammeId(long programmeId) { this.programmeId = programmeId; }
    public void setUtilisateurId(long utilisateurId) { this.utilisateurId = utilisateurId; }
}

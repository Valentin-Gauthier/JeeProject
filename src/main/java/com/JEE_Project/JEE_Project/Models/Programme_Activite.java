package com.JEE_Project.JEE_Project.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Programmes_Activite")
public class Programme_Activite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "programmeId", nullable = false)
    private Programme programme;

    @ManyToOne
    @JoinColumn(name = "activiteId", nullable = false)
    private Activite activite;

    public Programme_Activite() {}

    public Programme_Activite(Programme programme, Activite activite) {
        this.programme = programme;
        this.activite = activite;
    }

    public long getId() { return id; }
    public Programme getProgramme() { return programme; }
    public Activite getActivite() { return activite; }

    public void setId(long id) { this.id = id; }
    public void setProgramme(Programme programme) { this.programme = programme; }
    public void setActivite(Activite activite) { this.activite = activite; }
}

package com.JEE_Project.JEE_Project.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Activite_Pathologie")
public class Activite_Pathologie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "activiteId")
    private Activite activite;

    @ManyToOne
    @JoinColumn(name = "pathologieId")
    private Pathologie pathologie;

    public long getId() { return id; }
    public Activite getActivite() { return activite; }
    public Pathologie getPathologie() { return pathologie; }

    public void setId(long id) { this.id = id; }
    public void setActivite(Activite activite) { this.activite = activite; }
    public void setPathologie(Pathologie pathologie) { this.pathologie = pathologie; }
}

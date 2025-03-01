package com.JEE_Project.JEE_Project.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Activites")
public class Activite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long activiteId;
    private String nom;
    private String description;
    private String address;

    public Activite() {}

    public Activite(long activiteId, String nom, String description, String address) {
        this.activiteId = activiteId;
        this.nom = nom;
        this.description = description;
        this.address = address;
    }

    public long getActiviteId() { return this.activiteId; }
    public String getNom() { return this.nom; }
    public String getDescription() { return this.description; }
    public String getAddress() { return this.address; }

    public void setActiviteId(long activiteId) { this.activiteId = activiteId; }
    public void setNom(String nom) { this.nom = nom; }
    public void setDescription(String description) { this.description = description; }
    public void setAddress(String address) { this.address = address; }
}

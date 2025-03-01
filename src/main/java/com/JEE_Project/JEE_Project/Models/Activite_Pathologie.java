package com.JEE_Project.JEE_Project.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Activite_Pathologie")
public class Activite_Pathologie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long activiteId;
    private long pathologieId;

    public long getId() { return id; }
    public long getActiviteId() { return activiteId; }
    public long getPathologieId() { return pathologieId; }

    public void setId(long id) { this.id = id; }
    public void setActiviteId(long activiteId) { this.activiteId = activiteId; }
    public void setPathologieId(long pathologieId) {}
}

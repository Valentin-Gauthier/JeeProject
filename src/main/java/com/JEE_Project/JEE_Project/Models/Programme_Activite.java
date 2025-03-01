package com.JEE_Project.JEE_Project.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Programmes_Activite")
public class Programme_Activite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long programmeId;
    private long activiteId;

    public Programme_Activite() {}

    public Programme_Activite(long programmeId, long activiteId) {
        this.programmeId = programmeId;
        this.activiteId = activiteId;
    }

    public long getId() { return id; }
    public long getProgrammeId() { return programmeId; }
    public long getActiviteId() { return activiteId; }

    public void setId(long id) { this.id = id; }
    public void setProgrammeId(long programmeId) { this.programmeId = programmeId; }
    public void setActiviteId(long activiteId) { this.activiteId = activiteId; }
}

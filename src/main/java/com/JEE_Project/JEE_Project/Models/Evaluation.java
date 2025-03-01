package com.JEE_Project.JEE_Project.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Evaluations")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long evaluationId;
    private long utilisateurId;
    private long activiteId;
    private double note;

    public Evaluation() {}
    public Evaluation(long utilisateurId, long activiteId, double note) {
        this.utilisateurId = utilisateurId;
        this.activiteId = activiteId;
        this.note = note;
    }

    public long getEvaluationId() { return evaluationId; }
    public long getUtilisateurId() { return utilisateurId; }
    public long getActiviteId() { return activiteId; }
    public double getNote() { return note; }

    public void setEvaluationId(long evaluationId) { this.evaluationId = evaluationId; }
    public void setUtilisateurId(long utilisateurId) { this.utilisateurId = utilisateurId; }
    public void setActiviteId(long activiteId) { this.activiteId = activiteId; }
    public void setNote(double note) { this.note = note; }
}

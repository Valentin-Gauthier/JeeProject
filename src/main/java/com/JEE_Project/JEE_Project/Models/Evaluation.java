package com.JEE_Project.JEE_Project.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Evaluations")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long evaluationId;

    @ManyToOne
    @JoinColumn(name = "utilisateurId",nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "activiteId", nullable = false)
    private Activite activite;

    private double note;

    public Evaluation() {}

    public Evaluation(Utilisateur utilisateur, Activite activite, double note) {
        this.utilisateur = utilisateur;
        this.activite = activite;
        this.note = note;
    }

    public long getEvaluationId() { return evaluationId; }
    public Utilisateur getUtilisateur() { return utilisateur; }
    public Activite getActivite() { return activite; }
    public double getNote() { return note; }

    public void setEvaluationId(long evaluationId) { this.evaluationId = evaluationId; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }
    public void setActivite(Activite activite) { this.activite = activite; }
    public void setNote(double note) { this.note = note; }
}

package com.JEE_Project.JEE_Project.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Programmes")
public class Programme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long programmeId;

    @ManyToOne
    @JoinColumn(name = "utilisateurId", nullable = false)
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "programme", cascade = CascadeType.ALL)
    private List<Programme_Activite> activites;

    @Transient
    public double getMoyenne() {
        if (activites == null || activites.isEmpty()) {
            return 0.0;
        }
        // Récupérer toutes les évaluations de toutes les activités du programme
        List<Evaluation> evaluations = activites.stream()
                .filter(pa -> pa.getActivite() != null) // filtre les ProgrammeActivite qui ont bien une Activite
                .flatMap(pa -> pa.getActivite().getEvaluations().stream())// Recupere les Evaluations
                .filter(evaluation -> evaluation.getUtilisateur() != null && evaluation.getUtilisateur().equals(utilisateur))// filtre les Evaluations de l'Utilisateur
                .toList();

        if (evaluations.isEmpty()) {
            return 0.0;
        }

        double moyenne = evaluations.stream()
                .mapToDouble(Evaluation::getNote)
                .average()
                .orElse(0.0);

        return Math.round(moyenne * 10.0) / 10.0;
    }

    public Programme() {}

    public Programme(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public long getProgrammeId() { return programmeId; }
    public Utilisateur getUtilisateur() { return utilisateur; }
    public List<Programme_Activite> getActivites() { return activites; }

    public void setProgramme(long programmeId) { this.programmeId = programmeId; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }
    public void setActivites(List<Programme_Activite> activites) { this.activites = activites; }
}

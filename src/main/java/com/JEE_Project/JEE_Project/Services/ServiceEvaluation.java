package com.JEE_Project.JEE_Project.Services;

import com.JEE_Project.JEE_Project.Models.Evaluation;
import com.JEE_Project.JEE_Project.Models.Utilisateur;
import com.JEE_Project.JEE_Project.Repositories.RepoEvaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceEvaluation {

    @Autowired
    private RepoEvaluation repoEvaluation;

    // Recuperer les Evaluations d'un Utilisateur
    public List<Evaluation> getEvaluations(Utilisateur utilisateur) {
        return repoEvaluation.findEvaluationByUtilisateur(utilisateur);
    }

    // Verifier si une Evaluation exist déjà ou non
    public boolean evaluationExist(Evaluation evaluation) {
        int eval = repoEvaluation.findExistingEvaluation(evaluation.getUtilisateur(), evaluation.getActivite());
        return (eval != 0);
    }

    // Ajoute une Evaluation
    @Transactional
    public void addEvaluation(Evaluation evaluation) {
        repoEvaluation.save(evaluation);
    }

    @Transactional
    public void updateEvaluation(Evaluation e) {
        repoEvaluation.update(e.getUtilisateur(), e.getActivite(), e.getNote());
    }
}

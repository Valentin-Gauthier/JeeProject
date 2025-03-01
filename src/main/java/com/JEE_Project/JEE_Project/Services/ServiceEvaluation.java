package com.JEE_Project.JEE_Project.Services;

import com.JEE_Project.JEE_Project.Models.Evaluation;
import com.JEE_Project.JEE_Project.Repositories.RepoEvaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceEvaluation {

    @Autowired
    private RepoEvaluation repoEvaluation;

    @Transactional
    public void addEvaluation(Evaluation evaluation) {
        repoEvaluation.save(evaluation);
    }

    public double getMoyenneForActivite(long activiteId) {
        Double moyenne = repoEvaluation.findMoyenneFromActivite(activiteId);
        return (moyenne != null) ? moyenne : 0.0;
    }

    public boolean evaluationExist(long utilisateurId, long activiteId) {
        int eval = repoEvaluation.findExistingEvaluation(utilisateurId, activiteId);
        System.out.println(eval);
        return (eval != 0);
    }

    @Transactional
    public void updateEvaluation(Evaluation e) {
        repoEvaluation.update(e.getUtilisateurId(), e.getActiviteId(), e.getNote());
    }

}

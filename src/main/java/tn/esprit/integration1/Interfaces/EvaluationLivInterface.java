package tn.esprit.integration1.Interfaces;

import tn.esprit.integration1.Entities.EvaluationLiv;
import tn.esprit.integration1.Entities.EvaluationType;


public interface EvaluationLivInterface {

    EvaluationLiv addEvaluation(EvaluationLiv ce);
    public void AffecterEvaluation(Integer iduser, Integer idLivraison, EvaluationType e, String t);
}
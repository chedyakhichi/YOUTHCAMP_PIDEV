package tn.esprit.integration1.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.integration1.Entities.Delivery;
import tn.esprit.integration1.Entities.EvaluationLiv;
import tn.esprit.integration1.Entities.EvaluationType;
import tn.esprit.integration1.Entities.User;
import tn.esprit.integration1.Interfaces.EvaluationLivInterface;
import tn.esprit.integration1.Repositories.DeliveryRepository;
import tn.esprit.integration1.Repositories.EvaluationLivRepository;
import tn.esprit.integration1.Repositories.UserRepository;


@Service
public class EvaluationLivService implements EvaluationLivInterface {

    @Autowired
    EvaluationLivRepository evaluationLivRepository;
    @Autowired
    UserRepository userRepository ;
    @Autowired
    DeliveryRepository deliveryRepository;
    @Override
    public EvaluationLiv addEvaluation(EvaluationLiv ce) {
        return evaluationLivRepository.save(ce);
    }

    public void AffecterEvaluation(Integer iduser, Integer idLivraison, EvaluationType e, String t) {
        User u = userRepository.findById(iduser).get();
        Delivery d = deliveryRepository.findById(idLivraison).get();
        EvaluationLiv evaluationLiv = new EvaluationLiv() ;
        evaluationLiv.setText(t);
        evaluationLiv.setEvaluationType(e);
        evaluationLiv.setDelivery(d);
        evaluationLiv.setUser(u);
        evaluationLivRepository.save(evaluationLiv);

    }






            }

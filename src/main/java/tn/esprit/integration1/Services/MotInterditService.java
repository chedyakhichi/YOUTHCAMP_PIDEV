package tn.esprit.integration1.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.integration1.Entities.MotInterdit;
import tn.esprit.integration1.Interfaces.IMot;
import tn.esprit.integration1.Repositories.MotInterditRepository;

import java.util.List;
@Slf4j
@Service
public class MotInterditService implements IMot {

    @Autowired
    MotInterditRepository motInterditRepository;
    @Override
    public List<MotInterdit> retrieveAllMotIterdit() {
        return (List<MotInterdit>) motInterditRepository.findAll() ;
    }

    @Override
    public MotInterdit addMotInterdit(MotInterdit m) {
        return motInterditRepository.save(m);
    }

    @Override
    public MotInterdit retrieveMotInterdit(Integer idMot) {
        return motInterditRepository.findById(idMot).get();
    }

    @Override
    public void deleteMotInterdit(Integer idMot) {
    MotInterdit m=retrieveMotInterdit(idMot);
    motInterditRepository.delete(m);
    }
}

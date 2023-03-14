package tn.esprit.integration1.Services;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.integration1.Entities.PriorityWords;
import tn.esprit.integration1.Interfaces.IPriorityWordsService;
import tn.esprit.integration1.Repositories.PriorityWordsRepository;

import java.util.List;
@AllArgsConstructor
@Service
@Slf4j

public class PriorityWordsService implements IPriorityWordsService {
 PriorityWordsRepository priorityWordsRepository;
    @Override
    public PriorityWords addPriorityWords(PriorityWords f) {
        return priorityWordsRepository.save(f);
    }

    @Override
    public PriorityWords updatePriorityWords(PriorityWords f) {
        return priorityWordsRepository.save(f);
    }

    @Override
    public List<PriorityWords> retrvievePriorityWords() {
        return priorityWordsRepository.findAll();
    }

    @Override
    public List<String> retrvievePriWords() {
        return priorityWordsRepository.getpriwords();
    }

    @Override
    public void removePriorityWords(Integer IdWord) {
    priorityWordsRepository.deleteById(IdWord);
    }
}

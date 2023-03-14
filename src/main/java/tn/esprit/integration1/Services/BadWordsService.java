package tn.esprit.integration1.Services;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.integration1.Entities.BadWords;
import tn.esprit.integration1.Interfaces.IBadWordsService;
import tn.esprit.integration1.Repositories.BadWordsRepository;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class BadWordsService implements IBadWordsService {
    BadWordsRepository badWordsRepository;
    public BadWords addBadWords(BadWords f){
        return badWordsRepository.save(f);    }
    public BadWords updateBadWords(BadWords f){
        return badWordsRepository.save(f);
    }
    public List<BadWords> retrvieveBadWords(){
        return badWordsRepository.findAll();
    }
    public List<String> retrvievewords(){
        return badWordsRepository.getwords();
    }
    public  void removeBadWords(Integer IdWord){
        badWordsRepository.deleteById(IdWord);
    }
}

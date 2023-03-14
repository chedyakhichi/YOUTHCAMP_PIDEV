package tn.esprit.integration1.Interfaces;



import tn.esprit.integration1.Entities.BadWords;

import java.util.List;

public interface IBadWordsService {
    public BadWords addBadWords(BadWords f);
    public BadWords updateBadWords(BadWords f);
    public List<BadWords> retrvieveBadWords();
    public List<String> retrvievewords();


    public  void removeBadWords(Integer IdWord);
}

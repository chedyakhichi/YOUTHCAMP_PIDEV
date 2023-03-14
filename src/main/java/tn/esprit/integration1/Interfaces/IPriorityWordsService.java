package tn.esprit.integration1.Interfaces;



import tn.esprit.integration1.Entities.PriorityWords;

import java.util.List;

public interface IPriorityWordsService{
    public PriorityWords addPriorityWords(PriorityWords f);
    public PriorityWords updatePriorityWords(PriorityWords f);
    public List<PriorityWords> retrvievePriorityWords();
    public List<String> retrvievePriWords();


    public  void removePriorityWords(Integer IdWord);
}

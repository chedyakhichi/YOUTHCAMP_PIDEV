package tn.esprit.integration1.Interfaces;




import tn.esprit.integration1.Entities.Feedback;

import java.util.List;
import java.util.Map;

public interface IFeedbackService {
    public Feedback addFeedback(Feedback f);
    public Feedback updateFeedback(Feedback f);
    public List<Feedback> retrieveAllFeedbacks();
    public  void removeFeedback(Integer idFeedback);

        public void assignFeedbackToUser(Integer idFeedback,Integer IdUser);
    public String filter (String input);
    public  int countOccurrences(String text, String word);
    public  Map<String, Integer> countWords(String text);
    public  Integer countWords2(String text);





    }

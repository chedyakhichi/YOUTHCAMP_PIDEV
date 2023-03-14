package tn.esprit.integration1.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.integration1.Entities.Feedback;
import tn.esprit.integration1.Entities.User;
import tn.esprit.integration1.Interfaces.IFeedbackService;
import tn.esprit.integration1.Repositories.BadWordsRepository;
import tn.esprit.integration1.Repositories.FeedbackRepository;
import tn.esprit.integration1.Repositories.UserRepository;



import javax.transaction.Transactional;
import java.util.*;

import java.util.HashMap;
import java.util.Map;
@AllArgsConstructor
@Service
@Slf4j

public class FeedbackService implements IFeedbackService {
    @Autowired
    FeedbackRepository feedbackRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BadWordsRepository badWordsRepository;
    @Autowired
    BadWordsService bws;
    @Autowired
    PriorityWordsService pws;
    public Feedback addFeedback(Feedback f) {
        String d;

        d=filter(f.getDescription());
        f.setDescription(d);
        Map<String, Integer> wordCounts =countWords(f.getDescription());
        int x=countWords2(f.getDescription());
        f.setNbrw(x);
        log.info(wordCounts.toString());
        log.info(String.valueOf(x));
        return feedbackRepository.save(f);
    }


    public Feedback updateFeedback(Feedback f) {
        String d;

        d=filter(f.getDescription());
        f.setDescription(d);
        Map<String, Integer> wordCounts =countWords(f.getDescription());
        int x=countWords2(f.getDescription());
        f.setNbrw(x);
        log.info(wordCounts.toString());
        log.info(String.valueOf(x));
        return feedbackRepository.save(f);
    }


    public List<Feedback> retrieveAllFeedbacks() {

        return feedbackRepository.affichepriorty();
    }




    public void removeFeedback(Integer idFeedback) {
        feedbackRepository.deleteById(idFeedback);
    }
    @Transactional
    public void assignFeedbackToUser(Integer idFeedback,Integer IdUser){
        Feedback F=feedbackRepository.findById(idFeedback).orElse(null);

        User U=userRepository.findById(IdUser).orElse(null);
        F.setUser(U);
        feedbackRepository.save(F);

    }

   /* public List<String> words(){
        List<String> words = badWordsRepository.getwords();
        for (String word : words) {
             X= Collections.singletonList(word);
        }
        return  X;
    }*/


    //  private static final String[] BAD_WORDS = {"bad", "bad", "bad"};


    public String filter (String input){
        List<String> BAD_WORDS =bws.retrvievewords();
        String filteredInput = input;
            /*int NbrWords=  input.split("\\s+").length;
            for (String name : BAD_WORDS) {
                if (!input.contains(name)) {
                   // return false;

                    log.info("Fama Kelma");
                }
            }*/
        int a=input.length();
        String starStringmemetaille = "";
        for (int i = 0; i < a; i++) {
            starStringmemetaille += "*";
        }

        String starStringdifftaille = "";
        for (int i = 1; i < a; i++) {
            starStringdifftaille += "*";
        }
        for (String badWord : BAD_WORDS) {
            if(badWord.length()==input.length())
                filteredInput = filteredInput.replaceAll(badWord, starStringmemetaille);
            if(badWord.length()!=input.length())
                filteredInput = filteredInput.replaceAll(badWord, starStringdifftaille);

        }

        return filteredInput;
    }

    //  private static final String[] words = {"important", "urgent", "grave"};
    //List<String> words =pws.retrvievePriWords();
    public  Map<String, Integer> countWords(String text) {
        List<String> words =pws.retrvievePriWords();

        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : words) {
            int count = countOccurrences(text, word);
            wordCounts.put(word, count);
        }
        return wordCounts;
    }
    /*public  int countWords2(String text, String[] words) {
       int x = 0;
        for (String word : words) {
            x = countOccurrences(text, word);

          //  log.info(String.valueOf(x));
        }
          log.info(String.valueOf(x));

        return x;

    }*/
    public Integer countWords2(String text) {
        List<String> words =pws.retrvievePriWords();

        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : words) {
            int count = countOccurrences(text, word);
            wordCounts.put(word, count);
        }
        int sum = 0;
        for (int value : wordCounts.values()) {
            sum += value;
        }
        return sum;
    }


    public  int countOccurrences(String text, String word) {
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(word, index)) != -1) {
            count++;
            index += word.length();
        }

        return count;
    }
}
   /* public void badwords(String text) {
        String[] badwords = new String[2];
        badwords[0] = "khra";
        badwords[0] = "ham";
        for(int i=0;i<text.length();i++){
            for (int j=0;j<2;j++){
                for(int k=0;k<badwords[j].length();k++){
                    if(text[i])

                }
            }
        }

    }*/


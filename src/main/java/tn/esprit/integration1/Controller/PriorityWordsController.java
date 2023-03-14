package tn.esprit.integration1.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.integration1.Entities.PriorityWords;
import tn.esprit.integration1.Interfaces.IPriorityWordsService;

import java.util.List;

@RestController
@RequestMapping("/PriorityWords")
public class PriorityWordsController {
    @Autowired
    IPriorityWordsService PriorityWordsService;
    @PostMapping("/AddPriorityWords")
    public PriorityWords addPriorityWords(@RequestBody PriorityWords F) {
        PriorityWords PriorityWords = PriorityWordsService.addPriorityWords(F);
        return PriorityWords;
    }

    @GetMapping("/retrieve-all-PriorityWords")
    public List<PriorityWords> getPriorityWords() {
        List<PriorityWords> listPriorityWords = PriorityWordsService.retrvievePriorityWords();
        return listPriorityWords;
    }
    @GetMapping("/retrieve-Words")
    public List<String> retrievewords(){

        return PriorityWordsService.retrvievePriWords();
    }
    @PutMapping("/update-PriorityWords")
    public PriorityWords updatePriorityWords(@RequestBody PriorityWords c) {
        PriorityWords PriorityWords = PriorityWordsService.updatePriorityWords(c);
        return PriorityWords;
    }
    @DeleteMapping("/remove-PriorityWords/{IdWord}")
    public void removePriorityWords(@PathVariable("IdWord") Integer IdWord) {

        PriorityWordsService.removePriorityWords(IdWord);
    }
}
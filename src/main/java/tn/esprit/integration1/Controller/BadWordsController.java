package tn.esprit.integration1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.integration1.Entities.BadWords;
import tn.esprit.integration1.Interfaces.IBadWordsService;

import java.util.List;
@RestController
@RequestMapping("/BadWords")
public class BadWordsController {
@Autowired
IBadWordsService BadWordsService;
    @PostMapping("/AddBadWords")
    public BadWords addBadWords(@RequestBody BadWords F) {
        BadWords BadWords = BadWordsService.addBadWords(F);
        return BadWords;
    }

    @GetMapping("/retrieve-all-BadWords")
    public List<BadWords> getBadWords() {
        List<BadWords> listBadWords = BadWordsService.retrvieveBadWords();
        return listBadWords;
    }
    @GetMapping("/retrieve-Words")
    public List<String> retrievewords(){
        return BadWordsService.retrvievewords();
    }
    @PutMapping("/update-BadWords")
    public BadWords updateBadWords(@RequestBody BadWords c) {
        BadWords BadWords = BadWordsService.updateBadWords(c);
        return BadWords;
    }

    @DeleteMapping("/remove-BadWords/{IdWord}")
    public void removeBadWords(@PathVariable("IdWord") Integer IdWord) {

        BadWordsService.removeBadWords(IdWord);
    }
}

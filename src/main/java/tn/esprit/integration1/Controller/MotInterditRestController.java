package tn.esprit.integration1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.integration1.Entities.MotInterdit;
import tn.esprit.integration1.Interfaces.IMot;

import java.util.List;

@RestController
@RequestMapping("/mots-interdits")
public class MotInterditRestController {
    @Autowired
    IMot motInterditService;
    @GetMapping("/retrieve-all-mot")
    public List<MotInterdit> getMotInterdits() {
        List<MotInterdit> motInterditList = motInterditService.retrieveAllMotIterdit();
        return motInterditList;
    }
    //
    @GetMapping("/retrieve-Mot/{motInterdit-id}")
    public MotInterdit retrieveMotInterdit(@PathVariable("motInterdit-id") Integer idMot) {
        return motInterditService.retrieveMotInterdit(idMot);
    }


    @PostMapping("/add-MotInterdit")
    public MotInterdit addMotInterdit(@RequestBody MotInterdit m) {
        MotInterdit motInterdit = motInterditService.addMotInterdit(m);
        return motInterdit;
    }

    // supprimer pub
    @DeleteMapping("/remove-Mot/{motInterdit}")
    public void  deleteMotInterdit(@PathVariable("motInterdit") Integer idMot) {
        motInterditService.deleteMotInterdit(idMot);
    }




}

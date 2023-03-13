package tn.esprit.integration1.Interfaces;


import tn.esprit.integration1.Entities.MotInterdit;

import java.util.List;

public interface IMot {
    public List<MotInterdit> retrieveAllMotIterdit();
    public MotInterdit addMotInterdit (MotInterdit m);
    public MotInterdit retrieveMotInterdit (Integer idMot);
    public  void deleteMotInterdit(Integer idMot);
}

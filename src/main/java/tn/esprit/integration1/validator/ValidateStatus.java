package tn.esprit.integration1.validator;


import tn.esprit.integration1.Entities.CmdType;

public class ValidateStatus {
    public static boolean validateStatus(CmdType status) {
        return status == CmdType.CREATED;
    }

}

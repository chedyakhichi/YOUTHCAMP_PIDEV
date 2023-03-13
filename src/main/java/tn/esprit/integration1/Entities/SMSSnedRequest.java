package tn.esprit.integration1.Entities;

import lombok.Data;

@Data
public class SMSSnedRequest {
    private  String destinationSMSNumber ;
    private String SMSMessage ;
}

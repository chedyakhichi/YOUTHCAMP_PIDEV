package tn.esprit.integration1.Services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class SMSService {



   // @Value("${TWILIO_ACCOUNT_SID}")
    String ACCOUNT_SID="AC3fae6b1dbd1e024503c9f6edaac22232";
  //// @Value("${TWILIO_AUTH_TOKEN}")
    String AUTH_TOKEN="4a0225547cbff08be112120e62e8ddbb";
 //  @Value("${TWILIO_OUTGOING_SMS_NUMBER}")
    String OUTGOING_SMS_NUMBER="+15076088669";

    @PostConstruct
    private  void setup(){
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);

    }
    public String senSMS(String smsNumber,String smsMessage){
        Message message=Message.creator(new PhoneNumber(smsNumber),
                new PhoneNumber(OUTGOING_SMS_NUMBER),
                smsMessage).create();


        return "nothing ";

    }
}

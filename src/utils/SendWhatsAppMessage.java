package utils;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SendWhatsAppMessage {
    public static final String ACCOUNT_SID = "ACd6a23ccb06661f122c7479f402e65b93";
    public static final String AUTH_TOKEN = "19de6279f75b33ec0dee1f5547afb821";

    public static void sendMessage(String number) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber("whatsapp:+21623483895"),
                        new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                        "Un événement est ajouté")
                .create();

        System.out.println(message.getSid());
    }

}
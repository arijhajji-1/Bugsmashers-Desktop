package utils;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SendWhatsAppMessage {
    // Remplacez avec votre Account Sid et votre Auth Token
    public static final String ACCOUNT_SID = "AC195a3237f56889be975f16b4474ab3bf";
    public static final String AUTH_TOKEN = "e4fdc7f957a8881717f23f28d4681d83";

    public static void sendMessage(String number) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(number),
                        new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                        "Un événement est ajouté")
                .create();

        System.out.println(message.getSid());
    }

}
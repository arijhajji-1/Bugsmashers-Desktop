package utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TwillioMailSender {
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC42b5d3f112c3f59488515fc01a69a5f5";
    public static final String AUTH_TOKEN = "87574a7dd47ed9bde92d0ff11f55894b";

    public static void sendMail(String msg) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new PhoneNumber("whatsapp:+21623483895"),
                        new PhoneNumber("whatsapp:+14155238886"),
                        msg)
                .create();

        System.out.println(message.getSid());
    }
}

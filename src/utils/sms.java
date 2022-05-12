/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author Arij Hajji
 */
public class sms {
    // Find your Account Sid and Auth Token at twilio.com/console

    public static final String ACCOUNT_SID
            = "ACd091dbe1eb29a46b5af1a23ef554cf7e";
    public static final String AUTH_TOKEN
            = "0f7ac27ce76bd204a89ed0068c873d9c";

    public void send(String s, String x) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
       
        Message message = Message
                .creator(new PhoneNumber(x), // to
                        new PhoneNumber("+19894020981"), // from
                        "" + s)
                .create();
        System.out.println("aaslema");
        System.out.println(message.getSid());
    }

}

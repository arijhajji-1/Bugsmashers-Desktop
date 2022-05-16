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
            = "45bdce2595f2c01a9547ae076c0bf005";

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

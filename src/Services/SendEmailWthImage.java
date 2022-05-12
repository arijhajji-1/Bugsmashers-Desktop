package Services;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * This class is used to send email with image.
 * @author w3spoint
 */
public class SendEmailWthImage { 
 final String senderEmailId = "hsine.mtiraoui@esprit.tn";
 final String senderPassword = "Blackout4";
 final String emailSMTPserver = "smtpout.secureserver.net";
 
public SendEmailWthImage(String receiverEmail,
                         String subject, String messageText, String imagePath) {
	Properties properties = new Properties();
	properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
 
 try { 			
	Authenticator auth = new SMTPAuthenticator();
        Session session = Session.getInstance(properties, auth);
 
	Message emailMessage = new MimeMessage(session);
	emailMessage.setFrom(new InternetAddress(senderEmailId));
	emailMessage.setRecipients(Message.RecipientType.TO, 
			InternetAddress.parse(receiverEmail));
	emailMessage.setSubject(subject);			
 
	MimeBodyPart messageBodyPart1 = new MimeBodyPart();
	messageBodyPart1.setText(messageText);
 
	MimeBodyPart messageBodyPart2 = new MimeBodyPart();
        messageBodyPart2.attachFile(imagePath);
 
	Multipart multipart = new MimeMultipart();
	multipart.addBodyPart(messageBodyPart1);
	multipart.addBodyPart(messageBodyPart2);
	emailMessage.setContent(multipart);
 
	Transport.send(emailMessage);
 
	System.out.println("Email send successfully."); 
} catch (Exception e) {
    e.printStackTrace();
    }
}
 
private class SMTPAuthenticator extends 
  Authenticator {
    public PasswordAuthentication 
     getPasswordAuthentication() {
        return new PasswordAuthentication(senderEmailId, 
        		senderPassword);
    }
}
 
}
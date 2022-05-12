/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class mail {
private String username = "Reloua.tunisie@gmail.com";
private String password = "MehdiMehdi10";
public void envoyer() {
// Etape 1 : Création de la session
Properties props = new Properties();
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable","true");
props.put("mail.smtp.host","smtp.gmail.com");
props.put("mail.smtp.port","587");
Session session = Session.getInstance(props,
new javax.mail.Authenticator() {
protected PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(username, password);
}
});
try {
// Etape 2 : Création de l'objet Message
Message message = new MimeMessage(session);
message.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      message.addHeader("format", "flowed");
	      message.addHeader("Content-Transfer-Encoding", "8bit");
message.setFrom(new InternetAddress("Reloua.tunisie@gmail.com"));
message.setRecipients(Message.RecipientType.TO,
InternetAddress.parse("mahdi.ennour@esprit.tn"));
message.setSubject("RelouaTeam");
message.setText("Bonjour, Votre Commande est en cours de livraison merci pour votre confience");
// Etape 3 : Envoyer le message
Transport.send(message);
System.out.println("Message_envoye");
} catch (MessagingException e) {
throw new RuntimeException(e);
} }
}
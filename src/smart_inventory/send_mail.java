/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart_inventory;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;


/**
 *
 * @author MAHE
 */
public class send_mail {
 final String senderEmailID = "paishreya2305@gmail.com";
 final String senderPassword = "enter your app password";
 final String emailSMTPserver = "smtp.gmail.com";
 final String emailServerPort = "465";
 String receiverEmailID = null;
 static String emailSubject = "Test Mail";
 static String emailBody = ":)";
 String name="shreya pai";
 public send_mail(String receiverEmailID, String emailSubject, String emailBody)
 {
    this.receiverEmailID=receiverEmailID;
    this.emailSubject=emailSubject;
    this.emailBody=emailBody;
    Properties props = new Properties();
    props.put("mail.smtp.user", senderEmailID);
    props.put("mail.smtp.host", emailSMTPserver );
    props.put("mail.smtp.port", emailServerPort);
    props.put("mail.smtp.starttls.enable","true");
    props.put("mail.smtp.debug", "true");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.socketFactory.port", emailServerPort);
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.socketFactory.fallback", "false");

    
    try
    {
        SMTPAuthenticator auth = new SMTPAuthenticator();
        Session session = Session.getInstance(props, auth);
        session.setDebug(true);
        MimeMessage msg = new MimeMessage(session);
        msg.setText(emailBody);
        msg.setSubject(emailSubject);

        msg.setFrom(new InternetAddress(senderEmailID));
        msg.addRecipient(Message.RecipientType.TO,
            new InternetAddress(receiverEmailID));
        try (Transport transport = session.getTransport("smtps")) {
            transport.connect(emailSMTPserver, Integer.valueOf(emailServerPort), senderEmailID, senderPassword);
            transport.sendMessage(msg, msg.getAllRecipients());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        
        System.out.println("Message sent Successfully:)");
    }
    catch (Exception mex)
    {
        mex.printStackTrace();
    }
}
public class SMTPAuthenticator extends javax.mail.Authenticator
{
    public PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(senderEmailID, senderPassword);
    }
}
public static void main(String[] args) {
         //send_mail mailSender;
       // mailSender = new send_mail("paishreya1412@gmail.com","Hello There","This is an auto-generated email!:)");
    
}
}

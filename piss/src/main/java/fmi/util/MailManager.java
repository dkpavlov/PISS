package fmi.util;

import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

@Component
public class MailManager {

    public static void sendMail(String toEmail, String subject, String text) {

        Properties props = new Properties();
        try {
            props.load(MailManager.class.getClassLoader().getResourceAsStream(
                    "mail.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        final String username = props.getProperty("mail.username");
        final String password = props.getProperty("mail.password");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}

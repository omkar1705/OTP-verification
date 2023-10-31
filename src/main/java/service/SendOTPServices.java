package service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendOTPServices {
    public static void sendOTp(String email, String genOTP) {
        String to = email; // recipient email id need to mention
        String from = "mysqlworkbench.otp@gmail.com"; // email from where it is sent
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Create an Authenticator object with your email and password
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "oefl onym zutz qhhy\n");
            }
        };

        Session session = Session.getInstance(properties, authenticator);
        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);

            // Set the recipient email address
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject("File Enc Ka OTP");
            message.setText("Your OTP is " + genOTP);
            System.out.println("Sending...");

            Transport.send(message);
            System.out.println("Message is sent successfully");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}

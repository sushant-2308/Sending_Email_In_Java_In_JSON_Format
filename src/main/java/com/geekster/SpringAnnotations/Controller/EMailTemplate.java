package com.geekster.SpringAnnotations.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.geekster.SpringAnnotations.Student;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class EMailTemplate extends  StudentController{
    public static void main(String[] args) {
    String fromAddress = "rawatsushant2308@gmail.com";
    String toAddress = "rawatsushant2asb@gmail.com";
    String ccAddress = "sushant.rawat@ssipmt.com";
    Student student=new Student("Sushant","1234567890","Software Developer");
    ObjectMapper objectMapper=new ObjectMapper();
        try {
        String messageBody=objectMapper.writeValueAsString(student);
        sendMailWithoutAttachment(fromAddress, toAddress, ccAddress, messageBody);
    }
        catch (MessagingException | JsonProcessingException e) {
        e.printStackTrace();
    }

//        try {
//            sendMailWithAttachment(fromAddress, toAddress, ccAddress, messageBody);
//        }
//        catch (MessagingException e){
//            e.printStackTrace();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
    }
    private static Session getSession(){
        Properties properties = System.getProperties();
        /*
        We need below properties for establishing connection with email service provider
        1. host name
        2. port name
        3. ssl(secure socket level) level
        4. authentication parameter
        */
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //1. Creating session for establishing connection with Gmail server
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("rawatsushant2308@gmail.com", Constant.PASSWORD);
            }
        });
        return session;
    }

    public static void sendMailWithoutAttachment(String fromAddress, String toAddress,String ccAddress, String messageBody) throws MessagingException {
        Session session = getSession();
        //2. Compose the mail
        //add-form and set-form
        MimeMessage message = new MimeMessage(session);
        message.setFrom(fromAddress);
        message.addRecipients(Message.RecipientType.TO,toAddress);
        message.addRecipients(Message.RecipientType.CC,ccAddress);
        message.setSubject("Email Demo");
        message.setText(messageBody);

        //3. Send the mail
        Transport.send(message);
        System.out.println("Email sent Successfully......!!!!!");
    }


    public static void sendMailWithAttachment(String fromAddress, String toAddress,String ccAddress, String messageBody) throws MessagingException, IOException {
        Session session = getSession();
        //2. Compose the mail
        //add-form and set-form
        MimeMessage message = new MimeMessage(session);
        message.setFrom(fromAddress);
        message.addRecipients(Message.RecipientType.TO,toAddress);
        message.addRecipients(Message.RecipientType.CC,ccAddress);
        message.setSubject("Resume");

        MimeMultipart mimeMultipart = new MimeMultipart();
        //for body text
        MimeBodyPart bodyText = new MimeBodyPart();
        bodyText.setText(messageBody);
        //for file
        MimeBodyPart bodyAttachment = new MimeBodyPart();
        //Setting body with attachment
        String path = "//home//sameer//Downloads//Sankalp Resume.pdf";
        File file = new File(path);
        bodyAttachment.attachFile(file);

        //setting body part to multiport object
        mimeMultipart.addBodyPart(bodyText);
        mimeMultipart.addBodyPart(bodyAttachment);


        message.setContent(mimeMultipart);

        //3. Send the mail
        Transport.send(message);
        System.out.println("Email sent Successfully......!!!!!");
    }
}

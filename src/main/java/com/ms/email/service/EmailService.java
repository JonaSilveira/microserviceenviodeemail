package com.ms.email.service;

import com.ms.email.enums.StatusEmail;
import com.ms.email.model.Email;
import com.ms.email.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    public Email sendEmail(Email email) {
        email.setSendDateEmail(LocalDateTime.now());

        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(email.getEmailFrom());
            simpleMailMessage.setText(email.getText());
            simpleMailMessage.setTo(email.getEmailTo());
            simpleMailMessage.setSubject(email.getSubject());

            emailSender.send(simpleMailMessage);
            email.setStatusEmail(StatusEmail.SENT);
        }
        catch (MailException e){
            e.printStackTrace();
            email.setStatusEmail(StatusEmail.ERROR);
        }
        finally {
            return emailRepository.save(email);
        }

    }
}

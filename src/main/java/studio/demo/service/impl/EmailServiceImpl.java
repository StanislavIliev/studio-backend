package studio.demo.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import studio.demo.service.EmailService;


@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private  final SimpleMailMessage simpleMailMessage;
    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender, SimpleMailMessage simpleMailMessage) {
        this.emailSender = emailSender;
        this.simpleMailMessage = simpleMailMessage;
    }

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jintraxxx@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
package studio.demo.service.impl;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import studio.demo.model.entity.User;
import studio.demo.repository.UserRepository;
import studio.demo.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final SimpleMailMessage simpleMailMessage;
    private final UserRepository userRepository;
    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender, SimpleMailMessage simpleMailMessage, UserRepository userRepository) {
        this.emailSender = emailSender;
        this.simpleMailMessage = simpleMailMessage;
        this.userRepository = userRepository;
    }

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jintraxxx@gmail.com");
        message.setTo(to);
        message.setSubject("Request Reset Password");
        String token = generateString();
        message.setText("http://localhost:4200/" + token);
        User user= this.userRepository.findByEmail(to).orElse(null);
        if(user !=null) {
            user.setToken(token);
        }
        emailSender.send(message);
    }

    public static String generateString() {
        return UUID.randomUUID().toString();
    }
}
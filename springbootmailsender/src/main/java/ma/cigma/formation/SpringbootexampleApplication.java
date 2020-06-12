package ma.cigma.formation;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@SpringBootApplication
public class SpringbootexampleApplication implements CommandLineRunner {

	@Autowired
    private JavaMailSender javaMailSender;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootexampleApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Sending Email...");
        try {
            sendEmail("abbouhassane@gmail.com");
            sendEmailWithAttachment("abbouhassane@gmail.com","image1.jpg",new File("C:/images/image1.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        } 
        System.out.println("Done");
    }

    void sendEmail(String mailTo) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mailTo);
        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email \n www.google.com");
        javaMailSender.send(msg);
    }

    void sendEmailWithAttachment(String mailTo,String imageName,File image) throws MessagingException, IOException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(mailTo);
        helper.setSubject("Testing from Spring Boot");
        // default = text/plain
        //helper.setText("Check attachment for image!");
        // true = text/html
        helper.setText("<h1>Check attachment for image!</h1>", true);
        helper.addAttachment(imageName, image);
        javaMailSender.send(msg);
    }
}
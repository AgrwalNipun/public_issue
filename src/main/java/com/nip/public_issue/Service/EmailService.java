package com.nip.public_issue.Service;

import java.io.File;
// Updated Imports: 'javax' replaced with 'jakarta'
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.nip.public_issue.models.EmailDetails;

@Service
public class EmailService {

    @Autowired 
    private JavaMailSender javaMailSender;

    // This value is read from your application.properties/yml (e.g., spring.mail.username)
    @Value("${spring.mail.username}") 
    private String sender;

    /**
     * Sends a simple text email.
     * @param details Contains recipient, subject, and body.
     * @return Success or error message.
     */
    public String sendSimpleMail(EmailDetails details) {

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        } catch (MailException e) {
            // Using the specific MailException for clearer handling
            return "Error while Sending Mail: " + e.getMessage();
        }
    }

    /**
     * Sends an email with a file attachment.
     * @param details Contains recipient, subject, body, and attachment path.
     * @return Success or error message.
     */
    public String sendMailWithAttachment(EmailDetails details) {
        
        // Creating a mime message
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            // Setting multipart as true for attachments
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(details.getSubject());

            // Adding the attachment
            FileSystemResource file = new FileSystemResource(
                new File(details.getAttachment())
            );

            mimeMessageHelper.addAttachment(file.getFilename(), file);

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";

        } catch (MessagingException | MailException e) {
            // Catching both mail-related exceptions
            return "Error while sending mail!!!: " + e.getMessage();
        }
    }
}
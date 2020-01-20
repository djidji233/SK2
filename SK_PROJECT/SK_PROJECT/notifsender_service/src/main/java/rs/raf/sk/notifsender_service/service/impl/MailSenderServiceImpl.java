package rs.raf.sk.notifsender_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import rs.raf.sk.notifsender_service.dto.MailRequest;
import rs.raf.sk.notifsender_service.service.MailSenderService;
import rs.raf.sk.notifsender_service.service.TemplateService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {

    private static final Logger logger = LoggerFactory.getLogger(MailSenderService.class);

    private final JavaMailSender mailSender;

    private final TemplateService templateService;

    @Async
    @Override
    public void send(MailRequest mailRequest) {
        logger.info("New message for: {}", mailRequest.getEmail());
        String body = templateService.fillNotificationTemplate(mailRequest.getData());
        try {
            mailSender.send(prepareMessage(mailRequest.getEmail(), body));
        } catch (Exception e) {
            logger.error("Error in sending mail for: {}, error message: {}", mailRequest.getEmail(), e.getMessage());
        }
    }

    private MimeMessage prepareMessage(String to, String body) {
        MimeMessage msg = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(to);
            helper.setSubject("Weather Info Notification");
            helper.setText(body, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return msg;
    }

}

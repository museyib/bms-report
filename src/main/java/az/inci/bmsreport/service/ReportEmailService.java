package az.inci.bmsreport.service;

import jakarta.mail.Address;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReportEmailService
{
    private final JavaMailSender mailSender;

    public ReportEmailService(JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }

    public void  sendEmail(String content)
    {
        try
        {
            Address[] recipients = {
                    new InternetAddress("museyib.cr@gmail.com"),
                    new InternetAddress("mikayil.yusifov@inci.az"),
                    new InternetAddress("isa.abbasov@inci.az"),
                    new InternetAddress("elnur.qasimov@inci.az")};
            String recipient = "museyib.cr@gmail.com";

            MimeMessagePreparator messagePreparator = (message) -> {
                message.setRecipients(MimeMessage.RecipientType.TO, recipients);
                message.setSubject("Günlük satış hesabatı");
                message.setFrom("Inci Report Center <museyib.alekber@inci.az>");
                message.setSender(new InternetAddress("museyib.alekber@inci.az"));
                message.setContent(content, "text/html; charset=utf-8");
            };
            mailSender.send(messagePreparator);
        }
        catch(Exception e)
        {
            log.error(e.toString());
        }
    }
}

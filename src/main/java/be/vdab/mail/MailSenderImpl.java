package be.vdab.mail;

import be.vdab.entities.Filiaal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class MailSenderImpl implements MailSender {
    private static final Logger LOGGER = Logger.getLogger(MailSenderImpl.class.getName());
    private final JavaMailSender sender;
    private final String webmaster;

    @Autowired
    public MailSenderImpl(JavaMailSender sender, @Value("${webmaster}") String webmaster) {
        this.sender = sender;
        this.webmaster = webmaster;
    }
    @Override
    public void nieuwFiliaalMail(Filiaal filiaal) {
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(webmaster);
            helper.setSubject("Nieuw filiaal");
            helper.setText(String.format("Filiaal <strong>%s</strong> is toegevoegd", filiaal.getNaam()), true);
            sender.send(message);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "kan mail nieuw filiaal niet versturen", e);
            throw new RuntimeException("Kan mail nieuw filiaal niet versturen", e);
        }
    }
}

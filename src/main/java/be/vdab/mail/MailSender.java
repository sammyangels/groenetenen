package be.vdab.mail;

import be.vdab.entities.Filiaal;

public interface MailSender {
    void nieuwFiliaalMail(Filiaal filiaal);
}

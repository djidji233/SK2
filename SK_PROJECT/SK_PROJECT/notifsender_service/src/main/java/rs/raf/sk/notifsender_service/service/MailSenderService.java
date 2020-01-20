package rs.raf.sk.notifsender_service.service;

import rs.raf.sk.notifsender_service.dto.MailRequest;

public interface MailSenderService {

    void send (MailRequest mailRequest);

}

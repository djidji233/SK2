package rs.raf.sk.notifsender_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.MessageEndpoint;
import rs.raf.sk.notifsender_service.channel.InputChannel;
import rs.raf.sk.notifsender_service.dto.MailRequest;
import rs.raf.sk.notifsender_service.service.MailSenderService;

@MessageEndpoint
@EnableBinding(InputChannel.class)
@RequiredArgsConstructor
public class MessageReceiver {

    private final MailSenderService mailSenderService;

    @StreamListener(InputChannel.CHANNEL_NAME)
    public void receive(MailRequest mailRequest) {
        mailSenderService.send(mailRequest);
    }

}

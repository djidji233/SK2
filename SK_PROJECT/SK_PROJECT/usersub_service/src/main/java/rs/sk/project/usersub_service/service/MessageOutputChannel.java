package rs.sk.project.usersub_service.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MessageOutputChannel {

    @Output("simple_channel")
    MessageChannel output();

}

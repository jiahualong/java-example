package cc.stan.example.streamkafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Slf4j
@Service
public class GreetingsService {
    private final GreetingsStreams greetingsStream;

    @Autowired
    public GreetingsService(GreetingsStreams greetingsStream) {
        this.greetingsStream = greetingsStream;
    }

    public void sendGreeting(final Greetings greetings) {
        log.info("send {}", greetings);
        MessageChannel messageChannel = greetingsStream.outboundGreetings();
        messageChannel.send(
                MessageBuilder.withPayload(greetings)
                        .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .build());
    }
}

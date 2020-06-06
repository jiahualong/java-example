package cc.stan.example.streamkafka;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsCtl {

    private final GreetingsService greetingsService;

    public GreetingsCtl(GreetingsService greetingsService) {
        this.greetingsService = greetingsService;
    }

    public void greetings(@RequestParam("msg") String msg) {
        Greetings greetings = Greetings.builder()
                .message(msg)
                .timestamp(System.currentTimeMillis())
                .build();
        greetingsService.sendGreeting(greetings);
    }

}

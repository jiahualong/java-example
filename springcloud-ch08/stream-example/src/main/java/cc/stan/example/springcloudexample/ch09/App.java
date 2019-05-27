package cc.stan.example.springcloudexample.ch09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

@SpringBootApplication
@EnableBinding(Source.class)
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

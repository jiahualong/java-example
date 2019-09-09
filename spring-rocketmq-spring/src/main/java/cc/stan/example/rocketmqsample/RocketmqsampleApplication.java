package cc.stan.example.rocketmqsample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.IntStream;

@SpringBootApplication
public class RocketmqsampleApplication implements CommandLineRunner {

    private SampleProducker prod;

    @Autowired
    public RocketmqsampleApplication(SampleProducker prod) {
        this.prod = prod;
    }

    public static void main(String[] args) {
        SpringApplication.run(RocketmqsampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        IntStream.range(0, 10).forEach(i -> prod.send(i + ""));
    }
}

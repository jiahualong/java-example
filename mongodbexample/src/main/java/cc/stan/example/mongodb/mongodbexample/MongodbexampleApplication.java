package cc.stan.example.mongodb.mongodbexample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongodbexampleApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MongodbexampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("start ... ");
    }
}

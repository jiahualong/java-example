package cc.stan.example.springcloudconfigclient.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    @Value("${userservice.title}")
    private String title;

    @Value("${myapp.welcome}")
    private String welcome;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("hello");
        System.out.println(String.format("title,%s, welcome,%s \n", title, welcome));
    }
}

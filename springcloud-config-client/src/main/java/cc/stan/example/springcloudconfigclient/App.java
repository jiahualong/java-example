package cc.stan.example.springcloudconfigclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println("start ...");
    }

//    @Bean
//    CommandLineRunner runner() {
//        return args -> {
//            System.out.println("CommandLineRunner running...");
//            System.out.println(String.format("title,%s, welcome,%s \n", title, welcome));
//        };
//    }
}

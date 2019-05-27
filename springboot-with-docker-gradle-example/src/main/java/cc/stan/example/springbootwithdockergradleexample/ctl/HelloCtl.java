package cc.stan.example.springbootwithdockergradleexample.ctl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloCtl {
    @GetMapping("/")
    public String hello() {
        return "springboot with docker gradle ";
    }
}

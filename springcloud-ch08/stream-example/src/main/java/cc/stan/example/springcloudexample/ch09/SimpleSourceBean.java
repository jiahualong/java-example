package cc.stan.example.springcloudexample.ch09;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;

public class SimpleSourceBean {

    @Autowired
    private Source source;

    public void publishPersonChange(String personId) {

    }

}

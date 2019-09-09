package cc.stan.example.rocketmqsample;


import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SampleProducker {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void send(String msg) {
        SendResult sendResult = rocketMQTemplate.syncSend("test_topic:tag1", msg);
        System.out.println("sendResultMsgId," + sendResult.getMsgId());
    }
}

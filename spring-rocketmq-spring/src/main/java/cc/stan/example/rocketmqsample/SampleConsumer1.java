package cc.stan.example.rocketmqsample;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "test_topic", consumerGroup = "test_consumer_group_1")
public class SampleConsumer1 implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.println("接受的消息:" + message);
    }
}

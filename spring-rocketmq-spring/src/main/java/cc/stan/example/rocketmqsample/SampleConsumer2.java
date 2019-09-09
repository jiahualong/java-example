package cc.stan.example.rocketmqsample;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "test_topic", consumerGroup = "test_consumer_group_2")
public class SampleConsumer2 implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt message) {
        System.out.println("tag," + message.getTags() + ",msg," + new String(message.getBody()));
    }
}

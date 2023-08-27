package cn.itcast.mq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMessage2SimpleQueue() {
        String queueName = "simple.queue";
        String message = "Hello, world!";
        rabbitTemplate.convertAndSend(queueName, message);
    }


    @Test
    public void testSendMessage2WorkQueue() throws InterruptedException {
        String queueName = "simple.queue";
        String message = "Hello, message__";
        for (int i = 1; i <= 50; i++) {
            rabbitTemplate.convertAndSend(queueName, message + i);
            Thread.sleep(20);
        }
    }


    @Test
    public void testSendFanoutExchange() throws InterruptedException {
        //交换机名称
        String exchangeName = "itcast.fanout";
        //消息
        String message = "Hello, everyone";
        //发送
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }


    @Test
    public void testSendDirectExchange() throws InterruptedException {
        //交换机名称
        String exchangeName = "itcast.direct";
        //消息
        String message = "Hello, red";
        //发送
        rabbitTemplate.convertAndSend(exchangeName, "red", message);
    }


    @Test
    public void testSendTopicExchange() throws InterruptedException {
        //交换机名称
        String exchangeName = "itcast.topic";
        //消息
        String message = "当996成为福报";
        //发送
        rabbitTemplate.convertAndSend(exchangeName, "china.news", message);
    }
}

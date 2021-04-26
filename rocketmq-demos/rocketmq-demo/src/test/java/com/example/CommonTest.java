package com.example;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.Test;

public class CommonTest {

    @Test
    public void test() {
        System.out.println("aa");
    }

    @Test
    public void syncProducer() throws Exception {
        // Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("producer1");
        // Specify name server addresses.
        producer.setNamesrvAddr("localhost:9876");
        // Launch the instance.
        producer.start();
        for (int i = 0; i < 100; i++) {
            // Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest" /* Topic */, "TagA" /* Tag */,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            // Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        // Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }

    @Test
    public void asyncProducer() throws Exception {
        // Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("producer1");
        // Specify name server addresses.
        producer.setNamesrvAddr("localhost:9876");
        // Launch the instance.
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);

        int messageCount = 100;
        final CountDownLatch countDownLatch = new CountDownLatch(messageCount);
        for (int i = 0; i < messageCount; i++) {
            try {
                final int index = i;
                Message msg = new Message("TopicTest", "TagA", "OrderID188",
                        "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
                producer.send(msg, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        countDownLatch.countDown();
                        System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
                    }

                    @Override
                    public void onException(Throwable e) {
                        countDownLatch.countDown();
                        System.out.printf("%-10d Exception %s %n", index, e);
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        countDownLatch.await(5, TimeUnit.SECONDS);
        producer.shutdown();
    }

    @Test
    public void onewayProducer() throws Exception {
        // Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("producer1");
        // Specify name server addresses.
        producer.setNamesrvAddr("localhost:9876");
        // Launch the instance.
        producer.start();
        for (int i = 0; i < 100; i++) {
            // Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest" /* Topic */, "TagA" /* Tag */,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            // Call send message to deliver message to one of brokers.
            producer.sendOneway(msg);
        }
        // Wait for sending to complete
        Thread.sleep(5000);
        producer.shutdown();
    }

    @Test
    public void consumer() throws Exception {
        // Instantiate with specified consumer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer1");

        // Specify name server addresses.
        consumer.setNamesrvAddr("localhost:9876");

        // Subscribe one more more topics to consume.
        consumer.subscribe("TopicTest", "*");
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        // Launch the consumer instance.
        consumer.start();

        System.out.printf("Consumer Started.%n");
        while(true) {
            
        }
    }

}

package com.example.config;

import java.util.Arrays;

import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.property.MqttClientProperty;
import com.example.service.MqttMessageService;

@Configuration
public class MqttClientConfig {

    private MqttClientProperty clientProperty;
    
    public MqttClientConfig(MqttClientProperty clientProperty) {
        this.clientProperty = clientProperty;
    }

    @Bean
    public IMqttAsyncClient client() throws MqttException {
        MqttAsyncClient client = new MqttAsyncClient(clientProperty.getUri(), clientProperty.getId());
        return client;
    }

    @Bean
    @ConditionalOnBean(IMqttAsyncClient.class)
    public MqttCallbackExtended callback(IMqttAsyncClient client, MqttMessageService mqttMessageService) {
        return new MqttCallbackExtended() {

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                mqttMessageService.process(topic, new String(message.getPayload()));

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("deliveryComplete");
            }

            @Override
            public void connectionLost(Throwable cause) {
                System.out.println("connectionLost");

            }

            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                try {
                    String[] topics = clientProperty.getTopic();
                    int[] qoss = new int[topics.length];
                    Arrays.fill(qoss, 0);
                    client.subscribe(topics, qoss);
                } catch (MqttException e) {
                    e.printStackTrace();
                }

            }
        };
    }

    @Bean
    @ConditionalOnBean(MqttCallbackExtended.class)
    public ApplicationRunner runClient(IMqttAsyncClient client, MqttCallbackExtended callback) {
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {
                client.setCallback(callback);
                MqttConnectOptions option = new MqttConnectOptions();
                option.setAutomaticReconnect(true);
                option.setCleanSession(true);
                client.connect(option);
            }
        };

    }
}

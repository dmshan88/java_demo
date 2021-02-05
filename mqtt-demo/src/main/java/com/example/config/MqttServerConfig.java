package com.example.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.moquette.broker.Server;

@Configuration
public class MqttServerConfig {

    @Bean
    @ConditionalOnProperty("app.mqtt.broker.enable")
    public Server server() {
        Server server = new Server();
        return server;
    }

    @Bean
    @ConditionalOnBean(Server.class)
    public ApplicationRunner runServer(Server server) {
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {
                server.startServer();
            }
        };
    }

}

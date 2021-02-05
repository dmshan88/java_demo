package com.example.property;

import java.util.Arrays;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.mqtt.client")
public class MqttClientProperty {
    
    private String uri;
    
    private String id;
    
    private String[] topic;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getTopic() {
        return topic;
    }

    public void setTopic(String[] topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "MqttClientProperty [uri=" + uri + ", id=" + id + ", topic=" + Arrays.toString(topic) + "]";
    }
    

}

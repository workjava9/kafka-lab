package dev.lab.producer.kafka;

import dev.lab.producer.dto.UserEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventsProducer {

    private final KafkaTemplate<String, UserEvent> kafka;
    private final String topic;

    public EventsProducer(KafkaTemplate<String, UserEvent> kafka,
                          @Value("${topic.user-events}") String topic) {
        this.kafka = kafka;
        this.topic = topic;
    }

    public void send(UserEvent e) {
        kafka.send(topic, e.userId(), e);
    }
}

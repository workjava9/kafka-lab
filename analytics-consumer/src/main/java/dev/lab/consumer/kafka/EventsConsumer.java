package dev.lab.consumer.kafka;

import dev.lab.consumer.dto.UserEvent;
import dev.lab.consumer.metrics.EventsMetrics;
import io.micrometer.core.instrument.Metrics;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EventsConsumer {

    private final EventsMetrics metrics;

    public EventsConsumer(EventsMetrics metrics) {
        this.metrics = metrics;
    }

    @KafkaListener(topics = "${topic.user-events}")
    public void on(UserEvent e) {
        metrics.inc(e.type(), Metrics.globalRegistry);
    }
}

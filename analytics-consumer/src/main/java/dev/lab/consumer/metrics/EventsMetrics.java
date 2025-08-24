package dev.lab.consumer.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class EventsMetrics {

    private final Counter total;
    private final ConcurrentMap<String, Counter> byType = new ConcurrentHashMap<>();

    public EventsMetrics(io.micrometer.core.instrument.simple.SimpleMeterRegistry registry) {
        this.total = Counter.builder("user_events_total").register(registry);
    }

    public void inc(String type, MeterRegistry registry) {
        total.increment();
        byType.computeIfAbsent(type, t -> Counter.builder("user_events_by_type")
                .tag("type", t).register(registry)).increment();
    }
}

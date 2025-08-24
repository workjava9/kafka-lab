package dev.lab.consumer;

import dev.lab.consumer.metrics.EventsMetrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.Test;
import java.util.Objects;

public class EventsMetricsTest {
    @Test
    public void incrementsCounters() {
        var reg = new SimpleMeterRegistry();
        var m = new EventsMetrics(reg);
        m.inc("LOGIN", reg);
        m.inc("LOGIN", reg);
        m.inc("CLICK", reg);
        assert Objects.requireNonNull(reg.find("user_events_total").counter()).count() == 3.0;
        assert Objects.requireNonNull(reg.find("user_events_by_type").tag("type", "LOGIN").counter()).count() == 2.0;
    }
}

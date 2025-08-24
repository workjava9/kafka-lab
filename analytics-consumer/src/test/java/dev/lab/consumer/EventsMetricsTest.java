package dev.lab.consumer;

import dev.lab.consumer.metrics.EventsMetrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.Objects.requireNonNull;

public class EventsMetricsTest {

    @Test
    public void incrementsCounters() {
        var reg = new SimpleMeterRegistry();
        var m = new EventsMetrics(reg);

        m.inc("LOGIN", reg);
        m.inc("LOGIN", reg);
        m.inc("CLICK", reg);

        assertEquals(3.0, requireNonNull(reg.find("user_events_total").counter()).count());
        assertEquals(2.0, requireNonNull(
                reg.find("user_events_by_type").tag("type", "LOGIN").counter()
        ).count());
    }
}

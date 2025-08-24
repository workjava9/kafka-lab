package dev.lab.producer.web;

import dev.lab.producer.dto.UserEvent;
import dev.lab.producer.kafka.EventsProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventsController {

    private final EventsProducer producer;

    public EventsController(EventsProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody UserEvent e) {
        producer.send(e);
        return ResponseEntity.accepted().build();
    }
}

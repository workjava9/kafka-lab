package dev.lab.producer;

import dev.lab.producer.dto.UserEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.test.context.EmbeddedKafka;
import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {

                "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}"
        }
)
@EmbeddedKafka(partitions = 1, topics = { "user.events" })
class ProducerApplicationTests {

    @Autowired
    TestRestTemplate rest;

    @Test
    void postReturns202() {
        var body = new UserEvent("u1", "LOGIN", System.currentTimeMillis());
        var resp = rest.postForEntity("/events", body, Void.class);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
    }
}



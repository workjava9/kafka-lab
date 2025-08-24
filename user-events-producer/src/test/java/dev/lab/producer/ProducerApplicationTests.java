package dev.lab.producer;

import dev.lab.producer.dto.UserEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProducerApplicationTests {

    @Autowired
    TestRestTemplate rest;

    @Test
    void postReturns202() {
        ResponseEntity<Void> resp = rest.postForEntity("/events",
                new UserEvent("u1", "LOGIN", System.currentTimeMillis()), Void.class);
        assert resp.getStatusCode() == HttpStatus.ACCEPTED;
    }
}

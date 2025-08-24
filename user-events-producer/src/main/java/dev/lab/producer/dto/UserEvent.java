package dev.lab.producer.dto;

public record UserEvent(String userId, String type, long ts) {

}

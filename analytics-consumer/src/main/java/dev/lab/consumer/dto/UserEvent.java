package dev.lab.consumer.dto;

public record UserEvent(String userId, String type, long ts) {

}

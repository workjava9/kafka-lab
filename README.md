# Kafka Lab

Два сервиса для демонстрации Kafka: продюсер и консьюмер.
Kafka-lab — учебный проект: продюсер/консьюмер на Spring Boot + Kafka + Prometheus. 
Показывает работу event-driven архитектуры.

## Сервисы
- `user-events-producer` — REST `/events` принимает JSON `UserEvent` и публикует в Kafka топик `user.events`.
- `analytics-consumer` — читает `user.events`, считает метрики Micrometer и отдаёт их на `/actuator/prometheus`.

## Запуск
1. Подними Kafka и UI:
   ```bash
   docker compose up -d
   ```
   UI: http://localhost:8081

2. Запусти продюсер и консьюмер в двух терминалах:
   ```bash
   ./gradlew :user-events-producer:bootRun
   ./gradlew :analytics-consumer:bootRun
   ```

3. Отправь событие:
   ```bash
   curl -X POST localhost:8080/events -H 'Content-Type: application/json'      -d '{"userId":"u1","type":"LOGIN","ts":1690000000}'
   ```

4. Проверь метрики:
   ```bash
   curl localhost:8082/actuator/metrics/user_events_total
   ```

## Тесты
```
./gradlew test
```

## Что добавить дальше
- DLQ и ретраи для консьюмера
- Avro/Schema Registry
- Dockerfile + GitHub Actions

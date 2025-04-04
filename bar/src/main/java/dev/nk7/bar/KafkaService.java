package dev.nk7.bar;

import dev.nk7.navigator.api.Route;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
public class KafkaService {

  private static final Logger log = LoggerFactory.getLogger(KafkaService.class);

  private final KafkaProducer<String, String> kafkaProducer;

  public KafkaService(KafkaProducer<String, String> kafkaProducer) {
    this.kafkaProducer = Objects.requireNonNull(kafkaProducer);
  }


  void send(String payload, Route route) throws ExecutionException, InterruptedException {
    log.info("Начинаю отправлять сообщение '{}' в кафку по маршруту {}", payload, route);
    final String topic = route.routes().get("wf");
    final ProducerRecord<String, String> record = new ProducerRecord<>(topic, payload);
    kafkaProducer.send(record).get();
  }
}

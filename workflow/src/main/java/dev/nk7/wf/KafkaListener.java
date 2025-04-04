package dev.nk7.wf;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Objects;

@Component
public class KafkaListener implements CommandLineRunner {
  private static final Logger log = LoggerFactory.getLogger(KafkaListener.class);
  private final KafkaConsumer<String, String> consumer;

  public KafkaListener(KafkaConsumer<String, String> consumer) {
    this.consumer = Objects.requireNonNull(consumer);
  }

  void poll() {
    final ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

    for (final ConsumerRecord<String, String> record : records) {
      log.info("Получено сообщение от БАР-а: {}", record);
    }
  }

  @Override
  public void run(String... args) throws Exception {
    while (!Thread.currentThread().isInterrupted()) {
      poll();
      Thread.yield();
    }
  }
}

package dev.nk7.wf;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class Application {


  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  KafkaConsumer<String, String> kafkaConsumer(ApplicationProperties applicationProperties) {
    final KafkaConsumer<String, String> consumer = new KafkaConsumer<>(
      Map.of(
        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9091",
        ConsumerConfig.CLIENT_ID_CONFIG, "bar",
        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName(),
        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName(),
        ConsumerConfig.GROUP_ID_CONFIG, "wf-consumer-group"
      )
    );
    consumer.subscribe(List.of(applicationProperties.topic()));

    return consumer;
  }

}
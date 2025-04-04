package dev.nk7.bar;


import com.fasterxml.jackson.databind.ObjectMapper;
import dev.nk7.navigator.client.HttpNavigatorClient;
import dev.nk7.navigator.client.NavigatorClient;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.http.HttpClient;
import java.util.Map;

@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }


  @Bean
  public NavigatorClient navigatorClient(ObjectMapper objectMapper) {
    return new HttpNavigatorClient(HttpClient.newBuilder().build(), objectMapper);
  }


  @Bean
  KafkaProducer<String, String> kafkaProducer() {

    return new KafkaProducer<String, String>(
      Map.of(
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9091",
        ProducerConfig.CLIENT_ID_CONFIG, "bar",
        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName(),
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()
      )
    );
  }
}
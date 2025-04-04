package dev.nk7.bar;

import dev.nk7.navigator.api.Route;
import dev.nk7.navigator.client.NavigatorClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

@RestController
public class BarController {
  private static final Logger log = LoggerFactory.getLogger(BarController.class);

  private final KafkaService kafkaService;
  private final NavigatorClient navigatorClient;

  public BarController(KafkaService kafkaService, NavigatorClient navigatorClient) {
    this.kafkaService = Objects.requireNonNull(kafkaService);
    this.navigatorClient = Objects.requireNonNull(navigatorClient);
  }

  @GetMapping("/receive/{payload}")
  ResponseEntity<String> receive(@PathVariable("payload") String payload) throws ExecutionException, InterruptedException {
    log.info("Получено сообщение от участника с palyload = {}.", payload);
    final Route route = navigatorClient.assignRoute();
    kafkaService.send(payload, route);
    return ResponseEntity.ok(String.format("Отправлено по маршруту %s", route));
  }
}

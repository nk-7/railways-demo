package dev.nk7.navigator.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.nk7.navigator.api.Route;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.Optional;

public class HttpNavigatorClient implements NavigatorClient {

  private final HttpClient client;

  private final ObjectMapper objectMapper;


  public HttpNavigatorClient(HttpClient client, ObjectMapper objectMapper) {
    this.client = Objects.requireNonNull(client);
    this.objectMapper = Objects.requireNonNull(objectMapper);
  }

  @Override
  public Route assignRoute() {
    try {
      final HttpRequest request = HttpRequest.newBuilder(new URI("http://localhost:8080/assign"))
        .GET()
        .build();
      final HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

      final byte[] body = response.body();
      return objectMapper.readValue(body, Route.class);

    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public Optional<Route> getRouteById(String id) {
    throw new UnsupportedOperationException();
  }
}

package dev.nk7.navigator.api;

import java.util.Map;

public record Route(
  String id,
  int weight,
  Map<String, String> routes
) {
}

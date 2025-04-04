package dev.nk7.navigator.client;

import dev.nk7.navigator.api.Route;

import java.util.Optional;

public interface NavigatorClient {

  Route assignRoute();

  Optional<Route> getRouteById(String id);
}

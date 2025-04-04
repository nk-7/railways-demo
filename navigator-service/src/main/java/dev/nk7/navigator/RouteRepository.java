package dev.nk7.navigator;

import dev.nk7.navigator.api.Route;

import java.util.List;
import java.util.Optional;

public interface RouteRepository {

  void save(Route route);

  Optional<Route> getById(String id);

  List<Route> all();

  void reset(List<Route> routes);
}

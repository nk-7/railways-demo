package dev.nk7.navigator;

import dev.nk7.navigator.api.Route;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


@Repository
public class InMemoryRouteRepository implements RouteRepository {

  private final ConcurrentMap<String, Route> map = new ConcurrentHashMap<>();

  public InMemoryRouteRepository() {
    map.put("1", new Route("1", 7, Map.of("wf", "bar-to-wf-a")));
    map.put("2", new Route("2", 3, Map.of("wf", "bar-to-wf-b")));
  }

  @Override
  public void save(Route route) {
    map.put(route.id(), route);
  }

  @Override
  public Optional<Route> getById(String id) {
    return Optional.ofNullable(map.get(id));
  }

  @Override
  public List<Route> all() {
    return map.values().stream().toList();
  }
}

package dev.nk7.navigator;

import dev.nk7.navigator.api.Route;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class RouteServiceImpl implements RouteService {


  private final RouteRepository routeRepository;

  public RouteServiceImpl(RouteRepository routeRepository) {
    this.routeRepository = Objects.requireNonNull(routeRepository);
  }

  @Override
  public Route assignRoute() {
    final List<Route> routes = new ArrayList<>(routeRepository.all());
    routes.sort(Comparator.comparing(Route::weight));

    final int sum = routes.stream().mapToInt(Route::weight).sum();
    final int seed = RandomUtils.nextInt(1, sum);
    int count = 0;
    for (final Route route : routes) {
      count += route.weight();
      if (count > seed) {
        return route;
      }
    }
    return routes.getLast();
  }
}

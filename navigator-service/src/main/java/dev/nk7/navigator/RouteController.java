package dev.nk7.navigator;

import dev.nk7.navigator.api.Route;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class RouteController {

  private final RouteService routeService;

  public RouteController(RouteService routeService) {
    this.routeService = Objects.requireNonNull(routeService);
  }


  @GetMapping("/assign")
  ResponseEntity<Route> getRouteById() {
    return ResponseEntity.ok(routeService.assignRoute());
  }
}

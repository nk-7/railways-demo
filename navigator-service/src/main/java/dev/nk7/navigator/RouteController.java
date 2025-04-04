package dev.nk7.navigator;

import dev.nk7.navigator.api.Route;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

  @GetMapping("/routes")
  ResponseEntity<List<Route>> routes() {
    return ResponseEntity.ok(routeService.routes());
  }

  @PostMapping("/routes")
  ResponseEntity<List<Route>> routes(@RequestBody List<Route> routes) {
    return ResponseEntity.ok(routeService.routes());
  }
}

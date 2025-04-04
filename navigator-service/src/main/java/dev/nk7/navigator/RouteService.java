package dev.nk7.navigator;

import dev.nk7.navigator.api.Route;

import java.util.List;

public interface RouteService {

  Route assignRoute();


  List<Route> routes();

  void reset(List<Route> routes);

//  Route getById(String id);
}

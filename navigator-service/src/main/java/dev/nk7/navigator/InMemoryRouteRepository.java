package dev.nk7.navigator;

import dev.nk7.navigator.api.Route;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;
import java.util.stream.Collectors;


@Repository
public class InMemoryRouteRepository implements RouteRepository {

  private final ConcurrentMap<String, Route> map = new ConcurrentHashMap<>();

  private final ReadWriteLock lock = new ReentrantReadWriteLock();

  public InMemoryRouteRepository() {
    map.put("1", new Route("1", 7, Map.of("wf", "bar-to-wf-a")));
    map.put("2", new Route("2", 3, Map.of("wf", "bar-to-wf-b")));
  }

  @Override
  public void save(Route route) {
    Lock writeLock = null;
    try {
      writeLock = lock.writeLock();
      writeLock.lock();
      map.put(route.id(), route);
    } finally {
      if (writeLock != null) {
        writeLock.unlock();
      }
    }
  }

  @Override
  public Optional<Route> getById(String id) {
    Lock reedLock = null;
    try {
      reedLock = lock.writeLock();
      reedLock.lock();
      return Optional.ofNullable(map.get(id));
    } finally {
      if (reedLock != null) {
        reedLock.unlock();
      }
    }
  }

  @Override
  public List<Route> all() {
    Lock reedLock = null;
    try {
      reedLock = lock.writeLock();
      reedLock.lock();
      return map.values().stream().toList();
    } finally {
      if (reedLock != null) {
        reedLock.unlock();
      }
    }

  }

  @Override
  public void reset(List<Route> routes) {
    Lock writeLock = null;
    try {
      writeLock = lock.writeLock();
      writeLock.lock();
      map.clear();
      final Map<String, Route> collect = routes.stream().collect(Collectors.toMap(Route::id, Function.identity()));
      map.putAll(collect);
    } finally {
      if (writeLock != null) {
        writeLock.unlock();
      }
    }
  }

}

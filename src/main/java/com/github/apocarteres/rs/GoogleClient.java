package com.github.apocarteres.rs;

import java.io.Closeable;
import java.util.concurrent.ExecutorService;

/**
 * Http client class works on low level, it doesn't know about how and where it is going to be
 * used. It MIGHT control its own resources. In this particular class it allocates and releases resources.
 * The idea is to allocate/release resources in the same scope.
 */
public final class GoogleClient implements Closeable {
  private final String prefix;
  private final ExecutorService connectionManager;

  public GoogleClient(String prefix, ConnectionManagerFactory factory) {
    this.prefix = prefix;
    this.connectionManager = factory.build();
    System.out.println("GoogleClient is created");
  }

  @Override
  public void close() {
    new ExecutorShutdownLatch().shutdownAndWait("GoogleClient", connectionManager);
  }

  public void fetch(String path) {
    connectionManager.submit(() -> System.out.printf("GoogleClient: fetching %s/%s%n", prefix, path));
  }
}

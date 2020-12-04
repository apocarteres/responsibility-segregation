package com.github.apocarteres.rs;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Focuses only on business, so developer doesn't waste time on thinking of resource management.
 * <p>
 * (!) Easiest unit testing as a great bonus.
 */
public final class YahooClient implements Closeable {
  private final ExecutorService connectionManager;

  public YahooClient(ConnectionManagerFactory connectionManagerFactory) {
    this.connectionManager = connectionManagerFactory.build();
    System.out.println("YahooClient is created");
  }

  @Override
  public void close() {
    new ExecutorShutdownLatch().shutdownAndWait("YahooClient", connectionManager);
  }

  public void readNews(String url) {
    connectionManager.submit(() -> System.out.printf("YahooClient: fetching %s%n", url));
  }
}

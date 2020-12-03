package com.github.apocarteres.rs;

import java.io.Closeable;
import java.util.concurrent.ExecutorService;

/**
 * Focuses only on business, so developer doesn't waste time on thinking of resource management.
 * <p>
 * (!) Easiest unit testing as a great bonus.
 */
public final class YahooClient implements Closeable {
  private final ExecutorService connectionManager;

  public YahooClient(ConnectionManagerFactory connectionManagerFactory) {
    this.connectionManager = connectionManagerFactory.build();
  }

  public void readNews(String url) {
    System.out.printf("YahooClient: fetching %s%n", url);
  }

  @Override
  public void close() {
    connectionManager.shutdown();
    System.out.println("YahooClient was destroyed");
  }
}

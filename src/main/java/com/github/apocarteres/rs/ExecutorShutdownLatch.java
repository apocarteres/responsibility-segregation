package com.github.apocarteres.rs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public final class ExecutorShutdownLatch {
  void shutdownAndWait(String scope, ExecutorService service) {
    service.shutdown();
    try {
      do {
        System.out.printf("%s is being shutdown%n", scope);
      } while (!service.awaitTermination(1, TimeUnit.MINUTES));
    } catch (InterruptedException e) {
      throw new RuntimeException(format("unable to properly shutdown executor for '%s'", scope), e);
    }
    System.out.printf("%s was successfully shutdown%n", scope);
  }
}

package com.github.apocarteres.rs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * Global application context. Should be managing other, smaller contexts.
 * <p>
 * Also it might have its own resources, which should be managed in it's scope
 * and should never be exposed though getters or be injected into other components.
 * <p>
 */
public final class ApplicationContext implements DisposableContext {
  private final HttpContextFactory httpContextFactory;
  private final ExecutorService otherPurposeExecutorService;

  public ApplicationContext(HttpContextFactory httpContextFactory) {
    this.httpContextFactory = httpContextFactory;
    otherPurposeExecutorService = Executors.newSingleThreadExecutor(); // some other resources belong to ApplicationContext itself
  }

  public void runInHttpContext(Consumer<HttpContext> fn) {
    try (HttpContext httpContext = httpContextFactory.create()) { // this is where are actually allocating resources
      fn.accept(httpContext);
    }
  }

  @Override
  public void close() {
    otherPurposeExecutorService.shutdown();
  }
}

package com.github.apocarteres.rs;

/**
 * Decouples HTTP context creating and use cases where it applies.
 */
public interface HttpContextFactory {
  HttpContext create();
}

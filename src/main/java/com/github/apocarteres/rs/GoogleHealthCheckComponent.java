package com.github.apocarteres.rs;

/**
 * Business component which does something with content from Google and
 * doesn't care about Google Client lifecycle.
 */
public final class GoogleHealthCheckComponent {
  private final GoogleClient googleClient;

  public GoogleHealthCheckComponent(GoogleClient googleClient) {
    this.googleClient = googleClient;
  }

  public void checkHealth() {
    googleClient.fetch("healthcheck/status");
  }

  public void scanIndex() {
    googleClient.fetch("scan/index");
  }
}

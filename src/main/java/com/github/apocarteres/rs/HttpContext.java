package com.github.apocarteres.rs;

/**
 * Provides access to HTTP clients. As long as clients are allowed to be accessed only in context scope,
 * we can safely share client instances. It never should be the case when client will be called AFTER it has been
 * disposed.
 */
public final class HttpContext implements DisposableContext {
  private final GoogleClient googleClient;
  private final YahooClient yahooClient;

  public HttpContext(ConnectionManagerFactory googleConnectionFactory, ConnectionManagerFactory yahooConnectionFactory) {
    this.googleClient = new GoogleClient("https://google", googleConnectionFactory);
    this.yahooClient = new YahooClient(yahooConnectionFactory);
  }

  public GoogleClient getGoogleClient() {
    return googleClient;
  }

  public YahooClient getYahooClient() {
    return yahooClient;
  }

  @Override
  public void close() {
    googleClient.close();
    yahooClient.close();
  }

}

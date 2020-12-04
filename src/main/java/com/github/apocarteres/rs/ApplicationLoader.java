package com.github.apocarteres.rs;

import java.util.concurrent.Executors;

public final class ApplicationLoader {
  public static void main(String[] args) {
    System.out.println("app is starting");

    //at this step we are not allocating anything, only definitions. So, for example, in unit test those
    //factories will be replaced with mock easily.
    HttpContextFactory httpContextFactory = () -> {
      ConnectionManagerFactory googleConnectionFactory = Executors::newCachedThreadPool; // Google specific threads
      ConnectionManagerFactory yahooConnectionFactory = () -> Executors.newFixedThreadPool(8); // Yahoo specific threads
      return new HttpContext(googleConnectionFactory, yahooConnectionFactory);
    };
    try (ApplicationContext context = new ApplicationContext(httpContextFactory)) {
      //every component requires access to HTTP clients run this way (in http context), it is always SAFE.
      //Benefits are:
      // - it is IMPOSSIBLE to have a memory/file/socket/thread leak in this design.
      // - developer doesn't waste time on thinking about resources management.
      context.runInHttpContext(h -> {
        new GoogleHealthCheckComponent(h.getGoogleClient()).checkHealth();
        new YahooNewsReader(h.getYahooClient()).readNews();
        new GoogleHealthCheckComponent(h.getGoogleClient()).scanIndex();
        new YahooNewsReader(h.getYahooClient()).listEmails();
      });

      //other context here
      //other context here
      //other context here
    }
    // (!) It is obvious we can easily re-create ApplicationContext safely at any point of time.
    System.out.println("app is finished");
  }
}

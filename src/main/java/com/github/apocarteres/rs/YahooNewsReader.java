package com.github.apocarteres.rs;

/**
 * Business component which does something with content from Yahoo and
 * doesn't care about Yahoo Client lifecycle.
 *
 * Even in case of exceptions or even {@link Error}'s this way of using the client is safe,
 * cause it is being managed on different level.
 */

public final class YahooNewsReader {
  private final YahooClient yahooClient;

  public YahooNewsReader(YahooClient yahooClient) {
    this.yahooClient = yahooClient;
  }

  public void readNews() {
    yahooClient.readNews("https://www.yahoo.com/news/read/all");
  }

  public void listEmails() {
    yahooClient.readNews("https://www.yahoo.com/email/list");
  }
}

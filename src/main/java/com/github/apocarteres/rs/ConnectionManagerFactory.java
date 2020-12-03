package com.github.apocarteres.rs;

import java.util.concurrent.ExecutorService;

/**
 * Provides connection manager. Instead of direct instantiating thread pools in Google Client we
 * use this factory to decouple Google/Yahoo Client's logic from connection manager implementation details.
 *
 * Examples where it might be useful:
 * 1. We need to mute client with no code changes, so we spawn next client with dummy connection manager.
 * 2. We want to have unit tests with no hacks like "Thread.sleep".
 * 3. We want to make sure system won't run out of file descriptors when unit tests spawn tons of real thread pools.
 * 4. We don't want to see "Address already in use" or "Socket is already closed" excpetions.
 */
public interface ConnectionManagerFactory {
  ExecutorService build();
}

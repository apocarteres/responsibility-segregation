package com.github.apocarteres.rs;

import java.io.Closeable;

/**
 * Explicitly identifies implementation class as "disposable" and
 * which means that class has to be instantiated strictly in "try-with-resources" block.
 */
public interface DisposableContext extends Closeable {

}

package org.hamcrestcollections;

public interface Function<T, U> {
    U apply(T t);
}

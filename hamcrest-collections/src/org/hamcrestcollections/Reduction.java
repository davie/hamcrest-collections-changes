package org.hamcrestcollections;

public interface Reduction<T> {
    T apply(T first, T second);
}

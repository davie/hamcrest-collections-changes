package org.hamcrestcollections;

/**
 * <p>
 * A {@code Reducer} quite literally reduces two arguments down to one. Used as part of a {@link Reduction}.
 * </p>
 *
 * <p>
 * Examples of a {@code Reducer} might include a sum function that adds all the items of a list together, or a
 * string builder used to concatinate many strings together
 * </p?
 *
 * @see Reduction
 * @author Sam Newman (sam-newman@magpiebrain.com)
 */
public interface Reducer<T> {
    T apply(T first, T second);
}

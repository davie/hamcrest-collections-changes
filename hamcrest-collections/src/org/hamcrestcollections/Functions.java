package org.hamcrestcollections;

/**
 * A set of (hopefully) useful {@link Function} implementations
 *
 * @see Function
 * @author Sam Newman (sam-newman@magpiebrain.com)
 */
public class Functions {

    /**
     * Returns the toString() of the passed in value
     *
     * @return the asString function
     */
    public static <T> Function<T, String> asString() {
        return new Function<T, String>() {
            public String apply(T t) {
                return t.toString();
            }
        };
    }
}

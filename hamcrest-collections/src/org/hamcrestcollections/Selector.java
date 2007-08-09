package org.hamcrestcollections;

import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Applies a Hamcrest {@link Matcher} to a collection, returning the items that match
 * </p>
 * For Example:
 * </p>
 * <pre>
 * import static org.hamcrest.Matchers.greaterThan;
 * ...
 * Collection<Integer> ints = new ArrayList<Integer>();
 * ints.add(1);
 * ints.add(2);
 * ints.add(3);
 * ints.add(4);
 * <p/>
 * Iterable<Integer> threeAndFour = Selector.select(ints, greaterThan(2);
 * </pre>
 *
 * @author Sam Newman (sam-newman@magpiebrain.com)
 * @see org.hamcrest.Matcher
 */
public class Selector {

    /**
     * Returns all those {@code items} that match the given Hamcrest {@link Matcher}
     *
     * @param items   The collection to run the matcher over
     * @param matcher The Matcher to apply
     * @return The items that match
     */
    public static <T> Iterable<T> select(Iterable<T> items, Matcher<T> matcher) {
        List<T> matching = new ArrayList<T>();

        for (T item : items) {
            if (matcher.matches(item)) {
                matching.add(item);
            }
        }
        return matching;
    }

    public static <T> Iterable<T> select(T items[], Matcher<T> matcher) {
        return returnMathcing(items, matcher);
    }

    private static <T> Iterable<T> returnMathcing(T[] items, Matcher<T> matcher) {
        List<T> matching = new ArrayList<T>();

        for (T item : items) {
            if (matcher.matches(item)) {
                matching.add(item);
            }
        }
        return matching;
    }
}

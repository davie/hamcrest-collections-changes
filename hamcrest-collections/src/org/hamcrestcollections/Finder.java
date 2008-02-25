package org.hamcrestcollections;

import org.hamcrest.Matcher;

import java.util.List;

/**
 * Applies a Hamcrest {@link Matcher} to a collection, returning the first item that matches.
 * Return null if no matching item found.
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
 * Iterable<Integer> threeAndFour = Finder.find(ints, greaterThan(2);
 * </pre>
 *
 * @author Davie Moston (daviemoston@gmail.com)
 * @see org.hamcrest.Matcher
 */

public class Finder {

    public static <T> T find(Iterable<T> items, Matcher<T> matcher) {
        for (T item : items) {
            if (matcher.matches(item)) {
                return item;
            }
        }
        return null;
    }

    public static <T> boolean canFind(Iterable<T> items, Matcher<T> matcher) {
        for (T item : items) {
            if (matcher.matches(item)) {
                return true;
            }
        }
        return false;
    }
}

package org.hamcrestcollections;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

/**
 * Like the python {@code zip()} finction, zips lists together.
 */
public class Zipper {

    /**
     * <p>
     * Not quite as sopphisticated as a python {@code zip()} - items of {@code first} become the keys
     * to a {@code Map}, items of {@code second} become the values.
     * </p>
     *
     * <p>
     * Where {@code first} is shorter than {@code second}, or {@code second} is longer than first, then
     * the extra values are ignored
     * <p>
     *
     * <p>
     * Example:
     * </p>
     *
     * <pre>
     * List<String> names = ListUtils.asList("Norman", "Derek", "John");
     * List<Integer> ages = ListUtils.asList("23, 56, 23);
     * Map<String, Integer> result = Zipper.zip(names, ages);
     * </pre>
     *
     * @param first The first collection to zip
     * @param second The second collection to zip
     * @return A map with the keys from the first collection and values from the second
     */
    public static <T, U> Map<T, U> zip(Iterable<T> first, Iterable<U> second) {
        Map<T, U> result = new HashMap<T, U>();

        Iterator<T> firstIter = first.iterator();
        Iterator<U> secondIter = second.iterator();

        while (firstIter.hasNext() && secondIter.hasNext()) {
            result.put(firstIter.next(), secondIter.next());
        }

        return result;
    }
}

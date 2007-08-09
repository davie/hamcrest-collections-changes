package org.hamcrestcollections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Like the python {@code zip()} finction, zips lists together.
 * </p>
 * Not quite as sopphisticated as the python version
 * (as the python version can take many lists and return a list of tuples)
 * {@code zip()} - items of the first list become the keys
 * to a {@code Map}, items of the second become the values.
 * </p>
 * Example:
 * </p>
 * <pre>
 * List<String> names = ListUtils.asList("Norman", "Derek", "John");
 * List<Integer> ages = ListUtils.asList("23, 56, 23);
 * Map<String, Integer> result = Zipper.zip(names, ages);
 * </pre>
 *
 * @author Sam Newman (sam-newman@magpiebrain.com)
 */
public class Zipper {

    /**
     * Zips two lists together to form a map. For example useage, see the class documentation.
     * </p>
     * Where {@code first} is shorter than {@code second}, or {@code second} is longer than first, then
     * the extra values are ignored. If either list is empty or null, then an empty map is returned
     * </p>
     * @param first  The first collection to zip
     * @param second The second collection to zip
     * @return A map with the keys from the first collection and values from the second
     */
    public static <T, U> Map<T, U> zip(Iterable<T> first, Iterable<U> second) {
        Map<T, U> result = new HashMap<T, U>();

        if ((first == null) || (second == null)) {
            return result;
        }

        Iterator<T> firstIter = first.iterator();
        Iterator<U> secondIter = second.iterator();

        while (firstIter.hasNext() && secondIter.hasNext()) {
            result.put(firstIter.next(), secondIter.next());
        }

        return result;
    }
}

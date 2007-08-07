package org.hamcrestcollections;

import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SelectMatcher<T> {
    public static <T> Iterable<T> select(Collection<T> items, Matcher<T> matcher) {
        List<T> matching = new ArrayList<T>();

        for (T item : items) {
            if (matcher.matches(item)) {
                matching.add(item);
            }
        }
        return matching;
    }
}

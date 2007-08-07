package org.hamcrestcollections;

import org.hamcrest.Matcher;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class RejectMatcher {

    public static <T> Iterable<T> reject(Collection<T> items, Matcher<T> matcher) {
        List<T> matching = new ArrayList<T>();

        for (T item : items) {
            if (!matcher.matches(item)) {
                matching.add(item);
            }
        }
        return matching;
    }
}

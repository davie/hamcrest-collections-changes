package org.hamcrestcollections;

import java.util.ArrayList;
import java.util.List;

public class FunctionMapper {
    public static <T, U> Iterable<U> map(Iterable<T> items, Function<T, U> function) {
        List<U> results = new ArrayList<U>();

        for (T item : items) {
            results.add(function.apply(item));
        }
        
        return results;
    }
}

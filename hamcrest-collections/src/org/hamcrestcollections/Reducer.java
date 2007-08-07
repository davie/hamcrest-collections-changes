package org.hamcrestcollections;

import java.util.List;

public class Reducer {
    public static <T> T reduce(Iterable<T> iterable, Reduction<T> reduction) {
        List<T> items = ListUtils.asList(iterable);
        T currentValue = null;

        if (items.size() == 0) {
            throw new InvalidReductionException("Cannot reduce zero items");
        }
        
        if (items.size() == 1) {
            return items.get(0);
        }

        T first = items.get(0);
        T second = items.get(1);
        currentValue = reduction.apply(first, second);

        for (int i = 2; i < items.size(); i++) {
            currentValue = reduction.apply(currentValue, items.get(i));
        }
        
        return currentValue;
    }

}

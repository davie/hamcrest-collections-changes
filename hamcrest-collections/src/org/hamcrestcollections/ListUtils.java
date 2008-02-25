package org.hamcrestcollections;

import org.hamcrest.Matcher;
import org.hamcrest.Description;
import org.hamcrest.BaseMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * Some general utility functions for working with lists
 *
 * @author Sam Newman (sam-newman@magpiebrain.com)
 */
public class ListUtils {
    public static <T> List<T> asList(Iterable<T> iterable) {
        if (List.class.isAssignableFrom(iterable.getClass())) {
            return (List<T>)iterable;
        } else {
            List<T> list = new ArrayList<T>();
            for (T item : iterable) {
                list.add(item);
            }
            return list;
        }
    }

    public static <T> boolean containsExactly(List<T> items, T... itemsToMatch) {
        if (items.size() != itemsToMatch.length) {
            return false;
        }
        
        for (int i = 0; i < items.size(); i++) {
            if (!items.get(i).equals(itemsToMatch[i])) {
                return false;
            }
        }
        return true;
    }

    public static <T> List<T> listWith(T... values) {
        List<T> list = new ArrayList<T>();
        list.addAll(Arrays.asList(values));
        return list;
    }

    public static <T> List<T> emptyList() {
        return new ArrayList<T>();
    }
}

package org.hamcrestcollections;

import org.hamcrest.Matcher;
import org.hamcrest.Description;
import org.hamcrest.BaseMatcher;

import java.util.*;

import com.sun.javaws.exceptions.InvalidArgumentException;

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

    public static <T> T head(T... values) {
        List<T> listOfValues = Arrays.asList(values);
        return head(listOfValues);
    }

    public static <T> T head(Iterable<T> list) {
        Iterator<T> iterator = list.iterator();
        if(!iterator.hasNext()){
            throw new RuntimeException("Attempted to find head of empty list");
        }
        return iterator.next();
    }

    public static <T> T tail(T... values) throws InvalidArgumentException {
        return tail(Arrays.asList(values));
    }

    public static <T> T tail(Iterable<T> values) throws InvalidArgumentException {
        Iterator<T> iterator = values.iterator();
        if(!iterator.hasNext()){
            throw new InvalidArgumentException(new String[]{"Attempted to call tail on an emtpy list"});
        }
        
        T value = null;
        while(iterator.hasNext()){
            value = iterator.next();
        }

        return value;
    }
}

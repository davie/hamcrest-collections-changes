package org.hamcrestcollections;

public class Functions {
    public static <T> Function<T, String> asString() {
        return new Function<T, String>() {
            public String apply(T t) {
                return t.toString();
            }
        };
    }
}

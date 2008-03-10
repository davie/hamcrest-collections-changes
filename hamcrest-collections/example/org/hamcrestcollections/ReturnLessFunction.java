package org.hamcrestcollections;

public abstract class ReturnLessFunction<T> implements Function<T, Object> {
    public Object apply(T t) {
        applyWithoutReturn(t);
        return null;
    }

    protected abstract void applyWithoutReturn(T t);
}

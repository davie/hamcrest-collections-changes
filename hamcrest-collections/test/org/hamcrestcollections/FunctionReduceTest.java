package org.hamcrestcollections;

import junit.framework.TestCase;

import java.util.List;

import org.junit.Test;import static org.junit.Assert.assertEquals;

public class FunctionReduceTest {

    public void testShouldReduceElementsInCollection() {
        List<Integer> list = ListUtils.listWith(1, 2, 3);
        assertEquals(6, (int) Reduction.reduce(list, new Sum()));
    }

    public void testShouldReduceTwoItems() {
        List<Integer> list = ListUtils.listWith(1, 2);
        assertEquals(3, (int) Reduction.reduce(list, new Sum()));
    }

    public void testShouldReduceASingleItem() {
        List<Integer> list = ListUtils.listWith(1);
        assertEquals(1, (int) Reduction.reduce(list, new Sum()));
    }

    @Test(expected = InvalidReductionException.class)
    public void testShouldNotReduceEmptyValues() {
        List<Integer> list = ListUtils.emptyList();
        Reduction.reduce(list, new Sum());
    }

    public static class Sum implements Reducer<Integer> {
        public Integer apply(Integer first, Integer second) {
            return first + second;
        }
    }

}


package org.hamcrestcollections;

import junit.framework.TestCase;

import java.util.List;

public class FunctionReduceTest extends TestCase {

    public void testShouldReduceElementsInCollection() {
        List<Integer> list = ListUtils.listWith(1, 2, 3);
        assertEquals(6, (int) Reducer.reduce(list, new Sum()));
    }

    public void testShouldReduceTwoItems() {
        List<Integer> list = ListUtils.listWith(1, 2);
        assertEquals(3, (int) Reducer.reduce(list, new Sum()));
    }

    public void testShouldReduceASingleItem() {
        List<Integer> list = ListUtils.listWith(1);
        assertEquals(1, (int) Reducer.reduce(list, new Sum()));
    }

    public void testShouldNotReduceEmptyValues() {
        List<Integer> list = ListUtils.emptyList();

        try {
            Reducer.reduce(list, new Sum());
            fail("InvalidReductionException expected");
        } catch (InvalidReductionException e) {
            //expected
        }
    }

    public static class Sum implements Reduction<Integer> {
        public Integer apply(Integer first, Integer second) {
            return first + second;
        }
    }

}


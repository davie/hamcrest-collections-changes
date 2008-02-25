package org.hamcrestcollections;

import junit.framework.TestCase;
import org.junit.Test;import static org.junit.Assert.assertEquals;import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.Arrays;

import com.sun.javaws.exceptions.InvalidArgumentException;

public class ListUtilsTest {
    @Test
    public void testShouldReturnFirstItemAsHead(){
        int head = ListUtils.head(1, 2, 3);
        assertEquals(1, head);
    }

    @Test
    public void testShouldReturnHeadOfListWithOneItem(){
        String head = ListUtils.head("b", "a");
        assertEquals("b", head);
    }

    @Test(expected = RuntimeException.class)
    public void testShouldThrowExceptionWhenAttemptingToFindHeadOfEmptyArray(){
        ListUtils.head();
        fail();
    }

    @Test(expected = RuntimeException.class)
    public void testShouldThrowExceptionWhenAttemptingToFindHeadOfEmptyList(){
        ListUtils.head(Collections.EMPTY_LIST);
        fail("should have thrown an exception");
    }

    @Test
    public void testShouldReturnLastEntryAsTail() throws InvalidArgumentException {
        String head = ListUtils.tail("first", "middle", "last");
        assertEquals("last", head);
    }

    @Test
    public void testShouldReturnLastEntryOfListAsTail() throws InvalidArgumentException {
        String head = ListUtils.tail(Arrays.asList("first", "middle", "last"));
        assertEquals("last", head);
    }

    @Test(expected = InvalidArgumentException.class)
    public void testShouldThrowExceptionWhenTailCalledOnEmptyArray() throws InvalidArgumentException {
        ListUtils.tail();
        fail("should have thrown an exception");
    }
}

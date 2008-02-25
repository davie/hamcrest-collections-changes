package org.hamcrestcollections;

import junit.framework.TestCase;

import java.util.Arrays;

import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.Test;import static org.junit.Assert.assertEquals;import static org.junit.Assert.assertNull;import static org.junit.Assert.assertTrue;import static org.junit.Assert.assertFalse;

public class FinderTest {
    @Test
    public void shouldFindFirstMatchedElement() {
        Integer ints[] = {1, 2, 2, 4};

        int matching = Finder.find(Arrays.asList(ints), equalTo(2));
        assertEquals(2, matching);
    }

    @Test
    public void shouldReturnNullIfNoElementMatches() {
        Integer ints[] = {1, 2, 2, 4};

        int matching = Finder.find(Arrays.asList(ints), equalTo(3));
        assertNull(matching);
    }

    @Test
    public void testCanFindReturnsTrueIfElementMatches() {
        Integer ints[] = {1, 2, 2, 4};

        assertTrue(Finder.canFind(Arrays.asList(ints), equalTo(2)));
    }
    @Test
    public void testCanFindReturnsFalseIfNoElementsMatch() {
        Integer ints[] = {1, 2, 2, 4};

        assertFalse(Finder.canFind(Arrays.asList(ints), equalTo(3)));
    }

}

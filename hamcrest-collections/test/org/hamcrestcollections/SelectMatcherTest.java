package org.hamcrestcollections;

import junit.framework.TestCase;
import static org.hamcrest.core.IsEqual.equalTo;
import org.hamcrest.Matcher;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import static org.hamcrestcollections.Selector.select;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SelectMatcherTest extends TestCase {

    public void testShouldApplyMatcherToAllItemsInCollection() {
        Collection<Integer> ints = new ArrayList<Integer>();
        ints.add(1);
        ints.add(2);
        ints.add(2);
        ints.add(4);

        List<Integer> matching = ListUtils.asList(select(ints, equalTo(2)));
        assertTrue(ListUtils.containsExactly(matching, 2, 2));
    }

    public void testShouldApplyMatcherToAllItemsInArray() {
        Integer ints[] = {1, 2, 2, 4};

        List<Integer> matching = ListUtils.asList(select(ints, equalTo(2)));
        assertTrue(ListUtils.containsExactly(matching, 2, 2));
    }

}

package org.hamcrestcollections;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RejectMatcherTest extends TestCase {
    public void testShouldRejectItemsThatMatchMatcher() {
        Collection<Integer> ints = new ArrayList<Integer>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(4);

        List<Integer> remaining = ListUtils.asList(RejectMatcher.reject(ints, org.hamcrest.Matchers.greaterThan(2)));
        ListUtils.containsExactly(remaining, 1, 2);
    }
}

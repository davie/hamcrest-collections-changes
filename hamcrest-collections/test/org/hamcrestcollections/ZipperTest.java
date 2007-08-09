package org.hamcrestcollections;

import junit.framework.TestCase;

import java.util.List;
import java.util.Map;

public class ZipperTest extends TestCase {
    private static final Integer STANDARD_PAY = 25000;
    private static final Integer LOWER_PAY = 20000;
    private static final Integer HIGH_PAY = 40000;

    public void testShouldZipListsOfEqualLength() {
        List<String> names = ListUtils.listWith("Clive", "Derek", "Peter", "Dudley");
        List<Integer> pay = ListUtils.listWith(STANDARD_PAY, LOWER_PAY, HIGH_PAY, STANDARD_PAY);

        Map<String, Integer> map = Zipper.zip(names, pay);

        assertTrue(map.get("Clive").equals(STANDARD_PAY));
        assertTrue(map.get("Derek").equals(LOWER_PAY));
        assertTrue(map.get("Peter").equals(HIGH_PAY));
        assertTrue(map.get("Dudley").equals(STANDARD_PAY));
    }

    public void testShouldIgnoreExtraValuesOfSecondListIfFirstIsShorter() {
        List<String> shortList = ListUtils.listWith("Clive", "Derek");
        List<Integer> longList = ListUtils.listWith(STANDARD_PAY, LOWER_PAY, HIGH_PAY, STANDARD_PAY);

        Map<String, Integer> map = Zipper.zip(shortList, longList);

        assertTrue(map.get("Clive").equals(STANDARD_PAY));
        assertTrue(map.get("Derek").equals(LOWER_PAY));
    }

    public void testShouldIgnoreExtraValuesOfFirstListIfSecondIsShorter() {
        List<String> longList = ListUtils.listWith("Clive", "Derek", "Peter", "Dudley");
        List<Integer> shortList = ListUtils.listWith(STANDARD_PAY, LOWER_PAY);

        Map<String, Integer> map = Zipper.zip(longList, shortList);

        assertTrue(map.get("Clive").equals(STANDARD_PAY));
        assertTrue(map.get("Derek").equals(LOWER_PAY));
    }

    public void testShouldReturnEmptyMapIfEitherListIsEmpty() {
        List<String> firstList = ListUtils.emptyList();
        List<Integer> secondList = ListUtils.listWith(STANDARD_PAY, LOWER_PAY);
        Map<String, Integer> map = Zipper.zip(firstList, secondList);
        assertTrue("Map is expected to be empty", map.isEmpty());

        firstList = ListUtils.emptyList();
        secondList = ListUtils.emptyList();
        map = Zipper.zip(firstList, secondList);
        assertTrue("Map is expected to be empty", map.isEmpty());

        firstList = ListUtils.listWith("Clive", "Derek", "Peter", "Dudley");
        secondList = ListUtils.emptyList();
        map = Zipper.zip(firstList, secondList);
        assertTrue("Map is expected to be empty", map.isEmpty());
    }

    public void testsShouldReturnEmptyMapIfEitherListIsNull() {
        List<String> firstList = null;
        List<Integer> secondList = ListUtils.listWith(STANDARD_PAY, LOWER_PAY);
        Map<String, Integer> map = Zipper.zip(firstList, secondList);
        assertTrue("Map is expected to be empty", map.isEmpty());

        firstList = null;
        secondList = null;
        map = Zipper.zip(firstList, secondList);
        assertTrue("Map is expected to be empty", map.isEmpty());

        firstList = ListUtils.listWith("Clive", "Derek", "Peter", "Dudley");
        secondList = null;
        map = Zipper.zip(firstList, secondList);
        assertTrue("Map is expected to be empty", map.isEmpty());
    }

}

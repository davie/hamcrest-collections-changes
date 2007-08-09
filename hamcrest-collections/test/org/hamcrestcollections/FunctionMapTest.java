package org.hamcrestcollections;

import org.jmock.Mock;
import org.jmock.MockObjectTestCase;

import java.util.List;

public class FunctionMapTest extends MockObjectTestCase {
    public void testShouldRunFunctionOnAllItems() {
        Mock functionMock = mock(Function.class);

        functionMock.expects(once()).method("apply").with(eq(1)).will(returnValue("Hello"));
        functionMock.expects(once()).method("apply").with(eq(2)).will(returnValue("World"));

        List<Integer> ints = ListUtils.listWith(1, 2);
        FunctionMapper.map(ints, (Function<Integer,String>)functionMock.proxy());
    }
}

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*dedupe, 2015

Created Nov 24, 2015 by Esteban Valle

Copyright © 2015  Esteban Valle. All rights reserved.

+1-775-351-4427
esteban@thevalledesign.com
http://facebook.com/SeniorShizzle
*/
public class SeparateChainHashSTTest {

    /** a class wherein all objects hash to the same value, but do not .equals() */
    public class HashToSame{
        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }

    SequentialSearchST<Integer, Integer> table;

    @Before
    public void setUp() throws Exception {
        table = new SequentialSearchST<>();
    }

    @Test
    public void testPutAndGet() throws Exception {
        table.put(1, 1);
        table.put(2, 2);

        assertTrue(table.get(1) == 1);

        table.put(1, 3);

        assertTrue(table.get(1) == 3);

    }

    @Test
    public void testContains() throws Exception {
        table.put(88, 88);
        assertTrue(table.contains(88));

        table.delete(88);
        assertFalse(table.contains(88));

    }

    @Test
    public void testIsEmpty() throws Exception {
        assertTrue(table.isEmpty());
        table.put(1, 1);
        assertFalse(table.isEmpty());
        table.delete(1);
        assertTrue(table.isEmpty());
    }

    @Test
    public void testSize() throws Exception {
        table.put(1, 1);
        assertEquals(table.size(), 1);

        table.put(9999, 9999);
        assertEquals(table.size(), 2);

        table.delete(1);
        assertEquals(table.size(), 1);

    }

    @Test
    public void testKeys() throws Exception {
        table.put(1, 1);
        table.put(2, 2);
        table.put(3, 3);

        for (Integer i : table.keys()){
            assertTrue(i == 1 || i == 2 || i == 3);
        }

    }
}
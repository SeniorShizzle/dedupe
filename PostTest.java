import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/*dedupe, 2015

Created Nov 23, 2015 by Esteban Valle

Copyright © 2015  Esteban Valle. All rights reserved.

+1-775-351-4427
esteban@thevalledesign.com
http://facebook.com/SeniorShizzle
*/
public class PostTest {

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.Test
    public void testClone() throws Exception {

        Post post = new Post();
        Post otherPost = new Post();

        assertNotSame(post, otherPost);
        assertFalse(post == otherPost);

        otherPost = (Post)post.clone();

        assertNotSame(post, otherPost);
        assertFalse(post == otherPost);


    }

    @org.junit.Test
    public void testEquals() throws Exception {

        Post post = new Post();
        Post otherPost = new Post();

        assertFalse(post.equals(otherPost));

        otherPost = (Post)post.clone();

        assertTrue(post.equals(otherPost));
    }

    @org.junit.Test
    public void testHashCode() throws Exception {
        Post post = new Post();
        Post otherPost = new Post();

        assertNotEquals(post.hashCode(), otherPost.hashCode());

        otherPost = (Post)post.clone();
        //System.out.println(post + "\n" + otherPost);
        assertEquals(post.hashCode(), otherPost.hashCode());


        int testSize = 100000;
        //ArrayList<Integer> vals = new ArrayList<>(testSize);
        Set<Integer> vals = new HashSet<>(testSize);

        for (int i = 0; i < testSize; i++) {
            if (!vals.add(new Post().hashCode())) fail("Duplicate numbers at " + i);
        }

        //System.out.println(vals);

    }
}
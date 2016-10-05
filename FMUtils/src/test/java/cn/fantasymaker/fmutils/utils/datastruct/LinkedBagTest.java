package cn.fantasymaker.fmutils.utils.datastruct;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created :  2016-10-04
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class LinkedBagTest {
    @Test
    public void isEmpty() throws Exception {
        // test init
        LinkedBag<Integer> linkedBag = new LinkedBag<>();
        assertTrue(linkedBag.isEmpty());

        // test not empty
        for(int i = 0; i < 100000; i++){
            linkedBag.add(i);
            assertFalse(linkedBag.isEmpty());
        }
    }

    @Test
    public void size() throws Exception {
        // test init
        LinkedBag<Integer> linkedBag = new LinkedBag<>();
        assertEquals(0, linkedBag.size());

        // test add
        for(int i = 0; i < 100000; i++){
            linkedBag.add(i);
            assertEquals(i + 1, linkedBag.size());
        }
    }

    @Test
    public void add() throws Exception {
        LinkedBag<Integer> linkedBag = new LinkedBag<>();
        for(int i = 0; i < 100000; i++){
            linkedBag.add(i);
        }
    }

    @Test
    public void iterator() throws Exception {
        LinkedBag<Integer> linkedBag = new LinkedBag<>();
        for(int i = 0; i < 100000; i++){
            linkedBag.add(i);
        }
        Iterator<Integer> iterator = linkedBag.iterator();
        int index = 100000 - 1;
        while(iterator.hasNext()){
            int item = iterator.next();
            assertEquals(index, item);
            index--;
        }
    }

}
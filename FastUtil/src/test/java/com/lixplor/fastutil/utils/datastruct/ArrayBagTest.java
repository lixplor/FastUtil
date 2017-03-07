package com.lixplor.fastutil.utils.datastruct;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created :  2016-10-04
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class ArrayBagTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void isEmpty() throws Exception {
        // condition: init
        ArrayBag<Integer> arrayBag = new ArrayBag<>();
        boolean actual = arrayBag.isEmpty();
        assertTrue(actual);
        // condition: add
        ArrayBag<Integer> clearBag = new ArrayBag<>();
        for(int i = 0; i < 10; i++){
            clearBag.add(i);
        }
        assertFalse(clearBag.isEmpty());
    }

    @Test
    public void size() throws Exception {
        // condition: 0
        ArrayBag<Integer> zeroBag = new ArrayBag<>();
        assertEquals(0, zeroBag.size());

        // condition: has size
        ArrayBag<Integer> arrayBag = new ArrayBag<>();
        for(int i = 0; i < 10; i++){
            arrayBag.add(i);
        }
        int expect = 10;
        int actual = arrayBag.size();
        assertEquals(expect, actual);
    }

    @Test
    public void add() throws Exception {
        ArrayBag<Integer> arrayBag = new ArrayBag<>();
        for(int i = 0; i < 10; i++){
            arrayBag.add(i);
        }
        assertEquals(10, arrayBag.size());
    }

    @Test
    public void iterator() throws Exception {
        ArrayBag<Integer> arrayBag = new ArrayBag<>();
        for(int i = 0; i < 10; i++){
            arrayBag.add(i);
        }
        Iterator<Integer> iterator = arrayBag.iterator();
        assertNotNull(iterator);

        // element
        int index = 0;
        while (iterator.hasNext()){
            int i = iterator.next();
            System.out.println("index=" + index + " i=" + i);
            assertEquals(index, i);
            index++;
        }
    }
}
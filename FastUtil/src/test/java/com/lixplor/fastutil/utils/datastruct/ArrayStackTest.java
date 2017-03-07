package com.lixplor.fastutil.utils.datastruct;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created :  2016-10-04
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class ArrayStackTest {
    @Test
    public void isEmpty() throws Exception {
        // test init
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        assertTrue(arrayStack.isEmpty());

        // test not empty
        for(int i = 0; i < 100000; i++){
            arrayStack.push(i);
        }
        assertFalse(arrayStack.isEmpty());

        // test clear empty
        for(int i = 0; i < 100000; i++){
            arrayStack.pop();
        }
        assertTrue(arrayStack.isEmpty());
    }

    @Test
    public void size() throws Exception {
        // test init
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        assertEquals(0, arrayStack.size());

        // test add
        for(int i = 0; i < 100000; i++){
            arrayStack.push(i);
            assertEquals(i + 1, arrayStack.size());
        }

        // test remove
        for(int i = 100000; i > 0; i--){
            arrayStack.pop();
            assertEquals(i - 1, arrayStack.size());
        }
    }

    @Test
    public void push() throws Exception {
        // test push
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        for(int i = 0; i < 100000; i++){
            arrayStack.push(i);
            // test size should increase after push
            assertEquals(i + 1, arrayStack.size());
        }
        for(int i = 100000; i > 0; i--){
            int item = arrayStack.pop();
            // test items should be same after push
            assertEquals(i - 1, item);
        }
    }

    @Test
    public void pop() throws Exception {
        // test pop
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        for(int i = 0; i < 100000; i++){
            arrayStack.push(i);
        }
        for(int i = 100000; i > 0; i--){
            int item = arrayStack.pop();
            // test pop items should as expected
            assertEquals(i - 1, item);
            // test size should decrease
            assertEquals(i - 1, arrayStack.size());
        }
    }

    @Test
    public void iterator() throws Exception {
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        for(int i = 0; i < 100000; i++){
            arrayStack.push(i);
        }
        Iterator<Integer> iterator = arrayStack.iterator();
        int index = 100000 - 1;
        while(iterator.hasNext()){
            int item = iterator.next();
            // test items
            assertEquals(index, item);
            index--;
        }
    }
}
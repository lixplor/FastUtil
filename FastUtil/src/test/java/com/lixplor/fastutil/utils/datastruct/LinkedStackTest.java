package com.lixplor.fastutil.utils.datastruct;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created :  2016-10-04
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class LinkedStackTest {
    @Test
    public void isEmpty() throws Exception {
        // test init
        LinkedStack<Integer> linkedStack = new LinkedStack<>();
        assertTrue(linkedStack.isEmpty());

        // test not empty
        for(int i = 0; i < 100000; i++){
            linkedStack.push(i);
            assertFalse(linkedStack.isEmpty());
        }

        // test clear empty
        for(int i = 0; i < 100000; i++){
            linkedStack.pop();
            if(i < 100000 - 1){
                assertFalse(linkedStack.isEmpty());
            }else{
                assertTrue(linkedStack.isEmpty());
            }
        }
        assertTrue(linkedStack.isEmpty());
    }

    @Test
    public void size() throws Exception {
        // test init
        LinkedStack<Integer> linkedStack = new LinkedStack<>();
        assertEquals(0, linkedStack.size());

        // test add
        for(int i = 0; i < 100000; i++){
            linkedStack.push(i);
            assertEquals(i + 1, linkedStack.size());
        }

        // test clear empty
        for(int i = 0; i < 100000; i++){
            linkedStack.pop();
            assertEquals(100000 - i - 1, linkedStack.size());
        }
    }

    @Test
    public void push() throws Exception {
        LinkedStack<Integer> linkedStack = new LinkedStack<>();
        for(int i = 0; i < 100000; i++){
            linkedStack.push(i);
        }
        for(int i = 100000; i > 0; i--){
            int item = linkedStack.pop();
            assertEquals(i - 1, item);
        }
    }

    @Test
    public void pop() throws Exception {
        LinkedStack<Integer> linkedStack = new LinkedStack<>();
        for(int i = 0; i < 100000; i++){
            linkedStack.push(i);
        }
        for(int i = 100000; i > 0; i--){
            int item = linkedStack.pop();
            assertEquals(i - 1, item);
        }
    }

    @Test
    public void iterator() throws Exception {
        LinkedStack<Integer> linkedStack = new LinkedStack<>();
        for(int i = 0; i < 100000; i++){
            linkedStack.push(i);
        }
        Iterator<Integer> iterator = linkedStack.iterator();
        int index = 100000 - 1;
        while(iterator.hasNext()){
            int item = iterator.next();
            assertEquals(index, item);
            index--;
        }
    }

}
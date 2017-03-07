package cn.fantasymaker.fmutils.utils.datastruct;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created :  2016-10-04
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class ArrayQueueTest {
    @Test
    public void isEmpty() throws Exception {
        // test init
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        assertTrue(arrayQueue.isEmpty());

        // test not empty
        for(int i = 0; i < 100000; i++){
            arrayQueue.enqueue(i);
        }
        assertFalse(arrayQueue.isEmpty());

        // test clear empty
        for(int i = 0; i < 100000; i++){
            int item = arrayQueue.dequeue();
        }
        assertTrue(arrayQueue.isEmpty());
    }

    @Test
    public void size() throws Exception {
        // test init
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        assertEquals(0, arrayQueue.size());

        // test add
        for(int i = 0; i < 100000; i++){
            arrayQueue.enqueue(i);
        }
        assertEquals(100000, arrayQueue.size());

        // test remove
        for(int i = 0; i < 100000; i++){
            arrayQueue.dequeue();
        }
        assertEquals(0, arrayQueue.size());
    }

    @Test
    public void enqueue() throws Exception {
        // test add
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        for(int i = 0; i < 100000; i++){
            arrayQueue.enqueue(i);
            assertEquals(i + 1, arrayQueue.size());
        }
    }

    @Test
    public void dequeue() throws Exception {
        // test remove
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        for(int i = 0; i < 100000; i++){
            arrayQueue.enqueue(i);
        }
        for(int i = 0; i < 100000; i++){
            int item = arrayQueue.dequeue();
            assertEquals(i, item);
        }
    }

    @Test
    public void iterator() throws Exception {
        // test it
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        for(int i = 0; i < 100000; i++){
            arrayQueue.enqueue(i);
        }
        Iterator<Integer> iterator = arrayQueue.iterator();
        int index = 0;
        while(iterator.hasNext()){
            int item = iterator.next();
            assertEquals(index, item);
            index++;
        }
    }

}
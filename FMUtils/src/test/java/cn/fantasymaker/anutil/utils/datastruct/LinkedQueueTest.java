package cn.fantasymaker.anutil.utils.datastruct;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created :  2016-10-04
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class LinkedQueueTest {
    @Test
    public void isEmpty() throws Exception {
        // test init
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();
        assertTrue(linkedQueue.isEmpty());

        // test not empty
        for(int i = 0; i < 100000; i++){
            linkedQueue.enqueue(i);
            assertFalse(linkedQueue.isEmpty());
        }

        // test clear empty
        for(int i = 0; i < 100000; i++){
            linkedQueue.dequeue();
            if(i < 100000 - 1){
                assertFalse(linkedQueue.isEmpty());
            }else{
                assertTrue(linkedQueue.isEmpty());
            }
        }
        assertTrue(linkedQueue.isEmpty());
    }

    @Test
    public void size() throws Exception {
        // test init
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();
        assertEquals(0, linkedQueue.size());

        // test add
        for(int i = 0; i < 100000; i++){
            linkedQueue.enqueue(i);
            assertEquals(i + 1, linkedQueue.size());
        }

        // test remove
        for(int i = 100000; i > 0; i--){
            linkedQueue.dequeue();
            assertEquals(i - 1, linkedQueue.size());
        }
    }

    @Test
    public void enqueue() throws Exception {
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();
        for(int i = 0; i < 100000; i++){
            linkedQueue.enqueue(i);
        }
        for(int i = 0; i < 100000; i++){
            int item = linkedQueue.dequeue();
            assertEquals(i, item);
        }
    }

    @Test
    public void dequeue() throws Exception {
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();
        for(int i = 0; i < 100000; i++){
            linkedQueue.enqueue(i);
        }
        for(int i = 0; i < 100000; i++){
            int item = linkedQueue.dequeue();
            assertEquals(i, item);
        }
    }

    @Test
    public void iterator() throws Exception {
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();
        for(int i = 0; i < 100000; i++){
            linkedQueue.enqueue(i);
        }
        Iterator<Integer> iterator = linkedQueue.iterator();
        int index = 0;
        while(iterator.hasNext()){
            int item = iterator.next();
            assertEquals(index, item);
            index++;
        }
    }

}
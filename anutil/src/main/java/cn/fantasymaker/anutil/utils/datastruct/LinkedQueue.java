/*
 *     Copyright © 2016 Fantasymaker
 *
 *     Permission is hereby granted, free of charge, to any person obtaining a copy
 *     of this software and associated documentation files (the "Software"), to deal
 *     in the Software without restriction, including without limitation the rights
 *     to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *     copies of the Software, and to permit persons to whom the Software is
 *     furnished to do so, subject to the following conditions:
 *
 *     The above copyright notice and this permission notice shall be included in all
 *     copies or substantial portions of the Software.
 *
 *     THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *     IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *     FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *     AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *     LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *     OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *     SOFTWARE.
 */

package cn.fantasymaker.anutil.utils.datastruct;

import java.util.Iterator;

/**
 * Created :  2016-10-04
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class LinkedQueue<Item> implements Iterable<Item> {

    private Node mFirstNode;
    private Node mLastNode;
    private int mSize;

    /**
     * Return is queue has no element
     *
     * @return true if is empty; false otherwise
     */
    public boolean isEmpty() {
        return mFirstNode == null;
        // or return mSize == 0;
    }

    /**
     * Return size of queue
     *
     * @return size of queue
     */
    public int size() {
        return mSize;
    }

    /**
     * Add an item into queue's tail
     *
     * @param item item to be added
     */
    public void enqueue(Item item) {
        // create temp node to store old last node
        Node oldLastNode = mLastNode;
        // create new node to be last node
        mLastNode = new Node();
        // store item in new last node
        mLastNode.mItem = item;
        // make new last node's next to be null as it's the last
        mLastNode.mNext = null;
        if (isEmpty()) {
            // if queue is empty, then first is the last
            mFirstNode = mLastNode;
        } else {
            // if queue is not empty, then make old last node's next to be new last node
            oldLastNode.mNext = mLastNode;
        }
        // increase size
        mSize++;
    }

    /**
     * Remove first item from queue's head
     *
     * @return first item
     */
    public Item dequeue() {
        // get item from first node
        Item item = mFirstNode.mItem;
        // make second node to be first node
        mFirstNode = mFirstNode.mNext;
        if (isEmpty()) {
            // if queue is empty, make last node as null
            mLastNode = null;
        }
        // decrease size
        mSize--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkQueueIterator();
    }

    private class LinkQueueIterator implements Iterator<Item> {

        private Node mCurrentNode = mFirstNode;

        @Override
        public boolean hasNext() {
            return mCurrentNode != null;
        }

        @Override
        public Item next() {
            // get item in current node
            Item item = mCurrentNode.mItem;
            // make next node to be current node
            mCurrentNode = mCurrentNode.mNext;
            // return item in current node
            return item;
        }

        @Override
        public void remove() {

        }
    }

    /**
     * Node element in linked list
     */
    private class Node {
        Item mItem;
        Node mNext;
    }
}

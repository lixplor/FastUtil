/*
 *  Copyright 2016 Lixplor
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package cn.fantasymaker.fmutils.utils.datastruct;

import java.util.Iterator;

/**
 * Created :  2016-10-04
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
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

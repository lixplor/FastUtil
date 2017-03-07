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
public class LinkedStack<Item> implements Iterable<Item> {

    private Node mFirstNode;
    private int mSize;

    /**
     * Return if stack has no element
     *
     * @return true if stack is empty; otherwise false
     */
    public boolean isEmpty() {
        return mFirstNode == null;
        // or return mSize == 0;
    }

    /**
     * Get size of stack
     *
     * @return size of stack
     */
    public int size() {
        return mSize;
    }

    /**
     * Add item to top of the stack
     *
     * @param item item to be added
     */
    public void push(Item item) {
        // create temp node to store old first node
        Node oldFirstNode = mFirstNode;
        // create a new node to be first node
        mFirstNode = new Node();
        // make first node to point new item
        mFirstNode.mItem = item;
        // make old first node to be the second node
        mFirstNode.mNext = oldFirstNode;
        // increase size
        mSize++;
    }

    /**
     * Remove item from top of the stack
     *
     * @return top item
     */
    public Item pop() {
        // Get item from top of stack
        Item item = mFirstNode.mItem;
        // make the second node to be the first node
        mFirstNode = mFirstNode.mNext;
        // decrease size
        mSize--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkStackIterator();
    }

    private class LinkStackIterator implements Iterator<Item> {

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

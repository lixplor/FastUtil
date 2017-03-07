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
public class LinkedBag<Item> implements Iterable<Item> {

    private Node mFirstNode;
    private int mSize;

    /**
     * Return if the bag has no element
     *
     * @return true if empty; false otherwise
     */
    public boolean isEmpty() {
        return mSize == 0;
    }

    /**
     * Return size of bag
     *
     * @return size of bag
     */
    public int size() {
        return mSize;
    }

    /**
     * Add item into bag
     *
     * @param item item to be added
     */
    public void add(Item item) {
        Node oldFirstNode = mFirstNode;
        mFirstNode = new Node();
        mFirstNode.mItem = item;
        mFirstNode.mNext = oldFirstNode;
        mSize++;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkBagIterator();
    }

    private class LinkBagIterator implements Iterator<Item> {

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

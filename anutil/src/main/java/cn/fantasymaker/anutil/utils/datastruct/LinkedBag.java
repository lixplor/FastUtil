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

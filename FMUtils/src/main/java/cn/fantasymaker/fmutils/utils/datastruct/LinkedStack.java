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

package cn.fantasymaker.fmutils.utils.datastruct;

import java.util.Iterator;

/**
 * Created :  2016-10-04
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
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

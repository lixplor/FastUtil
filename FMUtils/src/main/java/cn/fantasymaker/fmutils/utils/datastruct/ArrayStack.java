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
public class ArrayStack<Item> implements Iterable<Item> {

    private Item[] mArr = (Item[]) new Object[1];
    private int mSize = 0;

    /**
     * Resize current array to a new size
     *
     * @param newSize new size
     */
    private void resize(int newSize) {
        Item[] tempArr = (Item[]) new Object[newSize];
        System.arraycopy(mArr, 0, tempArr, 0, mSize);
        mArr = tempArr;
    }

    /**
     * Return if stack is empty.
     *
     * @return True if stack has no element. False otherwise.
     */
    public boolean isEmpty() {
        return mSize == 0;
    }

    /**
     * Return size of stack
     *
     * @return size of stack
     */
    public int size() {
        return mSize;
    }

    /**
     * Add an item to top of the stack
     *
     * @param item item to be added
     */
    public void push(Item item) {
        // extend array if necessary
        if (mSize == mArr.length) {
            resize(2 * mArr.length);
        }
        // add item
        mArr[mSize++] = item;
    }

    /**
     * Remove the item from top of the stack
     */
    public Item pop() {
        // get top item
        Item item = mArr[--mSize];
        // cancel reference from array
        mArr[mSize] = null;
        // shorten array size if necessary
        if (mSize > 0 && mSize == mArr.length / 4) {
            resize(mArr.length / 2);
        }
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayStackIterator();
    }

    private class ArrayStackIterator implements Iterator<Item> {

        private int mIndex = mSize;

        @Override
        public boolean hasNext() {
            return mIndex > 0;
        }

        @Override
        public Item next() {
            return mArr[--mIndex];
        }

        @Override
        public void remove() {

        }
    }
}

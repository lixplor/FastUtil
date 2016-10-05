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
public class ArrayQueue<Item> implements Iterable<Item> {

    private Item[] mArr = (Item[]) new Object[1];
    private int mSize = 0;

    private void resize(int newSize) {
        Item[] tempArr = (Item[]) new Object[newSize];
        System.arraycopy(mArr, 0, tempArr, 0, mSize);
        mArr = tempArr;
    }

    /**
     * Return if the queue has no element
     *
     * @return true if is empty; false otherwise
     */
    public boolean isEmpty() {
        return mSize == 0;
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
     * Add item to queue tail
     *
     * @param item item to be added
     */
    public void enqueue(Item item) {
        if (mSize == mArr.length) {
            resize(2 * mArr.length);
        }
        mArr[mSize++] = item;
    }

    /**
     * Remove item in queue's head
     *
     * @return item removed
     */
    public Item dequeue() {
        if(!isEmpty()){
            // get head item
            Item item = mArr[0];
            mSize--;
            // move items position
            Item[] tempArr = (Item[]) new Object[mArr.length - 1];
            System.arraycopy(mArr, 1, tempArr, 0, tempArr.length);
            mArr = tempArr;
            // shorten array size if necessary
//            if (mSize > 0 && mSize == mArr.length / 4) {
//                resize(mArr.length / 2);
//            }
            return item;
        }else{
            return null;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayQueueIterator();
    }

    private class ArrayQueueIterator implements Iterator<Item> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Item next() {
            return null;
        }

        @Override
        public void remove() {

        }
    }
}

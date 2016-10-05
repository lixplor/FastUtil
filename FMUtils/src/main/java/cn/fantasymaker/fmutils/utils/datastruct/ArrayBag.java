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
public class ArrayBag<Item> implements Iterable<Item> {

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
     * Return if bag has no element
     *
     * @return true if size is empty; false otherwise
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

    public void add(Item item) {
        if (mSize == mArr.length) {
            resize(2 * mArr.length);
        }
        mArr[mSize++] = item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayBagIterator();
    }

    private class ArrayBagIterator implements Iterator<Item> {

        private int mIndex = 0;

        @Override
        public boolean hasNext() {
            return mIndex < mSize;
        }

        @Override
        public Item next() {
            return mArr[mIndex++];
        }

        @Override
        public void remove() {

        }
    }
}

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

package com.lixplor.fastutil.utils.datastruct;

import java.util.Iterator;

/**
 * Created :  2016-10-04
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
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

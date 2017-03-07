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

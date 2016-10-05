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

package cn.fantasymaker.anutil.utils.develop;

/**
 * Created :  2016-08-08
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class AlgorithmUtil {

    /*
    todo
    各种算法
    排序
    查找
    去重
    合并
    插入
    删除
    修改 replace(int start, int end)
     */

    /*排序部分 small -> large */

    public static int[] bucketSort(int[] arr) {
        //int bucketSize = arr.max() // TODO: 16/8/8
        //int[] bucket = new int[]
        return new int[]{1, 1};
    }

    /**
     * Bubble sort | O(N^2)
     * <p/>
     * x x x
     * x x
     * x
     *
     * @param arr array to be sorted
     * @return sorted array
     */
    public static int[] bubbleSort(int[] arr) {
        int length = arr.length;
        int temp;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * Quick sort | worst: O(N^2), avg: O(N * logN)
     *
     * @param arr
     * @param low
     * @param high
     * @return
     */
    public static int[] quickSort(int[] arr, int low, int high) {
        int lowIndex = low;
        int highIndex = high;
        int key = arr[lowIndex];
        while (lowIndex < highIndex) {
            while (arr[highIndex] >= key && highIndex > lowIndex) {
                highIndex--;
            }
            if (lowIndex < highIndex) {
                arr[lowIndex] = arr[highIndex];
                lowIndex++;
            }
            while (arr[lowIndex] <= key && lowIndex < highIndex) {
                lowIndex++;
            }
            if (lowIndex < highIndex) {
                arr[highIndex] = arr[lowIndex];
                highIndex--;
            }
        }
        arr[lowIndex] = key;
        if (lowIndex > low) {
            quickSort(arr, low, lowIndex - 1);
        }
        if (lowIndex < high) {
            quickSort(arr, lowIndex + 1, high);
        }
        return arr;
    }


    public static int[] reverse(int[] arr) {
        int length = arr.length;
        int[] newArr = new int[length];
        for (int i = 0; i < length; i++) {
            newArr[i] = arr[length - 1 - i];
        }
        return newArr;
    }

    /*查找部分*/

    public static int max(int[] arr) {
        return 0;
    }

    public static int min(int[] arr) {
        return 0;
    }

    public static int indexOf(int[] arr) {
        return 0;
    }


    //============= method run time counter

    public interface OnCountMethodTimeCallback{
        void onCountBegin();
    }

    public static OnCountMethodTimeCallback sOnCountMethodTimeCallback;

    public static void setOnCountMethodTimeCallback(OnCountMethodTimeCallback onCountMethodTimeCallback){
        sOnCountMethodTimeCallback = onCountMethodTimeCallback;
    }

    public static long time(OnCountMethodTimeCallback onCountMethodTimeCallback){
        long begin = System.currentTimeMillis();
        if(onCountMethodTimeCallback != null){
            onCountMethodTimeCallback.onCountBegin();
        }
        long end = System.currentTimeMillis();
        long duration = end - begin;
        System.out.println(duration);
        return duration;
    }
}

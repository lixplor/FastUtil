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

package cn.fantasymaker.fmutils.utils.calculate;

import android.graphics.Rect;

/**
 * Calculating actual value as per percent between two values
 *
 * Created :  2016-07-25
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class ProgressUtil {

    private ProgressUtil() throws IllegalAccessException{
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    /**
     * Calculate progress value
     * @param percent current percent value
     * @param startValue start value of progress
     * @param endValue end value of progress
     * @return actual progress value
     */
    public static int getInt(float percent, int startValue, int endValue) {
        return (int) (startValue + percent * (endValue - startValue));
    }

    /**
     * Calculate progress value
     * @param percent current percent value
     * @param startValue start value of progress
     * @param endValue end value of progress
     * @return actual progress value
     */
    public static Float getFloat(float percent, Number startValue, Number endValue) {
        float startFloat = startValue.floatValue();
        return startFloat + percent * (endValue.floatValue() - startFloat);
    }

    /**
     * Calculate progress value
     * @param percent current percent value
     * @param startValue start value of progress
     * @param endValue end value of progress
     * @return actual progress value
     */
    public static Object getArgb(float percent, Object startValue, Object endValue) {
        int startInt = (Integer) startValue;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;

        int endInt = (Integer) endValue;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;

        return (int) ((startA + (int) (percent * (endA - startA))) << 24)
                | (int) ((startR + (int) (percent * (endR - startR))) << 16)
                | (int) ((startG + (int) (percent * (endG - startG))) << 8)
                | (int) ((startB + (int) (percent * (endB - startB))));
    }

    /**
     * Calculate progress value
     * @param percent current percent value
     * @param startValue start value of progress
     * @param endValue end value of progress
     * @return actual progress value
     */
    public static Rect getRect(float percent, Rect startValue, Rect endValue) {
        return new Rect(startValue.left + (int)((endValue.left - startValue.left) * percent),
                startValue.top + (int)((endValue.top - startValue.top) * percent),
                startValue.right + (int)((endValue.right - startValue.right) * percent),
                startValue.bottom + (int)((endValue.bottom - startValue.bottom) * percent));
    }
}

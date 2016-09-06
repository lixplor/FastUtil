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

package cn.fantasymaker.anutil.utils.convert;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created :  2016-07-25
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class DisplayUtil {

    private DisplayUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    /**
     * Calculate
     *
     * @param context context
     * @param value   value
     * @param unit    unit of display
     * @return float value
     */
    private static float getFloatPointValue(Context context, float value, int unit) {
        return TypedValue.applyDimension(unit, value, context.getResources().getDisplayMetrics());
    }

    /**
     * dp to px
     *
     * @param context context
     * @param dp      dp
     * @return px
     */
    public static float dpToPx(Context context, float dp) {
        return getFloatPointValue(context, dp, TypedValue.COMPLEX_UNIT_DIP);
    }

    /**
     * dp to px
     *
     * @param context context
     * @param dp      dp
     * @return px
     */
    public static int dpToPx(Context context, int dp) {
        return (int) (dpToPx(context, dp * 1f) + 0.5f);
    }

    /**
     * px to dp
     *
     * @param context context
     * @param px      px
     * @return dp
     */
    public static float pxToDp(Context context, float px) {
        return (px / context.getResources().getDisplayMetrics().density);
    }

    /**
     * sp to px
     *
     * @param context context
     * @param sp      sp
     * @return px
     */
    public static float spToPx(Context context, float sp) {
        return getFloatPointValue(context, sp, TypedValue.COMPLEX_UNIT_SP);
    }

    /**
     * sp to px
     *
     * @param context context
     * @param sp      sp
     * @return px
     */
    public static int spToPx(Context context, int sp) {
        return (int) (spToPx(context, sp * 1f) + 0.5f);
    }

    /**
     * px to sp
     *
     * @param context context
     * @param px      px
     * @return sp
     */
    public static float pxToSp(Context context, float px) {
        return (px / context.getResources().getDisplayMetrics().scaledDensity);
    }

    /**
     * dp to sp
     *
     * @param context context
     * @param dp      dp
     * @return sp
     */
    public static float dpToSp(Context context, float dp) {
        return pxToSp(context, dpToPx(context, dp));
    }

    /**
     * sp to dp
     *
     * @param context context
     * @param sp      sp
     * @return dp
     */
    public static float spToDp(Context context, float sp) {
        return pxToDp(context, spToPx(context, sp));
    }
}
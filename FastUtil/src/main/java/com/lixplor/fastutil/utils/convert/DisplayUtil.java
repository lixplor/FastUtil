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

package com.lixplor.fastutil.utils.convert;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created :  2016-07-25
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
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
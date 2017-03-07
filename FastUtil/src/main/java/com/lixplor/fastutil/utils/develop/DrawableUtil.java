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

package com.lixplor.fastutil.utils.develop;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

/**
 * Created :  2016-07-25
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class DrawableUtil {

    private DrawableUtil() throws IllegalAccessException{
        throw new IllegalAccessException("Instatiation is not allowed! Use static methods only!");
    }

    /**
     * Create a rectangel shape drawable
     * @param argb color in argb
     * @param radius corner radius
     * @return a rectangel shape
     */
    public static GradientDrawable createRectangleShape(int argb, float radius){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(radius);
        drawable.setColor(argb);
        return drawable;
    }

    /**
     * Create a rectangel shape drawable
     * @param argb color in argb
     * @param radiuses 4 corner radius, left-top, right-top, right-bottom, left-top
     * @return a rectangel shape
     */
    public static GradientDrawable createRectangleShape(int argb, float[] radiuses){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(argb);
        drawable.setCornerRadii(radiuses);
        return drawable;
    }

    /**
     * Create a selector(StateListDrawable) drawable
     * @param pressed drawable in pressed status
     * @param normal drawable in normal status
     * @return a selector(StateListDrawable) drawable
     */
    public static StateListDrawable createSelector(Drawable pressed, Drawable normal){
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_pressed}, pressed);//设置按下的图片
        drawable.addState(new int[]{}, normal);//设置默认的图片
        return drawable;
    }
}

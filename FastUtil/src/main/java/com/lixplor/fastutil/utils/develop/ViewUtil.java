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

import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created :  2016-08-12
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class ViewUtil {

    /*
    todo
    显示隐藏
    计算宽高
    获取属性

     */

    private ViewUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    public static int[] getWidthHeight(final View view) {
        final int[] widthHeight = new int[2];
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            boolean hasMeasured = false;

            @Override
            public boolean onPreDraw() {
                if (!hasMeasured) {
                    int width = view.getMeasuredWidth();
                    int height = view.getMeasuredHeight();
                    widthHeight[0] = width;
                    widthHeight[1] = height;
                    hasMeasured = true;
                }
                return true;
            }
        });
        // FIXME: 16/8/12 在回调中才能获取, 子线程, 不能直接返回
        return widthHeight;
    }
}

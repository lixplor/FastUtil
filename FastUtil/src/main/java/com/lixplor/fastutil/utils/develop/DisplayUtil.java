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

import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;

import com.lixplor.fastutil.utils.FastUtil;

/**
 * Created :  2016-07-25
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class DisplayUtil {

    private static Context sContext = FastUtil.getContext();
    private static WindowManager sWindowManager = (WindowManager) sContext.getSystemService(Context.WINDOW_SERVICE);
    private static DisplayMetrics sOutMetrics = new DisplayMetrics();

    private DisplayUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    /**
     * Get screen width in pixel
     *
     * @return screen width in pixel
     */
    public static int getScreenWidth() {
        sWindowManager.getDefaultDisplay().getMetrics(sOutMetrics);
        return sOutMetrics.widthPixels;
    }

    /**
     * Get screen height in pixel
     *
     * @return screen height in pixel
     */
    public static int getScreenHeight() {
        sWindowManager.getDefaultDisplay().getMetrics(sOutMetrics);
        return sOutMetrics.heightPixels;
    }

    /**
     * Get height of status bar
     *
     * @param view any view
     * @return status bar height
     */
    public static int getStatusBarHeight(View view) {
        if (view == null) {
            return 0;
        }
        Rect frame = new Rect();
        view.getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }

    public static int getScreenRotation() {
        int rotation = sWindowManager.getDefaultDisplay().getRotation();
        switch (rotation) {
            default:
            case Surface.ROTATION_0:
                return 0;
            case Surface.ROTATION_90:
                return 90;
            case Surface.ROTATION_180:
                return 180;
            case Surface.ROTATION_270:
                return 270;
        }
    }

    public static float getRefreshRate() {
        return sWindowManager.getDefaultDisplay().getRefreshRate();
    }


    public static boolean isPortrait() {
        return getScreenHeight() >= getScreenWidth();
    }

    public static boolean isLandscape() {
        return getScreenHeight() < getScreenWidth();
    }

    public static int getActionBarHeight() {
        TypedValue tv = new TypedValue();
        return sContext.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true) ?
                TypedValue.complexToDimensionPixelSize(tv.data, new DisplayMetrics()) :
                0;
    }

    public static int getNavigationBarHeight() {
        int resourceId = ResourceUtil.getIdentifier("navigation_bar_height", "dimen", "android");
        return resourceId > 0 ?
                ResourceUtil.getDimensionPixelSize(resourceId) :
                0;
    }
}

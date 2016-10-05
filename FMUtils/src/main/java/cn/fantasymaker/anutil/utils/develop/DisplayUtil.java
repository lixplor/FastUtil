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

import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;

import cn.fantasymaker.anutil.utils.FMUtils;

/**
 * Created :  2016-07-25
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class DisplayUtil {

    private static Context sContext = FMUtils.getContext();
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

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

package cn.fantasymaker.fmutils.utils.develop;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.DimenRes;

import cn.fantasymaker.fmutils.utils.FMUtils;

/**
 * Created :  2016-07-28
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class ResourceUtil {
    
    private static Context sContext = FMUtils.getContext();

    private ResourceUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    public static Resources getResources() {
        return sContext.getResources();
    }

    public static Configuration getConfiguration(){
        return getResources().getConfiguration();
    }

    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    public static String getString(int resId, Object formatArgs) {
        return getResources().getString(resId, formatArgs);
    }

    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    public static int getInt(int resId) {
        return getResources().getInteger(resId);
    }

    public static int[] getIntArray(int resId) {
        return getResources().getIntArray(resId);
    }

    public static boolean getBoolean(int resId) {
        return getResources().getBoolean(resId);
    }

    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    public static float getDimension(int resId) {
        return getResources().getDimension(resId);
    }

    public static Drawable getDrawable(int resId) {
        return getResources().getDrawable(resId);
    }

    public static int getIdentifier(String name, String defType, String defPackage) {
        return getResources().getIdentifier(name, defType, defPackage);
    }

    public static int getDimensionPixelSize(@DimenRes int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    public static String getSystemLanguage() {
        return getConfiguration().locale.getLanguage();
    }

    public static String getCountry() {
        return getConfiguration().locale.getCountry();
    }


}

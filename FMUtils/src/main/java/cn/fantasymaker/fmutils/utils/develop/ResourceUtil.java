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

package cn.fantasymaker.fmutils.utils.develop;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.DimenRes;

import cn.fantasymaker.fmutils.utils.FMUtils;

/**
 * Created :  2016-07-28
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
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

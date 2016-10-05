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

package cn.fantasymaker.anutil.utils.control;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

import cn.fantasymaker.anutil.utils.FMUtils;

/**
 * Created :  2016-08-11
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class SettingUtil {

    /*
    todo
    各种设置
    x读取
    ?写入
     */

    private static Context sContext = FMUtils.getContext();
    private static ContentResolver sContentResolver = sContext.getContentResolver();

    private SettingUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    /**
     * Get setting string value
     *
     * @param settingName constants in Settings.Global
     * @return string value
     */
    public static String getString(String settingName) {
        return android.provider.Settings.System.getString(sContentResolver, settingName);
    }

    /**
     * Get setting float value
     *
     * @param settingName constants in Settings.Global
     * @return string value
     */
    public static float getFloat(String settingName, float defaultValue) {
        return android.provider.Settings.System.getFloat(sContentResolver, settingName, defaultValue);
    }

    /**
     * Get setting int value
     *
     * @param settingName constants in Settings.Global
     * @return string value
     */
    public static int getInt(String settingName, int defaultValue) {
        return android.provider.Settings.System.getInt(sContentResolver, settingName, defaultValue);
    }

    /**
     * Get setting long value
     *
     * @param settingName constants in Settings.Global
     * @return string value
     */
    public static long getLong(String settingName, long defaultValue) {
        return android.provider.Settings.System.getLong(sContentResolver, settingName, defaultValue);
    }

    private static void notifyContentResolverChange(String settingName) {
        Uri uri = android.provider.Settings.System.getUriFor(settingName);
        sContentResolver.notifyChange(uri, null);
    }

    /**
     * Set string value to a setting
     *
     * @param settingName constants in Settings.Global
     * @param value       value
     */
    public static void putString(String settingName, String value) {
        android.provider.Settings.System.putString(sContentResolver, settingName, value);
        notifyContentResolverChange(settingName);
    }

    /**
     * Set float value to a setting
     *
     * @param settingName constants in Settings.Global
     * @param value       value
     */
    public static void putFloat(String settingName, float value) {
        android.provider.Settings.System.putFloat(sContentResolver, settingName, value);
        notifyContentResolverChange(settingName);
    }

    /**
     * Set int value to a setting
     *
     * @param settingName constants in Settings.Global
     * @param value       value
     */
    public static void putInt(String settingName, int value) {
        android.provider.Settings.System.putInt(sContentResolver, settingName, value);
        notifyContentResolverChange(settingName);
    }

    /**
     * Set long value to a setting
     *
     * @param settingName constants in Settings.Global
     * @param value       value
     */
    public static void putLong(String settingName, long value) {
        android.provider.Settings.System.putLong(sContentResolver, settingName, value);
        notifyContentResolverChange(settingName);
    }
}

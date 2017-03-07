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

package cn.fantasymaker.fmutils.utils.control;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

import cn.fantasymaker.fmutils.utils.FMUtils;

/**
 * Created :  2016-08-11
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
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

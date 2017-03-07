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

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import android.view.WindowManager;

/**
 * Created :  2016-08-11
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class ScreenUtil {

    /*
    todo
    锁屏, 解锁
    屏幕角度
    屏幕刷新率
    屏幕色温?
    屏幕亮度
    屏幕常亮
     */






    /**
     * Toggle auto brightness
     *
     * @param context context
     * @param enable  true if enable; Otherwise false
     */
    public static void enableAutoBrightness(Context context, boolean enable) {
        int mode = enable ? Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC : Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL;
        Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, mode);
    }

    /**
     * Return if auto brightness
     *
     * @param context context
     * @return true if auto; Otherwise false
     */
    public static boolean isAutoBrightness(Context context) {
        boolean isAutoAdjustBright = false;
        try {
            int currentMode = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE);
            isAutoAdjustBright = (currentMode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return isAutoAdjustBright;
    }

    /**
     * Get brightness
     *
     * @param context context
     * @return brightness value
     */
    public static int getBrightness(Context context) {
        int brightnessValue = 0;
        try {
            brightnessValue = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return brightnessValue;
    }

    /**
     * Get brightness
     * @param activity activity
     * @return value of brightness; Negtive value means use system perefered setting (not programmly set)
     */
    public static int getBrightness(Activity activity) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        return (int) (lp.screenBrightness * 255f);
    }

    /**
     * Get brightness percent
     * @param activity activity
     * @return percent of brightness (0~1); Negtive value means use system perefered setting (not programmly set)
     */
    public static float getBrightnessPercent(Activity activity) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        return lp.screenBrightness;
    }

    /**
     * Get brightness in percent
     *
     * @param context context
     * @return brightness value
     */
    public static double getBrightnessPercent(Context context) {
        double percent = 0.00;
        try {
            int brightnessValue = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
            percent = brightnessValue * 1.00 / 255.00;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return percent;
    }

    /**
     * Set brightness
     *
     * @param activity activity
     * @param persent  percent of brightness (0~1)
     */
    public static void setBrightness(Activity activity, float persent) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.screenBrightness = persent;
        activity.getWindow().setAttributes(lp);
    }

    /**
     * Set brightness
     *
     * @param activity activity
     * @param value  value of brightness (0~255)
     */
    public static void setBrightness(Activity activity, int value) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.screenBrightness = value * 1f / 255f;
        activity.getWindow().setAttributes(lp);
    }

    /**
     * Persistent brightness
     *
     * @param context    context
     * @param brightness
     */
    public static void saveBrightness(Context context, int brightness) {
        Uri uri = Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS);
        ContentResolver resolver = context.getContentResolver();
        Settings.System.putInt(resolver, Settings.System.SCREEN_BRIGHTNESS, brightness);
        resolver.notifyChange(uri, null);
    }
}

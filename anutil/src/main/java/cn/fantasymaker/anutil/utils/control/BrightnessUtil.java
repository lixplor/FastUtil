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

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import android.view.WindowManager;

import cn.fantasymaker.anutil.utils.Anutil;

/**
 * Created :  2016-08-10
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class BrightnessUtil {

    /*
     todo
     永久设置亮度
     */

    private static Context sContext = Anutil.getContext();

    private BrightnessUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    /**
     * Toggle auto brightness
     *
     * @param enable true if enable; Otherwise false
     */
    public static void enableAutoBrightness(boolean enable) {
        int mode = enable ? Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC : Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL;
        Settings.System.putInt(sContext.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, mode);
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
     *
     * @param activity activity
     * @return value of brightness; Negtive value means use system perefered setting (not programmly set)
     */
    public static int getBrightness(Activity activity) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        return (int) (lp.screenBrightness * 255f);
    }

    /**
     * Get brightness percent
     *
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
     * @param value    value of brightness (0~255)
     */
    public static void setBrightness(Activity activity, int value) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.screenBrightness = value * 1f / 255f;
        activity.getWindow().setAttributes(lp);
    }

    /**
     * fixme seems not work
     * Persistent brightness
     *
     * @param brightness
     */
    public static void saveBrightness(int brightness) {
        Uri uri = Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS);
        ContentResolver resolver = sContext.getContentResolver();
        Settings.System.putInt(resolver, Settings.System.SCREEN_BRIGHTNESS, brightness);
        resolver.notifyChange(uri, null);
    }
}

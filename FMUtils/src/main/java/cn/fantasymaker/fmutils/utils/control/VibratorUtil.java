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

package cn.fantasymaker.fmutils.utils.control;

import android.content.Context;
import android.os.Vibrator;

import cn.fantasymaker.fmutils.utils.FMUtils;

/**
 * Created :  2016-08-11
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class VibratorUtil {

    /*
    todo
    x震动
    x节奏震动
     */

    /*
    Need permission:
    <uses-permission android:name="android.permission.VIBRATE"/>
     */

    private static Context sContext = FMUtils.getContext();
    private static Vibrator sVibrator = (Vibrator) sContext.getSystemService(Context.VIBRATOR_SERVICE);

    private VibratorUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    /**
     * Vibrate for specific milli seconds
     *
     * @param millisec milli seconds to vibrate
     */
    public static void vibrate(long millisec) {
        sVibrator.vibrate(millisec);
    }

    /**
     * Vibrate for specific milli seconds
     *
     * @param pattern     array of milli seconds for stop, start.
     * @param repeatIndex index of pattern to repeat; Or -1 to disable repeat
     */
    public static void vibrate(long[] pattern, int repeatIndex) {
        sVibrator.vibrate(pattern, repeatIndex);
    }

    /**
     * Vibrate for specific milli seconds
     */
    public static void stopVibrate() {
        sVibrator.cancel();
    }

    /**
     * Vibrate for specific milli seconds
     *
     * @return true if has vibrator; Otherwise false
     */
    public static boolean hasVibrator() {
        return sVibrator.hasVibrator();
    }
}

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

package com.lixplor.fastutil.utils.control;

import android.content.Context;
import android.os.Vibrator;

import com.lixplor.fastutil.utils.FastUtil;

/**
 * Created :  2016-08-11
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
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

    private static Context sContext = FastUtil.getContext();
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

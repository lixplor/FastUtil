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
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import cn.fantasymaker.fmutils.utils.FMUtils;

/**
 * Created :  2016-08-03
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class SensorUtil {

    private static Context sContext = FMUtils.getContext();
    private static SensorManager sSensorManager = (SensorManager) sContext.getSystemService(Context.SENSOR_SERVICE);

    private SensorUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    public static Sensor getSensor(int type) {
        return sSensorManager.getDefaultSensor(type);
    }

    public static boolean hasSensor(int type) {
        return sSensorManager.getDefaultSensor(type) == null;
    }

    public static void registerSensorListener(Sensor sensor, int samplingPeriodUs, SensorEventListener sensorEventListener) {
        sSensorManager.registerListener(sensorEventListener, sensor, samplingPeriodUs);
    }

    public static void unregisterSensorListener(Sensor sensor, SensorEventListener sensorEventListener) {
        sSensorManager.unregisterListener(sensorEventListener, sensor);
    }
}

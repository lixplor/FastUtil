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
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.lixplor.fastutil.utils.FastUtil;

/**
 * Created :  2016-08-03
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class SensorUtil {

    private static Context sContext = FastUtil.getContext();
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

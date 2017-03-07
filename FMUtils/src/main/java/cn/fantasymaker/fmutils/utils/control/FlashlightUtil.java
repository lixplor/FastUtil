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

import android.hardware.Camera;

/**
 * Created :  2016-08-11
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class FlashlightUtil {

    /*
    TODO
    x打开闪光灯
    x关闭闪光灯
    节奏闪烁?
     */

    /*
    Needs permission:
    <uses-permission android:name="android.permission.CAMERA"/>
    <uses-feature android:name="android.hardware.camera"/>
    <uses-feature android:name="android.hardware.camera.autofocus"/>
     */

    private static Camera sCamera;

    private FlashlightUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    /**
     * Turn on/off flash light<br/>
     *
     *
     * @param enable true if turns on; Otherwise false
     */
    public static void enableFlashLight(boolean enable) {
        if (sCamera == null) {
            sCamera = Camera.open();
        }
        Camera.Parameters parameters = sCamera.getParameters();
        if (enable) {
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            sCamera.setParameters(parameters);
        } else {
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            sCamera.setParameters(parameters);
        }
    }

    /**
     * Release resource when you don't use flashlight
     */
    public static void release() {
        if (sCamera != null) {
            sCamera.release();
            sCamera = null;
        }
    }
}

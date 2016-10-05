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

import android.hardware.Camera;

/**
 * Created :  2016-08-11
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
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

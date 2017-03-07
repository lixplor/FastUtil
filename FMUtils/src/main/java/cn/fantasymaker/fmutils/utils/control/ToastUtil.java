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

import android.content.Context;
import android.widget.Toast;

import cn.fantasymaker.fmutils.utils.FMUtils;

/**
 * Toast util
 * <p/>
 * Created :  2016-07-25
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class ToastUtil {

    private static Context sContext = FMUtils.getContext();
    private static Toast sToast;

    private ToastUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    /**
     * show a toast in a short time
     *
     * @param msg the message to be displayed
     */
    public static void toastShort(String msg) {
        if (sToast == null) {
            sToast = Toast.makeText(sContext, "", Toast.LENGTH_SHORT);
            sToast.show();
        }
        sToast.setText(msg);
    }

    /**
     * show a toast in a long time
     *
     * @param msg the message to be displayed
     */
    public static void toastLong(String msg) {
        if (sToast == null) {
            sToast = Toast.makeText(sContext, "", Toast.LENGTH_LONG);
            sToast.show();
        }
        sToast.setText(msg);
    }
}
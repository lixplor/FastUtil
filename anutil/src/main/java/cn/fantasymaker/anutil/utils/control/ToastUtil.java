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

import android.content.Context;
import android.widget.Toast;

import cn.fantasymaker.anutil.utils.Anutil;

/**
 * Toast util
 * <p/>
 * Created :  2016-07-25
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class ToastUtil {

    private static Context sContext = Anutil.getContext();
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
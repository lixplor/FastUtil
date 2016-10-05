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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import cn.fantasymaker.fmutils.utils.FMUtils;
import cn.fantasymaker.fmutils.utils.builders.IntentUtil;

/**
 * Created :  2016-08-11
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class TelUtil {

    /*
    todo
    x拨打电话: 显示, 直接
    x获取当前电话状态: 闲置, 接听中, 响铃
    x获取来电信息
    -获取归属地-
    接听电话
    挂断电话
     */

    private static Context sContext = FMUtils.getContext();
    private static TelephonyManager sTelephonyManager = (TelephonyManager) sContext.getSystemService(Context.TELEPHONY_SERVICE);

    private TelUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    public static void call(Activity activity, String number){
        Intent intent = IntentUtil.getCallIntent(number);
        activity.startActivity(intent);
    }

    public static void dial(Activity activity, String number){
        Intent intent = IntentUtil.getDialIntent(number);
        activity.startActivity(intent);
    }

    public static void registerPhoneStateListener(PhoneStateListener phoneStateListener){
        sTelephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    public static void unregisterPhoneStateListener(){
        sTelephonyManager.listen(null, PhoneStateListener.LISTEN_CALL_STATE);
    }
}

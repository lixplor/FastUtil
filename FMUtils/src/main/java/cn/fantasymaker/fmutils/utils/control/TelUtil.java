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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import cn.fantasymaker.fmutils.utils.FMUtils;
import cn.fantasymaker.fmutils.utils.builders.IntentUtil;

/**
 * Created :  2016-08-11
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
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

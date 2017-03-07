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

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.AlarmManager.AlarmClockInfo;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;

import com.lixplor.fastutil.utils.FastUtil;

/**
 * Created :  2016-08-07
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class AlarmUtil {

    /*
    todo
    x创建闹钟
    修改闹钟
    x删除闹钟
    查询闹钟
    x创建定时任务
    获取定时任务
    x删除定时任务
    查询定时任务
    闹钟提醒方式(通知, 震动, 响铃, led, 短信, 电话, email, 自定义回调)
     */

    private static Context sContext = FastUtil.getContext();
    private static AlarmManager sAlarmManager = (AlarmManager) sContext.getSystemService(Context.ALARM_SERVICE);

    private AlarmUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    public static void cancel(PendingIntent pendingIntent) {
        sAlarmManager.cancel(pendingIntent);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static AlarmClockInfo getNextAlarmClock() {
        return sAlarmManager.getNextAlarmClock();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setAlarmClock(AlarmClockInfo alarmClockInfo, PendingIntent pendingIntent) {
        sAlarmManager.setAlarmClock(alarmClockInfo, pendingIntent);
    }

    public static void set(int type, long triggerAtMillis, PendingIntent pendingIntent) {
        sAlarmManager.set(type, triggerAtMillis, pendingIntent);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void setAndAllowWhileIdle(int type, long triggerAtMillis, PendingIntent pendingIntent) {
        sAlarmManager.setAndAllowWhileIdle(type, triggerAtMillis, pendingIntent);
    }

    public static void setExact(int type, long triggerAtMillis, PendingIntent pendingIntent) {
        sAlarmManager.setExact(type, triggerAtMillis, pendingIntent);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void setExactAndAllowWhileIdle(int type, long triggerAtMillis, PendingIntent pendingIntent) {
        sAlarmManager.setExactAndAllowWhileIdle(type, triggerAtMillis, pendingIntent);
    }

    public static void setInexactRepeating(int type, long triggerAtMillis, long intervalMillis, PendingIntent pendingIntent) {
        sAlarmManager.setInexactRepeating(type, triggerAtMillis, intervalMillis, pendingIntent);
    }

    public static void setRepeating(int type, long triggerAtMillis, long intervalMillis, PendingIntent pendingIntent) {
        sAlarmManager.setRepeating(type, triggerAtMillis, intervalMillis, pendingIntent);
    }

    public static void setTime(long millis) {
        sAlarmManager.setTime(millis);
    }

    public static void setTime(String timezone) {
        sAlarmManager.setTimeZone(timezone);
    }

    public static void setTime(int type, long windowStartMillis, long windowLengthMillis, PendingIntent pendingIntent) {
        sAlarmManager.setWindow(type, windowStartMillis, windowLengthMillis, pendingIntent);
    }

}

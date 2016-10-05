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

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.AlarmManager.AlarmClockInfo;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;

import cn.fantasymaker.fmutils.utils.FMUtils;

/**
 * Created :  2016-08-07
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
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

    private static Context sContext = FMUtils.getContext();
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

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

package cn.fantasymaker.anutil.utils.runtime;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;

import java.util.List;

import cn.fantasymaker.anutil.utils.FMUtils;

/**
 * Created :  2016-07-25
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class ProcessUtil {

    /*
    todo
    获取正在运行的程序及其信息
    杀死某个程序: 按包名

     */

    private static Context sContext = FMUtils.getContext();
    private static ActivityManager sActivityManager = (ActivityManager) sContext.getSystemService(Context.ACTIVITY_SERVICE);

    private ProcessUtil() throws IllegalAccessException{
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    /**
     * get running processes
     * @return list of running process
     */
    public static List<RunningAppProcessInfo> getRunningProcesses(){
        return sActivityManager.getRunningAppProcesses();
    }

    /**
     * Get importance of a running process. Less important process has more chance to be killed by system when memory is low
     * @param runningAppProcessInfo
     * @return
     */
    public static int getProcessImportance(RunningAppProcessInfo runningAppProcessInfo){
        return runningAppProcessInfo.importance;
    }

    public static String getProcessImportanceName(RunningAppProcessInfo runningAppProcessInfo){
        String name;
        int importance = getProcessImportance(runningAppProcessInfo);
        switch (importance){
            case RunningAppProcessInfo.IMPORTANCE_FOREGROUND: //100
                name = "FOREGROUND";
                break;
            case RunningAppProcessInfo.IMPORTANCE_FOREGROUND_SERVICE: //125
                name = "FOREGROUND_SERVICE";
                break;
            case RunningAppProcessInfo.IMPORTANCE_PERCEPTIBLE: //130
                name = "PERCEPTIBLE";
                break;
            case RunningAppProcessInfo.IMPORTANCE_TOP_SLEEPING: //150
                name = "TOP_SLEEPING";
                break;
            case RunningAppProcessInfo.IMPORTANCE_VISIBLE: //200
                name = "VISIBLE";
                break;
            case RunningAppProcessInfo.IMPORTANCE_SERVICE: //300
                name = "SERVICE";
                break;
            case RunningAppProcessInfo.IMPORTANCE_BACKGROUND: //400
                name = "BACKGROUND";
                break;
            case RunningAppProcessInfo.IMPORTANCE_EMPTY: //500
                name = "EMPTY";
                break;
            case RunningAppProcessInfo.IMPORTANCE_GONE: //1000
                name = "GONE";
                break;
            default:
                name = "";
                break;
        }
        return name;
    }

    public static int getProcessLru(RunningAppProcessInfo runningAppProcessInfo){
        return runningAppProcessInfo.lru;
    }

    /**
     * get amount of running processes
     * @return amount of running process
     */
    public static int getRunningProcessAmount(){
        return getRunningProcesses().size();
    }

    /**
     * Kill a process by its package name<br/>
     * Need permission: uses-permission android:name="android.permission.KILL_BACKGROUND_PROCESSES"
     * @param packageName package name
     */
    public static void killProcess(String packageName){
        sActivityManager.killBackgroundProcesses(packageName);
    }
}

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

package cn.fantasymaker.fmutils.utils.runtime;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;

import java.util.List;

import cn.fantasymaker.fmutils.utils.FMUtils;

/**
 * Created :  2016-07-25
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
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

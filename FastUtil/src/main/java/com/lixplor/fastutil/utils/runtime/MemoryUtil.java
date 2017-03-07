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

package com.lixplor.fastutil.utils.runtime;

import android.app.ActivityManager;
import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.lixplor.fastutil.utils.FastUtil;

/**
 * Created :  2016-07-25
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class MemoryUtil {

    /*
    todo
    获取系统当前内存, 总共, 已用, 剩余
    获取某个应用占用内存
     */

    private static Context sContext = FastUtil.getContext();
    private static ActivityManager sActivityManager = (ActivityManager) sContext.getSystemService(Context.ACTIVITY_SERVICE);
    private static ActivityManager.MemoryInfo sMemoryInfo = new ActivityManager.MemoryInfo();

    private MemoryUtil() throws IllegalAccessException{
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    public static long getUsedMemory(){
        return getTotalMemory() - getAvailableMemory();
    }

    /**
     * get available size of ram. Work for API 16 and above.
     * @return available size in byte
     */
    public static long getAvailableMemory(){
        sActivityManager.getMemoryInfo(sMemoryInfo);
        return sMemoryInfo.availMem;
    }
    /**
     * get total size of ram. Work for API 16 and above.
     * @return total size in byte
     */
    public static long getTotalMemory(){
        sActivityManager.getMemoryInfo(sMemoryInfo);
        return sMemoryInfo.totalMem;
    }

    /**
     * get total size of ram. Work for all API levels.
     * @return total size in byte
     * @deprecated
     */
    public static long getTotalMemory2(){
        StringBuffer sb = new StringBuffer();
        //read this file and pick up numbers
        File file = new File("/proc/meminfo");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String readLine = br.readLine();
            char[] charArray = readLine.toCharArray();
            for (char c : charArray) {
                if (c >='0' && c<='9') {
                    sb.append(c);
                }
            }
            String string = sb.toString();
            long parseLong = Long.parseLong(string);
            return parseLong * 1024;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    public static boolean isMemoryLow(){
        sActivityManager.getMemoryInfo(sMemoryInfo);
        return sMemoryInfo.lowMemory;
    }

    public static long getLowMemoryThreshold(){
        sActivityManager.getMemoryInfo(sMemoryInfo);
        return sMemoryInfo.threshold;
    }

    public static long getUsedMemory(String packageName){
        //// TODO: 16/8/11
        return 0;
    }
}

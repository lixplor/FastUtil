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

package cn.fantasymaker.fmutils.utils.runtime;

import android.app.ActivityManager;
import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import cn.fantasymaker.fmutils.utils.FMUtils;

/**
 * Created :  2016-07-25
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class MemoryUtil {

    /*
    todo
    获取系统当前内存, 总共, 已用, 剩余
    获取某个应用占用内存
     */

    private static Context sContext = FMUtils.getContext();
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

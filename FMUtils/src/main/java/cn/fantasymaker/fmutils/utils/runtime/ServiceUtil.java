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
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import cn.fantasymaker.fmutils.utils.FMUtils;

/**
 * Created :  2016-07-25
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class ServiceUtil {

    private static Context sContext = FMUtils.getContext();
    private static ActivityManager am = (ActivityManager) sContext.getSystemService(Context.ACTIVITY_SERVICE);

    private ServiceUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    /**
     * whether the given service is running
     *
     * @param clazz service class to be checked
     * @return true if the service is running; otherwise false
     */
    public static boolean isServiceRunning(Class<? extends Service> clazz) {
        List<ActivityManager.RunningServiceInfo> list = am.getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo info : list) {
            ComponentName service = info.service;
            String className = service.getClassName();
            if (className.equals(clazz.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * get names of all running service
     *
     * @return a list of running services' names
     */
    public static List<String> getAllRunningServiceName() {
        List<String> runningServices = new ArrayList<String>();
        List<ActivityManager.RunningServiceInfo> list = am.getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo info : list) {
            ComponentName service = info.service;
            String className = service.getClassName();
            runningServices.add(className);
        }
        return runningServices;
    }
}

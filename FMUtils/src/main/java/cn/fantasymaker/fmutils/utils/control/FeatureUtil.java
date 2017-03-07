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

import android.content.Context;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;

import cn.fantasymaker.fmutils.utils.FMUtils;

/**
 * Created :  2016-08-16
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class FeatureUtil {

    /*
    todo
    x获取拥有的功能
    x判断是否有某个功能

     */

    private static Context sContext = FMUtils.getContext();
    private static PackageManager sPackageManager = sContext.getPackageManager();

    private FeatureUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    /**
     * Get all system features
     *
     * @return array of FeatureInfo
     */
    public static FeatureInfo[] getAllFeatures() {
        return sPackageManager.getSystemAvailableFeatures();
    }

    /**
     * If system has a specific feature
     *
     * @param featureName feature name constant from PackageManager class
     * @return true if has; Otherwise false
     */
    public static boolean hasFeature(String featureName) {
        return sPackageManager.hasSystemFeature(featureName);
    }
}

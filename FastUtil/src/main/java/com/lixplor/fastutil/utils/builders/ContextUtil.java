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

package com.lixplor.fastutil.utils.builders;

import android.content.Context;
import android.content.pm.PackageManager;

import com.lixplor.fastutil.utils.FastUtil;

/**
 * Created :  2016-08-11
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class ContextUtil {

    private static Context sContext = FastUtil.getContext();

    private ContextUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    /**
     * Get context of app itself
     *
     * @return context
     */
    public static Context getSelfContext() {
        return sContext;
    }

    /**
     * Get context of other app by its package name
     *
     * @param packageName package name
     * @return context of package
     */
    public static Context getPackageContext(String packageName) {
        Context context = null;
        try {
            context = sContext.createPackageContext(packageName, Context.CONTEXT_INCLUDE_CODE | Context.CONTEXT_IGNORE_SECURITY);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return context;
    }
}

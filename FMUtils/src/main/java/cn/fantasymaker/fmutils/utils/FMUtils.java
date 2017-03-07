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

package cn.fantasymaker.fmutils.utils;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created :  2016-08-11
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class FMUtils {

    private static Context sContext;

    /**
     * Acquire application context for utils
     *
     * @param context Any context. It will use context.getApplicationContext() to retrive global application context.
     */
    public static void init(@NonNull Context context) {
        sContext = context.getApplicationContext();
    }

    /**
     * Get application context
     *
     * @return application context
     */
    public static Context getContext() {
        if (sContext == null) {
            throw new UnsupportedOperationException("Not initialized! Please call 'init(Context context)' first!");
        }
        return sContext;
    }
}

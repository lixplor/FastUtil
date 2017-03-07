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

package com.lixplor.fastutil.utils.develop;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created :  2016-07-25
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class CleanCacheUtil {

    private static Context sContext;

    public static void init(Context context){
        sContext = context;
    }

    /**
     * Clean internal cache(/data/data/com.xxx.xxx/cache)
     */
    public static void cleanInternalCache() {
        deleteFilesByDirectory(sContext.getCacheDir());
    }

    /**
     * Clean internal database(/data/data/com.xxx.xxx/databases)
     */
    public static void cleanDatabases() {
        deleteFilesByDirectory(new File(sContext.getFilesDir().getPath() + sContext.getPackageName() + "/databases"));
    }

    /**
     * Clean internal sharedPreferences(/data/data/com.xxx.xxx/shared_prefs)
     */
    public static void cleanSharedPreference() {
        deleteFilesByDirectory(new File(sContext.getFilesDir().getPath() + sContext.getPackageName() + "/shared_prefs"));
    }

    /**
     * Clean database by name
     */
    public static void cleanDatabaseByName(String dbName) {
        sContext.deleteDatabase(dbName);
    }

    /**
     * Clean internal files (/data/data/com.xxx.xxx/files)
     */
    public static void cleanFiles() {
        deleteFilesByDirectory(sContext.getFilesDir());
    }

    /**
     * Clean external cache(/mnt/sdcard/android/data/com.xxx.xxx/cache)
     */
    public static void cleanExternalCache() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            deleteFilesByDirectory(sContext.getExternalCacheDir());
        }
    }

    /**
     * Clean custom path
     */
    public static void cleanCustomCache(String filePath) {
        deleteFilesByDirectory(new File(filePath));
    }

    /**
     * Clean all datas including internal and external and custom path
     *
     * @param filepath 指定文件路径
     */
    public static void cleanApplicationData(String... filepath) {
        cleanInternalCache();
        cleanExternalCache();
        cleanDatabases();
        cleanSharedPreference();
        cleanFiles();
        for (String filePath : filepath) {
            cleanCustomCache(filePath);
        }
    }

    /**
     * Delete files under specific dir
     *
     * @param directory dir path
     */
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                if (item.isDirectory()) {
                    deleteFilesByDirectory(item);
                } else {
                    item.delete();
                }
            }
        }
    }
}

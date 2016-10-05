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

package cn.fantasymaker.fmutils.utils.develop;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created :  2016-07-25
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
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

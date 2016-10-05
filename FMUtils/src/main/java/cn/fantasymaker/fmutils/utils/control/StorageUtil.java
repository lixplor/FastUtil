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

package cn.fantasymaker.fmutils.utils.control;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.OnObbStateChangeListener;
import android.os.storage.StorageManager;

import java.io.File;

import cn.fantasymaker.fmutils.utils.FMUtils;
import cn.fantasymaker.fmutils.utils.builders.ContextUtil;

/**
 * Created :  2016-07-25
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class StorageUtil {

    /*
    todo
    x外部存储是否挂载, 状态
    x内外空间, 总共, 可用, 剩余
    x内外路径
    获取某个应用占用空间, 总, data, file, sp, databases等(应用, 数据, 缓存)
    -挂载, 卸载, 格式化sd卡-
     */

    private static Context sContext = FMUtils.getContext();
    private static StorageManager sStorageManager = (StorageManager) sContext.getSystemService(Context.STORAGE_SERVICE);

    private StorageUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    public static boolean isExternalStorageMounted() {
        return Environment.MEDIA_MOUNTED.equals(getExternalStorageState())
                || Environment.MEDIA_MOUNTED_READ_ONLY.equals(getExternalStorageState());
    }

    public static boolean isExternalStorageEmulated() {
        return Environment.isExternalStorageEmulated();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static boolean isExternalStorageEmulated(File file) {
        return Environment.isExternalStorageEmulated(file);
    }

    public static boolean isExternalStorageRemovable() {
        return Environment.isExternalStorageRemovable();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static boolean isExternalStorageRemovable(File file) {
        return Environment.isExternalStorageRemovable(file);
    }

    public static boolean isExternalStorageReadable() {
        return getExternalStorageDirectory().canRead();
    }

    public static boolean isExternalStorageWriteable() {
        return getExternalStorageDirectory().canWrite();
    }

    public static String getExternalStorageState() {
        return Environment.getExternalStorageState();
    }

    public static File getExternalStorageDirectory() {
        return Environment.getExternalStorageDirectory();
    }

    public static String getExternalStoragePath() {
        return getExternalStorageDirectory().getAbsolutePath();
    }

    public static boolean isInternalStorageReadable() {
        return getInternalStorageDirectory().canRead();
    }

    public static boolean isInternalStorageWriteable() {
        return getInternalStorageDirectory().canWrite();
    }

    public static File getInternalStorageDirectory() {
        return Environment.getDataDirectory();
    }

    public static String getInternalStoragePath() {
        return getInternalStorageDirectory().getAbsolutePath();
    }

    /**
     * get available size of external storage
     *
     * @return available size in byte
     */
    public static long getAvailableExternalStorageSize() {
        StatFs stat = new StatFs(getExternalStoragePath());
        return stat.getBlockSize() * stat.getAvailableBlocks();
    }

    /**
     * get total size of external storage
     *
     * @return total size in byte
     */
    public static long getTotalExternalStorageSize() {
        StatFs stat = new StatFs(getExternalStoragePath());
        return stat.getBlockSize() * stat.getBlockCount();
    }

    public static long getUsedExternalStorageSize() {
        return getTotalExternalStorageSize() - getAvailableExternalStorageSize();
    }

    /**
     * get available size of inner storage
     *
     * @return available size in byte
     */
    public static long getAvailableInternalStorageSize() {
        StatFs stat = new StatFs(getInternalStoragePath());
        return stat.getBlockSize() * stat.getAvailableBlocks();
    }

    /**
     * get total size of inner storage
     *
     * @return total size in byte
     */
    public static long getTotalInternalStorageSize() {
        StatFs stat = new StatFs(getInternalStoragePath());
        return stat.getBlockSize() * stat.getBlockCount();
    }

    public static long getUsedInternalStorageSize() {
        return getTotalInternalStorageSize() - getAvailableInternalStorageSize();
    }

    public static long getInternalCacheSize(String packageName) {
        Context context = ContextUtil.getPackageContext(packageName);
        return context == null ? 0 : getDirectorySize(context.getCacheDir());
    }

    public static long getExternalCacheSize(String packageName) {
        Context context = ContextUtil.getPackageContext(packageName);
        return isExternalStorageMounted() ? getDirectorySize(context.getExternalCacheDir()) : 0;
    }

    /**
     * Get internal and external cache dir size
     *
     * @return Cache size of both internal and external
     */
    public static long getTotalCacheSize(String packageName) {
        long cacheSize = 0;
        cacheSize += getInternalCacheSize(packageName);
        if (isExternalStorageMounted()) {
            cacheSize += getExternalCacheSize(packageName);
        }
        return cacheSize;
    }

    /**
     * Get size of a directory
     *
     * @param dir directory file object
     */
    public static long getDirectorySize(File dir) {
        long size = 0;
        if (dir == null || !dir.isDirectory()) {
            throw new IllegalArgumentException("Argument is null or is not a directory!");
        }
        try {
            File[] fileList = dir.listFiles();
            for (File file : fileList) {
                if (file.isDirectory()) {
                    size = size + getDirectorySize(file);
                } else {
                    size = size + file.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    public static void mountObb(String rawPath, String key, OnObbStateChangeListener onObbStateChangeListener) {
        sStorageManager.mountObb(rawPath, key, onObbStateChangeListener);
    }

    public static void unmountObb(String rawPath, boolean force, OnObbStateChangeListener onObbStateChangeListener) {
        sStorageManager.unmountObb(rawPath, force, onObbStateChangeListener);
    }

    public static String getMountObbPath(String rawPath) {
        return sStorageManager.getMountedObbPath(rawPath);
    }

    public static boolean isObbMounted(String rawPath) {
        return sStorageManager.isObbMounted(rawPath);
    }

    public static void registerMountEventReceiver(StorageEventReceiver storageEventReceiver) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_MEDIA_BAD_REMOVAL);
        intentFilter.addAction(Intent.ACTION_MEDIA_BUTTON);
        intentFilter.addAction(Intent.ACTION_MEDIA_CHECKING);
        intentFilter.addAction(Intent.ACTION_MEDIA_EJECT);
        intentFilter.addAction(Intent.ACTION_MEDIA_NOFS);
        intentFilter.addAction(Intent.ACTION_MEDIA_MOUNTED);
        intentFilter.addAction(Intent.ACTION_MEDIA_REMOVED);
        intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);
        intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_STARTED);
        intentFilter.addAction(Intent.ACTION_MEDIA_SHARED);
        intentFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTABLE);
        intentFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
        sContext.registerReceiver(storageEventReceiver, intentFilter);
    }

    public static void unregisterMountEventReceiver(StorageEventReceiver storageEventReceiver) {
        sContext.unregisterReceiver(storageEventReceiver);
        storageEventReceiver = null;
    }

    public abstract static class StorageEventReceiver extends BroadcastReceiver {
    }
}

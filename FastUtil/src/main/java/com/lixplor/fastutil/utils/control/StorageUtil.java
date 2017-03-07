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

package com.lixplor.fastutil.utils.control;

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

import com.lixplor.fastutil.utils.FastUtil;
import com.lixplor.fastutil.utils.builders.ContextUtil;

/**
 * Created :  2016-07-25
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
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

    private static Context sContext = FastUtil.getContext();
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

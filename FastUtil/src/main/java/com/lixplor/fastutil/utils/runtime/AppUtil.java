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

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ConfigurationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.os.Process;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import com.lixplor.fastutil.utils.FastUtil;
import com.lixplor.fastutil.utils.convert.RadixUtil;
import com.lixplor.fastutil.utils.develop.LogUtil;

/**
 * Created :  2016-07-24
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class AppUtil {

    /*
    todo
    仅用于本app本身, 不应处理其他app及其进程
     */

    private static Context sContext = FastUtil.getContext();
    private static PackageManager sPackageManager = sContext.getPackageManager();

    private AppUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    public static String getPackageName() {
        return sContext.getPackageName();
    }

    public static ApplicationInfo getApplicationInfo(String packageName) throws PackageManager.NameNotFoundException {
        return sPackageManager.getApplicationInfo(packageName, 0);
    }

    public static PackageInfo getPackageInfo(String packageName, int flag) throws PackageManager.NameNotFoundException {
        return sPackageManager.getPackageInfo(packageName, flag);
    }

    public static void killSelf() {
        Process.killProcess(Process.myPid());
    }

    /**
     * get version name of this app
     *
     * @return version name
     */
    public static String getVersionName() throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo = getPackageInfo(getPackageName(), PackageManager.GET_CONFIGURATIONS);
        return packageInfo == null ? null : packageInfo.versionName;
    }

    /**
     * get version code of this app
     *
     * @return version code
     */
    public static int getVersionCode() throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo = getPackageInfo(getPackageName(), PackageManager.GET_CONFIGURATIONS);
        return packageInfo == null ? 0 : packageInfo.versionCode;
    }

    /**
     * Check if app is sign by release keystore
     * @param releaseSign release signature string to compare
     * @return true if signed with release; Otherwise false
     */
    public static boolean isReleaseSigned(String releaseSign) {
        Signature releaseSignature = new Signature(releaseSign);
        try {
            PackageInfo packageInfo = sPackageManager.getPackageInfo(sContext.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : packageInfo.signatures) {
                if (signature.equals(releaseSignature)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Return true if we can't figure it out, just to play it safe
            return true;
        }
        return false;
    }

    public static String getMD5Sign(String packageName){
        String md5Sign = null;
        try {
            PackageInfo packageInfo = sPackageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            byte[] bytes = packageInfo.signatures[0].toByteArray();
            LogUtil.d(Arrays.toString(bytes));
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            byte[] encBytes = messageDigest.digest();
//            md5Sign = Base64.encodeToString(encBytes, Base64.URL_SAFE);
            md5Sign = RadixUtil.toHexString(encBytes);
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5Sign;
    }


    public static List<PackageInfo> getInstalledPackages(int flag) {
        return sPackageManager.getInstalledPackages(flag);
    }

    public static PermissionInfo[] getPermissions(PackageInfo packageInfo) {
        return packageInfo.permissions;
    }

    public static String[] getUsesPermissions(PackageInfo packageInfo) {
        return packageInfo.requestedPermissions;
    }

    public static ActivityInfo[] getActivities(PackageInfo packageInfo) {
        return packageInfo.activities;
    }

    public static ServiceInfo[] getServices(PackageInfo packageInfo) {
        return packageInfo.services;
    }

    public static ActivityInfo[] getReceivers(PackageInfo packageInfo) {
        return packageInfo.receivers;
    }

    public static ProviderInfo[] getProviders(PackageInfo packageInfo) {
        return packageInfo.providers;
    }

    public static ApplicationInfo getApplicationInfo(PackageInfo packageInfo) {
        return packageInfo.applicationInfo;
    }

    public static int getBaseRevisionCode(PackageInfo packageInfo) {
        return packageInfo.baseRevisionCode;
    }

    public static ConfigurationInfo[] getConfigurationInfos(PackageInfo packageInfo) {
        return packageInfo.configPreferences;
    }

    public static long getFirstInstallTime(PackageInfo packageInfo) {
        return packageInfo.firstInstallTime;
    }

    public static long getLastUpdateTime(PackageInfo packageInfo) {
        return packageInfo.lastUpdateTime;
    }

    public static String getPackageName(PackageInfo packageInfo) {
        return packageInfo.packageName;
    }

    public static Signature[] getSignatures(PackageInfo packageInfo) {
        return packageInfo.signatures;
    }

    public static String getVersionName(PackageInfo packageInfo) {
        return packageInfo.versionName;
    }

    public static int getVersionCode(PackageInfo packageInfo) {
        return packageInfo.versionCode;
    }

    public static String getInstallPath(ApplicationInfo applicationInfo) {
        return applicationInfo.sourceDir;
    }

    public static String getInstallPath(String packageName) throws PackageManager.NameNotFoundException {
        return getApplicationInfo(packageName).sourceDir;
    }

    public static File getApk(String packageName) throws PackageManager.NameNotFoundException {
        return new File(getInstallPath(packageName));
    }

    public static String getApplicationName(String packageName) {
        String applicationName = null;
        try {
            ApplicationInfo applicationInfo = sPackageManager.getApplicationInfo(packageName, 0);
            applicationName = (String) sPackageManager.getApplicationLabel(applicationInfo);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return applicationName;
    }

    public Drawable loadIcon(String packageName) {
        Drawable icon = null;
        try {
            icon = sPackageManager.getApplicationIcon(packageName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return icon;
    }

    public static long getApkSize(String packageName) throws PackageManager.NameNotFoundException {
        return new File(getInstallPath(packageName)).length();
    }
}
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

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Locale;

import com.lixplor.fastutil.utils.FastUtil;

/**
 * Created :  2016-08-11
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class NetworkUtil {

    /*
    Need permission:
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.CHANGE_NETWORK_STATE"/>
     */

    public static final int NETWORK_TYPE_UNKNOWN = -1;
    public static final int NETWORK_TYPE_DISCONNECT = 0;
    public static final int NETWORK_TYPE_WIFI = 1;
    public static final int NETWORK_TYPE_2G = 2;
    public static final int NETWORK_TYPE_3G = 3;
    public static final int NETWORK_TYPE_4G = 4;

    private static Context sContext = FastUtil.getContext();
    private static ConnectivityManager sConnectivityManager = (ConnectivityManager) sContext.getSystemService(Context.CONNECTIVITY_SERVICE);
    private static WifiManager sWifiManager = (WifiManager) sContext.getSystemService(Context.WIFI_SERVICE);

    private NetworkUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    /**
     * Get wifi IP only, mobile is not workable
     *
     * @return wifi IP
     */
    public static String getWifiIP() {
        WifiInfo wifiInfo = sWifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        return String.format(Locale.CHINA,
                "%d.%d.%d.%d",
                (ipAddress & 0xff),
                (ipAddress >> 8 & 0xff),
                (ipAddress >> 16 & 0xff),
                (ipAddress >> 24 & 0xff));
    }

    /**
     * Get network IP, including wifi and mobile
     *
     * @return wifi IP
     */
    public static String getLocalIP() {
        String noIp = "0.0.0.0";
        Enumeration<NetworkInterface> networkInterfaces = null;
        try {
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
            return noIp;
        }
        if (networkInterfaces == null) {
            return noIp;
        }
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                    return inetAddress.getHostAddress();
                }
            }
        }
        return noIp;
    }

    /**
     * Return if network(wifi/mobile/ethernet) is connected
     *
     * @return true if connected; Otherwise false
     */
    public static boolean isNetworkConnected() {
        NetworkInfo networkInfo = sConnectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isAvailable());
    }

    public static boolean isWifiConnected() {
        NetworkInfo networkInfo = sConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return (networkInfo != null && networkInfo.isAvailable());
    }

    public static boolean isMobileNetworkConnected() {
        NetworkInfo networkInfo = sConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return (networkInfo != null && networkInfo.isAvailable());
    }

    /**
     * Get network connection type
     *
     * @return type
     */
    public static int getNetworkType() {
        int type = NETWORK_TYPE_DISCONNECT;
        NetworkInfo info = sConnectivityManager.getActiveNetworkInfo();
        if (info == null) {
            type = NETWORK_TYPE_DISCONNECT;
        } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
            type = NETWORK_TYPE_WIFI;
        } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
            int subType = info.getSubtype();
            if (subType == TelephonyManager.NETWORK_TYPE_CDMA
                    || subType == TelephonyManager.NETWORK_TYPE_GPRS
                    || subType == TelephonyManager.NETWORK_TYPE_EDGE) {
                type = NETWORK_TYPE_2G;
            } else if (subType == TelephonyManager.NETWORK_TYPE_UMTS
                    || subType == TelephonyManager.NETWORK_TYPE_HSDPA
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_A
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_0
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_B) {
                type = NETWORK_TYPE_3G;
            } else if (subType == TelephonyManager.NETWORK_TYPE_LTE) {// LTE是3g到4g的过渡，是3.9G的全球标准
                type = NETWORK_TYPE_4G;
            } else {
                type = NETWORK_TYPE_UNKNOWN;
            }
        }
        return type;
    }

    public static int getNetworkConnectionType() {
        NetworkInfo networkInfo = sConnectivityManager.getActiveNetworkInfo();
        return networkInfo == null ? -1 : networkInfo.getType();
    }

    public static String getNetworkConnectionTypeName() {
        NetworkInfo networkInfo = sConnectivityManager.getActiveNetworkInfo();
        return networkInfo == null ? "DISCONNECTED" : networkInfo.getTypeName();
    }

    /**
     * Toggle wifi
     *
     * @param enable true if enable; Otherwise false
     */
    public static void enableWifi(boolean enable) {
        sWifiManager.setWifiEnabled(enable);
    }

    /**
     * Return if wifi is enabled
     *
     * @return true if enable; Otherwise false
     */
    public static boolean isWifiEnabled() {
        return sWifiManager.isWifiEnabled();
    }

    /**
     * Toggle mobile network
     *
     * @param enable true if enable; Otherwise false
     */
    public static void enableMobileNetwork(boolean enable) {
        try {
            Method setMobileDataEnabledMethod = sConnectivityManager.getClass().getDeclaredMethod("setMobileDataEnabled", boolean.class);
            setMobileDataEnabledMethod.invoke(sConnectivityManager, enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * if mobile network is enabled<br/>
     * WARN: if this method failed, will return false by default<br/>
     *
     * @return true if enable; Otherwise false
     */
    public static boolean isMobileNetworkEnabled() {
        try {
            Method getMobileDataEnabledMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled");
            getMobileDataEnabledMethod.setAccessible(true);
            return (Boolean) getMobileDataEnabledMethod.invoke(sConnectivityManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

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

import android.Manifest;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.lixplor.fastutil.utils.FastUtil;

/**
 * Created :  2016-08-11
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class GpsUtil {

    /*
    todo
    x开启
    ?关闭
    x跳转设置界面
    x获取坐标, 海拔, 速度
     */

    /*
    NEED PERMISSIONS:
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
     */

    private static Context sContext = FastUtil.getContext();
    private static ContentResolver sContentResolver = sContext.getContentResolver();
    private static LocationManager sLocationManager = (LocationManager) sContext.getSystemService(Context.LOCATION_SERVICE);

    private GpsUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    public static LocationManager getLocationManager() {
        return sLocationManager;
    }

    public static void goToGpsSetting() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sContext.startActivity(intent);
    }

    public static boolean isGpsEnabled() {
        return sLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public static void registerGpsListener(@NonNull ContentObserver contentObserver) {
        Uri uri = Settings.Secure.getUriFor(Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        sContentResolver.registerContentObserver(uri, false, contentObserver);
    }

    public static void unregisterGpsListener(@NonNull ContentObserver contentObserver) {
        sContentResolver.unregisterContentObserver(contentObserver);
    }

    public static void addProximityAlert(double latitude, double longtitude, float radius, long expireTime, PendingIntent pendingIntent) {
        if (ActivityCompat.checkSelfPermission(sContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(sContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        sLocationManager.addProximityAlert(latitude, longtitude, radius, expireTime, pendingIntent);
    }

    public static void removeProximityAlert(PendingIntent pendingIntent) {
        if (ActivityCompat.checkSelfPermission(sContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(sContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        sLocationManager.removeProximityAlert(pendingIntent);
    }

    public static void requestLocationUpdates(String provider, long minTime, float minDistance, LocationListener locationListener) {
        if (ActivityCompat.checkSelfPermission(sContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(sContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        sLocationManager.requestLocationUpdates(provider, minTime, minDistance, locationListener);
    }
}

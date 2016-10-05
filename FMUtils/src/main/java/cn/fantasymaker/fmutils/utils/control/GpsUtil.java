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

import cn.fantasymaker.fmutils.utils.FMUtils;

/**
 * Created :  2016-08-11
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
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

    private static Context sContext = FMUtils.getContext();
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

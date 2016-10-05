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

package cn.fantasymaker.fmutils.utils.builders;

import android.accounts.AccountManager;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.DownloadManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.app.UiModeManager;
import android.app.WallpaperManager;
import android.app.admin.DevicePolicyManager;
import android.app.job.JobScheduler;
import android.app.usage.NetworkStatsManager;
import android.app.usage.UsageStatsManager;
import android.appwidget.AppWidgetManager;
import android.bluetooth.BluetoothManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.RestrictionsManager;
import android.content.pm.LauncherApps;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.hardware.display.DisplayManager;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.input.InputManager;
import android.hardware.usb.UsbManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.media.midi.MidiManager;
import android.media.projection.MediaProjectionManager;
import android.media.session.MediaSessionManager;
import android.media.tv.TvInputManager;
import android.net.ConnectivityManager;
import android.net.nsd.NsdManager;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.nfc.NfcManager;
import android.os.BatteryManager;
import android.os.DropBoxManager;
import android.os.PowerManager;
import android.os.UserManager;
import android.os.Vibrator;
import android.os.storage.StorageManager;
import android.print.PrintManager;
import android.support.annotation.NonNull;
import android.telecom.TelecomManager;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.CaptioningManager;
import android.view.inputmethod.InputMethodManager;
import android.view.textservice.TextServicesManager;

import cn.fantasymaker.fmutils.utils.FMUtils;

/**
 * Created :  2016-08-07
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class ManagerUtil {

    private static Context sContext = FMUtils.getContext();

    private ManagerUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    /**
     * Get a system service
     *
     * @param serviceName service name
     * @return service
     */
    public static Object getSystemService(@NonNull String serviceName) {
        return sContext.getSystemService(serviceName);
    }

    /**
     * Get accessibility manager
     *
     * @return manager
     */
    public static AccessibilityManager getAccessibilityManager() {
        return (AccessibilityManager) getSystemService(Context.ACCESSIBILITY_SERVICE);
    }

    /**
     * Get captioning manager
     *
     * @return manager
     */
    @TargetApi(19)
    public static CaptioningManager getCaptioningManager() {
        return (CaptioningManager) getSystemService(Context.CAPTIONING_SERVICE);
    }

    /**
     * Get account manager
     *
     * @return manager
     */
    public static AccountManager getAccountManager() {
        return (AccountManager) getSystemService(Context.ACCOUNT_SERVICE);
    }

    /**
     * Get activity manager
     *
     * @return manager
     */
    public static ActivityManager getActivityManager() {
        return (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
    }

    /**
     * Get alarm manager
     *
     * @return manager
     */
    public static AlarmManager getAlarmManager() {
        return (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    }

    /**
     * Get audio manager
     *
     * @return manager
     */
    public static AudioManager getAudioManager() {
        return (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    }

    /**
     * Get media router manager
     *
     * @return manager
     */
    @TargetApi(16)
    public static MediaRouter getMediaRouter() {
        return (MediaRouter) getSystemService(Context.MEDIA_ROUTER_SERVICE);
    }

    /**
     * Get bluetooth manager
     *
     * @return manager
     */
    @TargetApi(18)
    public static BluetoothManager getBluetoothManager() {
        return (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
    }

    /**
     * Get clipboard manager
     *
     * @return manager
     */
    public static ClipboardManager getClipboardManager() {
        return (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    }

    /**
     * Get connectivity manager
     *
     * @return manager
     */
    public static ConnectivityManager getConnectivityManager() {
        return (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    /**
     * Get device policy manager
     *
     * @return manager
     */
    @TargetApi(8)
    public static DevicePolicyManager getDevicePolicyManager() {
        return (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
    }

    /**
     * Get download manager
     *
     * @return manager
     */
    @TargetApi(9)
    public static DownloadManager getDownloadManager() {
        return (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
    }

    /**
     * Get battery manager
     *
     * @return manager
     */
    @TargetApi(21)
    public static BatteryManager getBatteryManager() {
        return (BatteryManager) getSystemService(Context.BATTERY_SERVICE);
    }

    /**
     * Get nfc manager
     *
     * @return manager
     */
    @TargetApi(10)
    public static NfcManager getNfcManager() {
        return (NfcManager) getSystemService(Context.NFC_SERVICE);
    }

    /**
     * Get dropbox manager
     *
     * @return manager
     */
    @TargetApi(8)
    public static DropBoxManager getDropBoxManager() {
        return (DropBoxManager) getSystemService(Context.DROPBOX_SERVICE);
    }

    /**
     * Get input manager
     *
     * @return manager
     */
    @TargetApi(16)
    public static InputManager getInputManager() {
        return (InputManager) getSystemService(Context.INPUT_SERVICE);
    }

    /**
     * Get display manager
     *
     * @return manager
     */
    @TargetApi(17)
    public static DisplayManager getDisplayManager() {
        return (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
    }

    /**
     * Get input method manager
     *
     * @return manager
     */
    public static InputMethodManager getInputMethodManager() {
        return (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    /**
     * Get text services manager
     *
     * @return manager
     */
    @TargetApi(14)
    public static TextServicesManager getTextServicesManager() {
        return (TextServicesManager) getSystemService(Context.TEXT_SERVICES_MANAGER_SERVICE);
    }

    /**
     * Get keyguard manager
     *
     * @return manager
     */
    public static KeyguardManager getKeyguardManager() {
        return (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
    }

    /**
     * Get layout inflater
     *
     * @return layout inflater
     */
    public static LayoutInflater getLayoutInflater() {
        return (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Get location manager
     *
     * @return manager
     */
    public static LocationManager getLocationManager() {
        return (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    /**
     * Get notification manager
     *
     * @return manager
     */
    public static NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    /**
     * Get nsd manager
     *
     * @return manager
     */
    @TargetApi(16)
    public static NsdManager getNsdManager() {
        return (NsdManager) getSystemService(Context.NSD_SERVICE);
    }

    /**
     * Get power manager
     *
     * @return manager
     */
    public static PowerManager getPowerManager() {
        return (PowerManager) getSystemService(Context.POWER_SERVICE);
    }

    /**
     * Get search manager
     *
     * @return manager
     */
    public static SearchManager getSearchManager() {
        return (SearchManager) getSystemService(Context.SEARCH_SERVICE);
    }

    /**
     * Get sensor manager
     *
     * @return manager
     */
    public static SensorManager getSensorManager() {
        return (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    /**
     * Get storage manager
     *
     * @return manager
     */
    @TargetApi(9)
    public static StorageManager getStorageManager() {
        return (StorageManager) getSystemService(Context.STORAGE_SERVICE);
    }

    /**
     * Get telephony manager
     *
     * @return manager
     */
    public static TelephonyManager getTelephonyManager() {
        return (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
    }

    /**
     * Get subscription manager
     *
     * @return manager
     */
    @TargetApi(22)
    public static SubscriptionManager getSubscriptionManager() {
        return (SubscriptionManager) getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
    }

    /**
     * Get carrier config manager
     *
     * @return manager
     */
    @TargetApi(23)
    public static CarrierConfigManager getCarrierConfigManager() {
        return (CarrierConfigManager) getSystemService(Context.CARRIER_CONFIG_SERVICE);
    }

    /**
     * Get telecom manager
     *
     * @return manager
     */
    @TargetApi(21)
    public static TelecomManager getTelecomManager() {
        return (TelecomManager) getSystemService(Context.TELECOM_SERVICE);
    }

    /**
     * Get ui mode manager
     *
     * @return manager
     */
    @TargetApi(8)
    public static UiModeManager getUiModeManager() {
        return (UiModeManager) getSystemService(Context.UI_MODE_SERVICE);
    }

    /**
     * Get usb manager
     *
     * @return manager
     */
    @TargetApi(12)
    public static UsbManager getUsbManager() {
        return (UsbManager) getSystemService(Context.USB_SERVICE);
    }

    /**
     * Get vibrator
     *
     * @return vibrator
     */
    public static Vibrator getVibrator() {
        return (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    /**
     * Get wallpapaer manager
     *
     * @return manager
     */
    public static WallpaperManager getWallpaperManager() {
        return WallpaperManager.getInstance(sContext);
    }

    /**
     * Get wifi manager
     *
     * @return manager
     */
    public static WifiManager getWifiManager() {
        return (WifiManager) getSystemService(Context.WIFI_SERVICE);
    }

    /**
     * Get wifi p2p manager
     *
     * @return manager
     */
    @TargetApi(14)
    public static WifiP2pManager getWifiP2pManager() {
        return (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
    }

    /**
     * Get window manager
     *
     * @return manager
     */
    public static WindowManager getWindowManager() {
        return (WindowManager) getSystemService(Context.WINDOW_SERVICE);
    }

    /**
     * Get user manager
     *
     * @return manager
     */
    @TargetApi(17)
    public static UserManager getUserManager() {
        return (UserManager) getSystemService(Context.USER_SERVICE);
    }

    /**
     * Get app operations manager
     *
     * @return manager
     */
    @TargetApi(19)
    public static AppOpsManager getAppOpsManager() {
        return (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
    }

    /**
     * Get camera manager
     *
     * @return manager
     */
    @TargetApi(21)
    public static CameraManager getCameraManager() {
        return (CameraManager) getSystemService(Context.CAMERA_SERVICE);
    }

    /**
     * Get launcher manager
     *
     * @return manager
     */
    @TargetApi(21)
    public static LauncherApps getLauncherApps() {
        return (LauncherApps) getSystemService(Context.LAUNCHER_APPS_SERVICE);
    }

    /**
     * Get restrictions manager
     *
     * @return manager
     */
    @TargetApi(21)
    public static RestrictionsManager getRestrictionsManager() {
        return (RestrictionsManager) getSystemService(Context.RESTRICTIONS_SERVICE);
    }

    /**
     * Get print manager
     *
     * @return manager
     */
    @TargetApi(19)
    public static PrintManager getPrintManager() {
        return (PrintManager) getSystemService(Context.PRINT_SERVICE);
    }

    /**
     * Get consumer ir manager
     *
     * @return manager
     */
    @TargetApi(19)
    public static ConsumerIrManager getConsumerIrManager() {
        return (ConsumerIrManager) getSystemService(Context.CONSUMER_IR_SERVICE);
    }

    /**
     * Get media session manager
     *
     * @return manager
     */
    @TargetApi(21)
    public static MediaSessionManager getMediaSessionManager() {
        return (MediaSessionManager) getSystemService(Context.MEDIA_SESSION_SERVICE);
    }

    /**
     * Get finger print manager
     *
     * @return manager
     */
    @TargetApi(23)
    public static FingerprintManager getFingerprintManager() {
        return (FingerprintManager) getSystemService(Context.FINGERPRINT_SERVICE);
    }

    /**
     * Get tv input manager
     *
     * @return manager
     */
    @TargetApi(21)
    public static TvInputManager getTvInputManager() {
        return (TvInputManager) getSystemService(Context.TV_INPUT_SERVICE);
    }

    /**
     * Get usage stats manager
     *
     * @return manager
     */
    @TargetApi(22)
    public static UsageStatsManager getUsageStatsManager() {
        return (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
    }

    /**
     * Get network stats manager
     *
     * @return manager
     */
    @TargetApi(23)
    public static NetworkStatsManager getNetworkStatsManager() {
        return (NetworkStatsManager) getSystemService(Context.NETWORK_STATS_SERVICE);
    }

    /**
     * Get job scheduler manager
     *
     * @return manager
     */
    @TargetApi(21)
    public static JobScheduler getJobScheduler() {
        return (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
    }

    /**
     * Get media projection manager
     *
     * @return manager
     */
    @TargetApi(21)
    public static MediaProjectionManager getMediaProjectionManager() {
        return (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
    }

    /**
     * Get app widget manager
     *
     * @return manager
     */
    @TargetApi(21)
    public static AppWidgetManager getAppWidgetManager() {
        return (AppWidgetManager) getSystemService(Context.APPWIDGET_SERVICE);
    }

    /**
     * Get midi manager
     *
     * @return manager
     */
    @TargetApi(23)
    public static MidiManager getMidiManager() {
        return (MidiManager) getSystemService(Context.MIDI_SERVICE);
    }
}
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

package cn.fantasymaker.fmutils.utils.builders;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;

import java.io.File;
import java.util.ArrayList;

/**
 * Needs permission:<br/>
 * uses-permission android:name="android.permission.CALL_PHONE"<br/>
 * uses-permission android:name="android.permission.SEND_SMS"<br/>
 * <p>
 * Created :  2016-08-05
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class IntentUtil {

    private IntentUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    /**
     * Get intent of dial, go to dial interface but not call directly<br/>
     *
     * @param phoneNumber phone number to be dialed
     * @return intent
     */
    public static Intent getDialIntent(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNumber);
        intent.setData(data);
        return intent;
    }

    /**
     * Get intent of dial, and call directly<br/>
     * Need at least 2 numbers, or will cause error<br/>
     *
     * @param phoneNumber phone number to be dialed
     * @return intent
     */
    public static Intent getCallIntent(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNumber);
        intent.setData(data);
        return intent;
    }

    /**
     * Get intent of contact ui
     *
     * @return intent
     */
    public static Intent getContactIntent() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(ContactsContract.Contacts.CONTENT_URI);
        return intent;
    }

    /**
     * Get intent of sms, no phone number<br/>
     *
     * @param msg sms text
     * @return intent
     */
    public static Intent getSmsIntent(String msg) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.putExtra("sms_body", msg);
        intent.setType("vnd.android-dir/mms-sms");
        return intent;
    }

    /**
     * Get intent of sms, with phone number<br/>
     *
     * @param phoneNumber phone number to be sent
     * @param msg         sms text
     * @return intent
     */
    public static Intent getSmsIntent(String phoneNumber, String msg) {
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", msg);
        return intent;
    }

    public static Intent getEmailIntent(String chooserTitle, String[] to, String[] cc, String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, to);
        intent.putExtra(Intent.EXTRA_CC, cc);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        intent.setType("message/rfc882");
        return Intent.createChooser(intent, chooserTitle);
    }

    /**
     * Get intent of share for text
     *
     * @param chooserTitle share dialog title
     * @param shareText    text to be shared
     * @return intent
     */
    public static Intent getShareIntent(String chooserTitle, String shareText) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, shareText);
        intent.setType("text/plain");
        return Intent.createChooser(intent, chooserTitle);
    }

    /**
     * Get intent of share for a single image
     *
     * @param chooserTitle share dialog title
     * @param uri          image uri to be shared
     * @return intent
     */
    public static Intent getShareIntent(String chooserTitle, Uri uri) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/*");
        return Intent.createChooser(intent, chooserTitle);
    }

    /**
     * Get intent of share for a file
     *
     * @param chooserTitle share dialog title
     * @param file         file to be shared
     * @return intent
     */
    public static Intent getShareIntent(String chooserTitle, File file) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        intent.setType("*/*");
        return Intent.createChooser(intent, chooserTitle);
    }

    /**
     * Get intent of share for multiple images
     *
     * @param chooserTitle share dialog title
     * @param uriList      list which contains image uris to be shared
     * @return intent
     */
    public static Intent getShareIntent(String chooserTitle, ArrayList<? extends Parcelable> uriList) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uriList);
        intent.setType("image/*");
        return Intent.createChooser(intent, chooserTitle);
    }

    /**
     * Get intent to view an app in market<br/>
     * If no market app installed, this will raise an exception
     *
     * @param packageName app package name
     * @return intent
     */
    public static Intent getMarketIntent(String packageName) {
        String uriString = "market://details?id=" + packageName;
        Uri uri = Uri.parse(uriString);
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    public static Intent getBrowserIntent(String chooserTitle, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        return Intent.createChooser(intent, chooserTitle);
    }

    public static Intent getMapIntent(String chooserTitle, double[] position) {
        Uri uri = Uri.parse("geo:" + position[0] + "," + position[1]);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        return Intent.createChooser(intent, chooserTitle);
    }

    public static Intent getBaiduMapIntent(String chooserTitle, double[] position) {
        String intentStr = "intent://map/marker?location=%s,%s&title=&content=&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end";
        Uri uri = Uri.parse(String.format(intentStr, position[0], position[1]));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        return Intent.createChooser(intent, chooserTitle);
    }

    /**
     * Get intent of audio recorder, start it but not record
     *
     * @return intent
     */
    public static Intent getAudioRecordIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setClassName("com.android.soundrecorder", "com.android.soundrecorder.SoundRecorder");
        intent.setType("audio/amr");
        return intent;
    }

    /**
     * Get intent of video capture
     *
     * @param uri video file uri
     * @return intent
     */
    public static Intent getVideoCaptureIntent(Uri uri) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        return intent;
    }

    /**
     * Get intent of photo capture
     *
     * @param uri photo file uri
     * @return intent
     */
    public static Intent getImageCaptureIntent(Uri uri) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        return intent;
    }

    /**
     * Get intent of gallery to select photo
     *
     * @return intent
     */
    public static Intent getGalleryIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        return intent;
    }

    /**
     * Get intent of file chooser to choose an image
     *
     * @return intent
     */
    public static Intent getGalleryChooserIntent(String chooserTitle) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        return Intent.createChooser(intent, chooserTitle);
    }

    /**
     * Get intent of image crop
     *
     * @param uri Cropped image file uri
     * @return intent
     */
    public static Intent getCropIntent(Uri uri) {
        Intent intent = new Intent();
        intent.setAction("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);
        return intent;
    }

    /**
     * Play media
     *
     * @param file media file
     * @return intent
     */
    public static Intent getPlayMediaIntent(File file) {
        Uri uri = Uri.fromFile(file);
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    /**
     * Open browser and search keyword
     *
     * @param keyword keyword to be searched
     * @return intent
     */
    public static Intent getWebSearchIntent(String keyword) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, keyword);
        return intent;
    }

    /**
     * Get intent to start an app
     *
     * @param packageName package name of app to be started
     * @return intent
     */
    public static Intent getStartAppIntent(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(packageName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    /**
     * Install an apk using system package installer
     *
     * @param apkFile apk file
     * @return intent
     */
    public static Intent getInstallApkIntent(File apkFile) {
        Uri uri = Uri.fromFile(apkFile);
        return new Intent(Intent.ACTION_PACKAGE_ADDED, uri);
    }

    /**
     * Uninstall an app using system default
     *
     * @param packageName package name
     * @return intent
     */
    public static Intent getUninstallApkIntent(String packageName) {
        Uri uri = Uri.parse("package:" + packageName);
        return new Intent(Intent.ACTION_PACKAGE_REMOVED, uri);
    }

    /**
     * Fulling uninstall an app, including data
     *
     * @param packageName package name
     * @return intent
     */
    public static Intent getFullyUninstallApkIntent(String packageName) {
        Uri uri = Uri.parse("package:" + packageName);
        return new Intent(Intent.ACTION_PACKAGE_FULLY_REMOVED, uri);
    }

    /**
     * Raise a request to user to enable bluetooth
     *
     * @return intent
     */
    public static Intent getEnableBluetoothIntent() {
        return new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
    }

    /**
     * Raise a request to user to allow being discoverable
     *
     * @return intent
     */
    public static Intent getBluetoothDiscoverableIntent() {
        return new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
    }

    /**
     * Open accessiblity setting
     *
     * @return intent
     */
    public static Intent getAccessibilitySettingIntent() {
        return new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
    }

    /**
     * Open add account setting
     *
     * @return intent
     */
    public static Intent getAddAccountSettingIntent() {
        return new Intent(Settings.ACTION_ADD_ACCOUNT);
    }

    /**
     * Open airplane mode setting
     *
     * @return intent
     */
    public static Intent getAirplaneModeSettingIntent() {
        return new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
    }

    /**
     * Open apn setting
     *
     * @return intent
     */
    public static Intent getApnSettingIntent() {
        return new Intent(Settings.ACTION_APN_SETTINGS);
    }

    /**
     * Open app detail setting
     *
     * @return intent
     */
    public static Intent getAppDetailSettingIntent() {
        return new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
    }

    /**
     * Open developer mode setting
     *
     * @return intent
     */
    public static Intent getDevModeSettingIntent() {
        return new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
    }

    /**
     * Open app setting
     *
     * @return intent
     */
    public static Intent getAppSettingIntent() {
        return new Intent(Settings.ACTION_APPLICATION_SETTINGS);
    }

    /**
     * Open battery saver setting
     *
     * @return intent
     */
    @TargetApi(22)
    public static Intent getBatterySaverSettingIntent() {
        return new Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS);
    }

    /**
     * Open bluetooth setting
     *
     * @return intent
     */
    public static Intent getBluetoothSettingIntent() {
        return new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
    }

    /**
     * Open video caption setting
     *
     * @return intent
     */
    public static Intent getVideoCaptionSettingIntent() {
        return new Intent(Settings.ACTION_CAPTIONING_SETTINGS);
    }

    /**
     * Open cast setting
     *
     * @return intent
     */
    @TargetApi(21)
    public static Intent getCastSettingIntent() {
        return new Intent(Settings.ACTION_CAST_SETTINGS);
    }

    /**
     * Open data roaming setting
     *
     * @return intent
     */
    public static Intent getDataRoamingSettingIntent() {
        return new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
    }

    /**
     * Open date setting
     *
     * @return intent
     */
    public static Intent getDateSettingIntent() {
        return new Intent(Settings.ACTION_DATE_SETTINGS);
    }

    /**
     * Open device info setting
     *
     * @return intent
     */
    public static Intent getDeviceInfoSettingIntent() {
        return new Intent(Settings.ACTION_DEVICE_INFO_SETTINGS);
    }

    /**
     * Open display setting
     *
     * @return intent
     */
    public static Intent getDisplaySettingIntent() {
        return new Intent(Settings.ACTION_DISPLAY_SETTINGS);
    }

    /**
     * Open screen saver setting
     *
     * @return intent
     */
    public static Intent getSceenSaverSettingIntent() {
        return new Intent(Settings.ACTION_DREAM_SETTINGS);
    }

    /**
     * Open home setting
     *
     * @return intent
     */
    @TargetApi(21)
    public static Intent getHomeSettingIntent() {
        return new Intent(Settings.ACTION_HOME_SETTINGS);
    }

    /**
     * Open innore battery optimization setting
     *
     * @return intent
     */
    @TargetApi(23)
    public static Intent getIgnoreBatteryOptSettingIntent() {
        return new Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
    }

    /**
     * Open input method setting
     *
     * @return intent
     */
    public static Intent getIMESettingIntent() {
        return new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
    }

    /**
     * Open input method subtype setting
     *
     * @return intent
     */
    public static Intent getIMESubtypeSettingIntent() {
        return new Intent(Settings.ACTION_INPUT_METHOD_SUBTYPE_SETTINGS);
    }

    /**
     * Open locale setting
     *
     * @return intent
     */
    public static Intent getLocaleSettingIntent() {
        return new Intent(Settings.ACTION_LOCALE_SETTINGS);
    }

    /**
     * Open lacation source setting
     *
     * @return intent
     */
    public static Intent getLocationSourceSettingIntent() {
        return new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    }

    /**
     * Open manage all app setting
     *
     * @return intent
     */
    public static Intent getManageAllAppSettingIntent() {
        return new Intent(Settings.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS);
    }

    /**
     * Open manage app setting
     *
     * @return intent
     */
    public static Intent getManageAppSettingIntent() {
        return new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
    }

    /**
     * Open manage overlay setting
     *
     * @return intent
     */
    @TargetApi(23)
    public static Intent getManageOverlaySettingIntent() {
        return new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
    }

    /**
     * Open internal storage setting
     *
     * @return intent
     */
    public static Intent getInternalStorageSettingIntent() {
        return new Intent(Settings.ACTION_INTERNAL_STORAGE_SETTINGS);
    }

    /**
     * Open manage write setting
     *
     * @return intent
     */
    @TargetApi(23)
    public static Intent getManageWriteSettingIntent() {
        return new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
    }

    /**
     * Open memory card setting
     *
     * @return intent
     */
    public static Intent getMemoryCardSettingIntent() {
        return new Intent(Settings.ACTION_MEMORY_CARD_SETTINGS);
    }

    /**
     * Open network operator setting
     *
     * @return intent
     */
    public static Intent getNetworkOperatorSettingIntent() {
        return new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS);
    }

    /**
     * Open nfc payment setting
     *
     * @return intent
     */
    public static Intent getNfcPaymentSettingIntent() {
        return new Intent(Settings.ACTION_NFC_PAYMENT_SETTINGS);
    }

    /**
     * Open nfc setting
     *
     * @return intent
     */
    public static Intent getNfcSettingIntent() {
        return new Intent(Settings.ACTION_NFC_SETTINGS);
    }

    /**
     * Open nfc share setting
     *
     * @return intent
     */
    public static Intent getNfcShareSettingIntent() {
        return new Intent(Settings.ACTION_NFCSHARING_SETTINGS);
    }

    /**
     * Open notify listener setting
     *
     * @return intent
     */
    @TargetApi(22)
    public static Intent getNotifyListenerSettingIntent() {
        return new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
    }

    /**
     * Open privacy setting
     *
     * @return intent
     */
    @TargetApi(23)
    public static Intent getPrivacySettingIntent() {
        return new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
    }

    /**
     * Open quick launch setting
     *
     * @return intent
     */
    public static Intent getQuickLaunchSettingIntent() {
        return new Intent(Settings.ACTION_QUICK_LAUNCH_SETTINGS);
    }

    /**
     * Open regulatory setting
     *
     * @return intent
     */
    @TargetApi(21)
    public static Intent getRegulatorySettingIntent() {
        return new Intent(Settings.ACTION_SHOW_REGULATORY_INFO);
    }

    /**
     * Open sound setting
     *
     * @return intent
     */
    public static Intent getSoundSettingIntent() {
        return new Intent(Settings.ACTION_SOUND_SETTINGS);
    }

    /**
     * Open search setting
     *
     * @return intent
     */
    public static Intent getSearchSettingIntent() {
        return new Intent(Settings.ACTION_SEARCH_SETTINGS);
    }

    /**
     * Open security setting
     *
     * @return intent
     */
    public static Intent getSecuritySettingIntent() {
        return new Intent(Settings.ACTION_SECURITY_SETTINGS);
    }

    /**
     * Open setting
     *
     * @return intent
     */
    public static Intent getSettingIntent() {
        return new Intent(Settings.ACTION_SETTINGS);
    }

    /**
     * Open sync setting
     *
     * @return intent
     */
    public static Intent getSyncSettingIntent() {
        return new Intent(Settings.ACTION_SYNC_SETTINGS);
    }

    /**
     * Open usage access setting
     *
     * @return intent
     */
    @TargetApi(21)
    public static Intent getUsageAccessSettingIntent() {
        return new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
    }

    /**
     * Open user dict setting
     *
     * @return intent
     */
    public static Intent getUserDictSettingIntent() {
        return new Intent(Settings.ACTION_USER_DICTIONARY_SETTINGS);
    }

    /**
     * Open voice input setting
     *
     * @return intent
     */
    @TargetApi(21)
    public static Intent getVoiceInputSettingIntent() {
        return new Intent(Settings.ACTION_VOICE_INPUT_SETTINGS);
    }

    /**
     * Open wifi ip setting
     *
     * @return intent
     */
    public static Intent getWifiIpSettingIntent() {
        return new Intent(Settings.ACTION_WIFI_IP_SETTINGS);
    }

    /**
     * Open wifi setting
     *
     * @return intent
     */
    public static Intent getWifiSettingIntent() {
        return new Intent(Settings.ACTION_WIFI_SETTINGS);
    }

    /**
     * Open wireless setting
     *
     * @return intent
     */
    public static Intent getWirelessSettingIntent() {
        return new Intent(Settings.ACTION_WIRELESS_SETTINGS);
    }
}
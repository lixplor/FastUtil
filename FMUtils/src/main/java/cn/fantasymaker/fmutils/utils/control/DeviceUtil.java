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

package cn.fantasymaker.fmutils.utils.control;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;

import java.util.List;

import cn.fantasymaker.fmutils.utils.FMUtils;

/**
 * Created :  2016-08-03
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class DeviceUtil {

    /*
    todo 规范方法说明
     */

    /*
    NEED PERMISSIONS:
    <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
     */

    private static Context sContext = FMUtils.getContext();
    private static TelephonyManager sTelephonyManager = (TelephonyManager) sContext.getSystemService(Context.TELEPHONY_SERVICE);
    private static PackageManager sPackageManager = sContext.getPackageManager();


    /*
 * 电话状态：
 * 1.sTelephonyManager.CALL_STATE_IDLE=0          无活动
 * 2.sTelephonyManager.CALL_STATE_RINGING=1  响铃
 * 3.sTelephonyManager.CALL_STATE_OFFHOOK=2  摘机
 */
    public static int getCallState() {
        return sTelephonyManager.getCallState();
    }

    /*
     * 电话方位：
     *
     */
    public static CellLocation getLocation() {
        return sTelephonyManager.getCellLocation();
    }

    /*
     * 唯一的设备ID：
     * GSM手机的 IMEI 和 CDMA手机的 MEID.
     * Return null if device ID is not available.
     */
    public static String getDeviceIdIMEI() {
        return sTelephonyManager.getDeviceId();
    }

    /*
     * 设备的软件版本号：
     * 例如：the IMEI/SV(software version) for GSM phones.
     * Return null if the software version is not available.
     */
    public static String getDeviceSoftwareVersion() {
        return sTelephonyManager.getDeviceSoftwareVersion();
    }

    /*
     * 手机号：
     * GSM手机的 MSISDN.
     * Return null if it is unavailable.
     */
    public static String getPhone1Number() {
        return sTelephonyManager.getLine1Number();
    }

    /*
     * 附近的电话的信息:
     * 类型：List<NeighboringCellInfo>
     * 需要权限：android.Manifest.permission#ACCESS_COARSE_UPDATES
     */
    public static List<NeighboringCellInfo> getNeighboringCellInfo() {
        return sTelephonyManager.getNeighboringCellInfo();
    }

    /*
     * 获取ISO标准的国家码，即国际长途区号。
     * 注意：仅当用户已在网络注册后有效。
     *       在CDMA网络中结果也许不可靠。
     */
    public static String getNetworkCountryIso() {
        return sTelephonyManager.getNetworkCountryIso();
    }

    /*
     * MCC+MNC(mobile country code + mobile network code)
     * 注意：仅当用户已在网络注册时有效。
     *    在CDMA网络中结果也许不可靠。
     */
    public static String getNetworkOperator() {
        return sTelephonyManager.getNetworkOperator();
    }

    /*
     * 按照字母次序的current registered operator(当前已注册的用户)的名字
     * 注意：仅当用户已在网络注册时有效。
     *    在CDMA网络中结果也许不可靠。
     */
    public static String getNetworkOperatorName() {
        return sTelephonyManager.getNetworkOperatorName();
    }

    /*
     * 当前使用的网络类型：
     * 例如： NETWORK_TYPE_UNKNOWN  网络类型未知  0
       NETWORK_TYPE_GPRS     GPRS网络  1
       NETWORK_TYPE_EDGE     EDGE网络  2
       NETWORK_TYPE_UMTS     UMTS网络  3
       NETWORK_TYPE_HSDPA    HSDPA网络  8
       NETWORK_TYPE_HSUPA    HSUPA网络  9
       NETWORK_TYPE_HSPA     HSPA网络  10
       NETWORK_TYPE_CDMA     CDMA网络,IS95A 或 IS95B.  4
       NETWORK_TYPE_EVDO_0   EVDO网络, revision 0.  5
       NETWORK_TYPE_EVDO_A   EVDO网络, revision A.  6
       NETWORK_TYPE_1xRTT    1xRTT网络  7
     */
    public static int getNetworkType() {
        return sTelephonyManager.getNetworkType();
    }

    /*
     * 手机类型：
     * 例如： PHONE_TYPE_NONE  无信号
       PHONE_TYPE_GSM   GSM信号
       PHONE_TYPE_CDMA  CDMA信号
     */
    public static int getPhoneType() {
        return sTelephonyManager.getPhoneType();
    }

    /*
     * Returns the ISO country code equivalent for the SIM provider's country code.
     * 获取ISO国家码，相当于提供SIM卡的国家码。
     *
     */
    public static String getSimCountryIso() {
        return sTelephonyManager.getSimCountryIso();
    }

    /*
     * Returns the MCC+MNC (mobile country code + mobile network code) of the provider of the SIM. 5 or 6 decimal digits.
     * 获取SIM卡提供的移动国家码和移动网络码.5或6位的十进制数字.
     * SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断).
     */
    public static String getSimOperator() {
        return sTelephonyManager.getSimOperator();
    }

    /*
     * 服务商名称：
     * 例如：中国移动、联通
     * SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断).
     */
    public static String getSimOperatorName() {
        return sTelephonyManager.getSimOperatorName();
    }

    /*
     * SIM卡的序列号：
     * 需要权限：READ_PHONE_STATE
     */
    public static String getSimSerialNumber() {
        return sTelephonyManager.getSimSerialNumber();
    }

    /*
     * SIM的状态信息：
     *  SIM_STATE_UNKNOWN          未知状态 0
     SIM_STATE_ABSENT           没插卡 1
     SIM_STATE_PIN_REQUIRED     锁定状态，需要用户的PIN码解锁 2
     SIM_STATE_PUK_REQUIRED     锁定状态，需要用户的PUK码解锁 3
     SIM_STATE_NETWORK_LOCKED   锁定状态，需要网络的PIN码解锁 4
     SIM_STATE_READY            就绪状态 5
     */
    public static int getSimState() {
        return sTelephonyManager.getSimState();
    }

    /*
     * 唯一的用户ID：
     * 例如：IMSI(国际移动用户识别码) for a GSM phone.
     * 需要权限：READ_PHONE_STATE
     */
    public static String getSubscriberId() {
        return sTelephonyManager.getSubscriberId();
    }

    /*
     * 取得和语音邮件相关的标签，即为识别符
     * 需要权限：READ_PHONE_STATE
     */
    public static String getVoiceMainAlphaTag() {
        return sTelephonyManager.getVoiceMailAlphaTag();
    }

    /*
     * 获取语音邮件号码：
     * 需要权限：READ_PHONE_STATE
     */
    public static String getVoiceMailNumber() {
        return sTelephonyManager.getVoiceMailNumber();
    }

    /*
     * ICC卡是否存在
     */
    public static boolean hasIccCard() {
        return sTelephonyManager.hasIccCard();
    }

    /*
     * 是否漫游:
     * (在GSM用途下)
     */
    public static boolean isNetworkRoaming() {
        return sTelephonyManager.isNetworkRoaming();
    }

    public static String getBoardName() {
        return Build.BOARD;
    }

    public static String getBootloaderVersion() {
        return Build.BOOTLOADER;
    }

    public static String getDevelopCodeName() {
        return Build.VERSION.CODENAME;
    }

    public static int getSDKVersionInt() {
        return Build.VERSION.SDK_INT;
    }

    public static String getSystemAndroidVersion() {
        return Build.VERSION.RELEASE;
    }

    public static String getBrand() {
        return Build.BRAND;
    }

    public static String getDevice() {
        return Build.DEVICE;
    }

    public static String getDisplayID() {
        return Build.DISPLAY;
    }

    public static String getFingerprint() {
        return Build.FINGERPRINT;
    }

    public static String getHardwareName() {
        return Build.HARDWARE;
    }

    public static String getHost() {
        return Build.HOST;
    }

    public static String getDeviceID() {
        return Build.ID;
    }

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String getDeviceModel() {
        return Build.MODEL;
    }

    public static String getProductName() {
        return Build.PRODUCT;
    }

    public static String getSerial() {
        return Build.SERIAL;
    }

    public static String getBuildTags() {
        return Build.TAGS;
    }

    public static String getBuildType() {
        return Build.TYPE;
    }

}

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

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.util.UUID;

import cn.fantasymaker.fmutils.utils.FMUtils;
import cn.fantasymaker.fmutils.utils.builders.IntentUtil;

/**
 * Created :  2016-08-16
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class BluetoothUtil {

    /*
    todo
    蓝牙2.0扫描, 连接
    是否有蓝牙4.0功能
    蓝牙是否开启
    x开启蓝牙, 关闭蓝牙
    x扫描
    读写
     */

    /**
    Need permissions:
        <uses-permission android:name="android.permission.BLUETOOTH"/>
        <uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
        <uses-feature android:name="android.hardware.bluetooth_le" android:required="true"/> or false if allow running on any device

     */


    private static Context sContext = FMUtils.getContext();
    private static BluetoothManager sBluetoothManager = (BluetoothManager) sContext.getSystemService(Context.BLUETOOTH_SERVICE);
    private static BluetoothAdapter sBluetoothAdapter = sBluetoothManager.getAdapter();
    private static Handler sHandler = new Handler(Looper.myLooper());
    private static BluetoothGatt sBluetoothGatt;

    private BluetoothUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    public static String getLocalAddress(){
        return sBluetoothAdapter.getAddress();
    }

    public static String getLocalName(){
        return sBluetoothAdapter.getName();
    }

    public static void enableBluetoothSilently(boolean enable){
        if(enable){
            sBluetoothAdapter.enable();
        }else{
            sBluetoothAdapter.disable();
        }
    }

    public static BluetoothDevice getRemoteDevice(String address){
        return sBluetoothAdapter.getRemoteDevice(address);
    }

    public static BluetoothDevice getRemoteDevice(byte[] address){
        return sBluetoothAdapter.getRemoteDevice(address);
    }

    public static int getScanMode(){
        return sBluetoothAdapter.getScanMode();
    }

    public static int getState(){
        return sBluetoothAdapter.getState();
    }


    public static boolean isEnabled(){
        return sBluetoothAdapter.isEnabled();
    }

    public static boolean isDiscovering(){
        return sBluetoothAdapter.isDiscovering();
    }

    public static void requestEnableBluetooth(Activity activity, int requestCode){
        activity.startActivityForResult(IntentUtil.getEnableBluetoothIntent(), requestCode);
    }

    public static boolean discover(long duration){
        sHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cancelDiscover();
            }
        }, duration);
        return sBluetoothAdapter.startDiscovery();
    }

    public static boolean cancelDiscover(){
        return sBluetoothAdapter.cancelDiscovery();
    }

    public static boolean scanBle(long duration, final LeScanCallback leScanCallback){
        sHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cancelScanBle(leScanCallback);
            }
        }, duration);
        return sBluetoothAdapter.startLeScan(leScanCallback);
    }

    public static boolean scanBle(long duration, UUID[] uuids, final LeScanCallback leScanCallback){
        sHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cancelScanBle(leScanCallback);
            }
        }, duration);
        return sBluetoothAdapter.startLeScan(uuids, leScanCallback);
    }

    public static void cancelScanBle(LeScanCallback leScanCallback){
        sBluetoothAdapter.stopLeScan(leScanCallback);
    }

    public static void connect(BluetoothDevice device, boolean autoConnect, BluetoothGattCallback callback){
        sBluetoothGatt = device.connectGatt(sContext, autoConnect, callback);
    }

    public static void connect(String address, boolean autoConnect, BluetoothGattCallback callback){
        BluetoothDevice device = getRemoteDevice(address);
        connect(device, autoConnect, callback);
    }

    public static void disconnect(){
        sBluetoothGatt.disconnect();
        sBluetoothGatt.close();
        sBluetoothGatt = null;
    }

    public static void enableNotification(BluetoothGattCharacteristic characteristic, boolean enable){
        sBluetoothGatt.setCharacteristicNotification(characteristic, enable);
    }

    public static void write(String mac, UUID serviceUUID, UUID characteristicUUID){
        // FIXME: 16/8/16
        BluetoothGattService service = new BluetoothGattService(serviceUUID, BluetoothGattService.SERVICE_TYPE_PRIMARY);
        BluetoothGattCharacteristic characteristic = new BluetoothGattCharacteristic(characteristicUUID, BluetoothGattCharacteristic.PROPERTY_WRITE, BluetoothGattCharacteristic.PERMISSION_WRITE);
        sBluetoothGatt.writeCharacteristic(characteristic);
    }

    public static void read(){

    }
}

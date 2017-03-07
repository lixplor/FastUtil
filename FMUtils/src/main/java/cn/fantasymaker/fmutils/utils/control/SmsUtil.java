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
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import cn.fantasymaker.fmutils.utils.FMUtils;
import cn.fantasymaker.fmutils.utils.builders.IntentUtil;
import cn.fantasymaker.fmutils.utils.develop.LogUtil;

/**
 * Created :  2016-08-11
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class SmsUtil {

    /*
    todo
    x发送短信: 跳转, 静默
    x监听短信
    读取短信(备份)
    写入短信(还原)
     */

    /*
    Permissions:
    <uses-permission android:name="android.permission.SEND_SMS"/>
    <uses-permission android:name="android.permission.RECEIVE_SMS"/>
    <uses-permission android:name="android.permission.READ_SMS"/>
    <uses-permission android:name="android.permission.WRITE_SMS"/>
     */

    private static final String ACTION_SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String URI_SMS = "content://sms";
    private static final String INDEX_ADDR = "address";
    private static final String INDEX_DATE = "date";
    private static final String INDEX_BODY = "body";
    private static final String INDEX_TYPE = "type";
    private static final String XML_TAG_ROOT = "allsms";
    private static final String XML_TAG_SINGLE_SMS = "sms";
    private static final String XML_TAG_ADDR = "address";
    private static final String XML_TAG_DATE = "date";
    private static final String XML_TAG_TYPE = "type";
    private static final String XML_TAG_BODY = "body";

    private static Context sContext = FMUtils.getContext();
    private static ContentResolver sContentResolver = sContext.getContentResolver();
    private static SmsReceiver receiver;

    /**
     * Send sms message silently.
     *
     * @param phoneNumber phone number
     * @param msg         sms text
     */
    public static void sendSmsSilently(String phoneNumber, String msg) {
        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> messages = smsManager.divideMessage(msg);
        for (String message : messages) {
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
        }
    }

    /**
     * Open sms with phone number and message
     *
     * @param activity    activity
     * @param phoneNumber phone number
     * @param msg         message
     */
    public static void sendSms(Activity activity, String phoneNumber, String msg) {
        Intent intent = IntentUtil.getSmsIntent(phoneNumber, msg);
        activity.startActivity(intent);
    }

    /**
     * Open sms with message
     *
     * @param activity activity
     * @param msg      message
     */
    public static void sendSms(Activity activity, String msg) {
        Intent intent = IntentUtil.getSmsIntent(msg);
        activity.startActivity(intent);
    }

    /**
     * Register a listener for sms arival
     *
     * @param onSmsReceivedListener SmsUtil.SmsReceiver.OnSmsReceivedListener
     */
    public static void registerSmsReceiver(SmsReceiver.OnSmsReceivedListener onSmsReceivedListener) {
        if (receiver == null) {
            receiver = new SmsReceiver(onSmsReceivedListener);
        }
        sContext.registerReceiver(receiver, new IntentFilter(ACTION_SMS_RECEIVED));

    }

    /**
     * Unregister the listener for sms arival
     */
    public static void unregisterSmsReceiver() {
        if (receiver != null) {
            sContext.unregisterReceiver(receiver);
        }
    }

    public static class SmsReceiver extends BroadcastReceiver {

        private OnSmsReceivedListener mOnSmsReceivedListener;

        public SmsReceiver(OnSmsReceivedListener onSmsReceivedListener) {
            mOnSmsReceivedListener = onSmsReceivedListener;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (ACTION_SMS_RECEIVED.equalsIgnoreCase(intent.getAction())) {
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    if (pdus != null && pdus.length > 0) {
                        SmsMessage[] smsMessages = new SmsMessage[pdus.length];
                        LogUtil.i("SmsMessage[].length=" + smsMessages.length);
                        for (int i = 0; i < pdus.length; i++) {
                            smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        }
                        if (mOnSmsReceivedListener != null) {
                            mOnSmsReceivedListener.onSmsReceived(smsMessages);
                        }
                    }
                }
            }
        }

        public interface OnSmsReceivedListener {
            void onSmsReceived(SmsMessage[] smsMessages);
        }
    }

    /**
     * Get all sms as a list
     *
     * @return list of sms
     */
    public static List<SmsBean> getAllSms() {
        List<SmsBean> smsBeen = new ArrayList<>();
        Uri uri = Uri.parse(URI_SMS);
        String[] projection = new String[]{INDEX_ADDR, INDEX_DATE, INDEX_TYPE, INDEX_BODY};
        Cursor cursor = sContentResolver.query(uri, projection, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String address = cursor.getString(cursor.getColumnIndex(INDEX_ADDR));
                String date = cursor.getString(cursor.getColumnIndex(INDEX_DATE));
                String type = cursor.getString(cursor.getColumnIndex(INDEX_TYPE));
                String body = cursor.getString(cursor.getColumnIndex(INDEX_BODY));
                SmsBean smsBean = new SmsBean(address, date, type, body);
                smsBeen.add(smsBean);
            }
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
        return smsBeen;
    }

    /**
     * Store sms in a xml file
     *
     * @param file    xml file
     * @param smsBeen sms list to be stored
     * @throws IOException
     */
    public static void storeSmsInXml(File file, List<SmsBean> smsBeen) throws IOException {
        XmlSerializer serializer = Xml.newSerializer();
        OutputStream os = new FileOutputStream(file);
        serializer.setOutput(os, "UTF-8");
        serializer.startDocument("UTF-8", true);
        serializer.startTag(null, XML_TAG_ROOT);
        for (SmsBean info : smsBeen) {
            //start
            serializer.startTag(null, XML_TAG_SINGLE_SMS);
            //address
            serializer.startTag(null, XML_TAG_ADDR);
            serializer.text(info.getAddress());
            serializer.endTag(null, XML_TAG_ADDR);
            //date
            serializer.startTag(null, XML_TAG_DATE);
            serializer.text(info.getDate());
            serializer.endTag(null, XML_TAG_DATE);
            //type
            serializer.startTag(null, XML_TAG_TYPE);
            serializer.text(info.getType());
            serializer.endTag(null, XML_TAG_TYPE);
            //body
            serializer.startTag(null, XML_TAG_BODY);
            serializer.text(info.getBody());
            serializer.endTag(null, XML_TAG_BODY);
            //end
            serializer.endTag(null, XML_TAG_SINGLE_SMS);
        }
        serializer.endTag(null, XML_TAG_ROOT);
        serializer.endDocument();
        os.close();
    }

    /**
     * Get sms from xml file
     *
     * @param file xml file
     * @return sms list
     * @throws XmlPullParserException
     * @throws IOException
     */
    public static List<SmsBean> getSmsFromXml(File file) throws XmlPullParserException, IOException {
        List<SmsBean> smsBeanList = null;
        SmsBean smsBean = null;
        XmlPullParser parser = Xml.newPullParser();
        InputStream inputStream = new FileInputStream(file);
        parser.setInput(inputStream, "UTF-8");
        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if (XML_TAG_ROOT.equals(parser.getName())) {
                        smsBeanList = new ArrayList<>();
                    } else if (XML_TAG_SINGLE_SMS.equals(parser.getName())) {
                        smsBean = new SmsBean();
                    } else if (XML_TAG_ADDR.equals(parser.getName())) {
                        String address = parser.nextText();
                        smsBean.setAddress(address);
                    } else if (XML_TAG_DATE.equals(parser.getName())) {
                        String date = parser.nextText();
                        smsBean.setDate(date);
                    } else if (XML_TAG_TYPE.equals(parser.getName())) {
                        String type = parser.nextText();
                        smsBean.setType(type);
                    } else if (XML_TAG_BODY.equals(parser.getName())) {
                        String body = parser.nextText();
                        smsBean.setBody(body);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("smsinfo".equals(parser.getName())) {
                        smsBeanList.add(smsBean);
                        smsBean = null;
                    }
                    break;

                default:
                    break;
            }
            eventType = parser.next();
        }
        return smsBeanList;
    }

    public static class SmsBean {
        private String address;
        private String date;
        private String type;
        private String body;

        public SmsBean() {
        }

        public SmsBean(String address, String date, String type, String body) {
            this.address = address;
            this.date = date;
            this.type = type;
            this.body = body;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        @Override
        public String toString() {
            return "SmsBean{" +
                    "address='" + address + '\'' +
                    ", date='" + date + '\'' +
                    ", type='" + type + '\'' +
                    ", body='" + body + '\'' +
                    '}';
        }
    }
}

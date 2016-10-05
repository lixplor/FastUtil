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

package cn.fantasymaker.anutil.utils.control;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import java.util.ArrayList;

import cn.fantasymaker.anutil.utils.FMUtils;

/**
 * 1.Register below in AndroidManifest.xml
 * <service
 * android:name=".utils.builders.NotificationUtil$CustomNotificationListenerService"
 * android:label="@string/app_name"
 * android:permission="android.permission.BIND_NOTIFICATION_LISTENER_SERVICE">
 * <intent-filter>
 * <action android:name="android.service.notification.NotificationListenerService"/>
 * </intent-filter>
 * </service>
 * 2.Go to Setting -> Security -> Notification Read Permission -> Check this app
 * <p/>
 * <p/>
 * Created :  2016-08-02
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class NotificationUtil {

    /*
    todo
    通知的创建, 修改, 取消
    通知消息的获取, 删除, 清空
     */

    private static Context sContext = FMUtils.getContext();
    private static CustomNotificationListenerService sCustomNotificationListenerService;
    private static OnNotificationEventListener sOnNotificationEventListener;

    /**
     * Start a service to listen to notification messages
     *
     * @param onNotificationEventListener listener
     */
    public static void startNotificationListenerService(OnNotificationEventListener onNotificationEventListener) {
        sContext.startService(new Intent(sContext, CustomNotificationListenerService.class));
        setOnNotificationEventListener(onNotificationEventListener);
    }

    /**
     * Stop the service which listen to notification message, and remove listener
     */
    public static void stopNotificationListenerService() {
        sContext.stopService(new Intent(sContext, CustomNotificationListenerService.class));
        removeOnNotificationEventListener();
    }

    /**
     * Clear all removable notification messages
     */
    public static void removeAllNotifications() {
        // fixme: 16/8/12 不能获取到service, 不能调用方法
        sCustomNotificationListenerService.cancelAllNotifications();
    }

    /**
     * Get all active notification messages
     *
     * @return array of StatusBarNotification
     */
    public static StatusBarNotification[] getActiveNotifications() {
        // fixme: 16/8/12 不能获取到service, 不能调用方法
        return sCustomNotificationListenerService.getActiveNotifications();
    }

    public static ArrayList<CustomNotification> getActiveNotificationBeans() {
        ArrayList<CustomNotification> customNotifications = new ArrayList<>();
        for (StatusBarNotification statusBarNotification : getActiveNotifications()) {
            customNotifications.add(new CustomNotification(statusBarNotification));
        }
        return customNotifications;
    }

    /**
     * Set a listener to listen to notification message change status
     *
     * @param onNotificationEventListener listener
     */
    public static void setOnNotificationEventListener(OnNotificationEventListener onNotificationEventListener) {
        sOnNotificationEventListener = onNotificationEventListener;
    }

    /**
     * Remove the listener which listen to notification message change status
     */
    public static void removeOnNotificationEventListener() {
        setOnNotificationEventListener(null);
        sOnNotificationEventListener = null;
    }

    public interface OnNotificationEventListener {
        /**
         * Called when a new message post on notification
         *
         * @param statusBarNotification statusBarNotification
         * @param customNotification    a bean can get field of notification
         */
        void onNotificationPosted(StatusBarNotification statusBarNotification, CustomNotification customNotification);

        /**
         * Called when a notification message has been removed
         *
         * @param statusBarNotification statusBarNotification
         */
        void onNotificationRemoved(StatusBarNotification statusBarNotification);
    }

    public static class CustomNotificationListenerService extends NotificationListenerService {

        @Override
        public void onNotificationPosted(StatusBarNotification sbn) {
            if (sOnNotificationEventListener != null) {
                CustomNotification customNotification = new CustomNotification(sbn);
                sOnNotificationEventListener.onNotificationPosted(sbn, customNotification);
            }
        }

        @Override
        public void onNotificationRemoved(StatusBarNotification sbn) {
            if (sOnNotificationEventListener != null) {
                sOnNotificationEventListener.onNotificationRemoved(sbn);
            }
        }

    }

    public static class CustomNotification {
        private String packageName;
        private int id;
        private long postTime;
        private boolean isClearable;
        private boolean isOngoing;
        private CharSequence tickerText;
        private String title;
        private String text;
        private String subText;
        private Bitmap largeIcon;
        private int smallIconId;
        private PendingIntent pendingIntent;

        public CustomNotification(StatusBarNotification statusBarNotification) {
            setPackageName(statusBarNotification.getPackageName());
            setId(statusBarNotification.getId());
            setPostTime(statusBarNotification.getPostTime());
            setClearable(statusBarNotification.isClearable());
            setOngoing(statusBarNotification.isOngoing());
            Notification notification = statusBarNotification.getNotification();
            Bundle extras = notification.extras;
            setTickerText(notification.tickerText);
            setTitle(extras.getString(Notification.EXTRA_TITLE));
            setLargeIcon((Bitmap) extras.getParcelable(Notification.EXTRA_LARGE_ICON)); //这个可能为空，得注意
            setSmallIconId(extras.getInt(Notification.EXTRA_SMALL_ICON)); //这个可能为空，得注意,网上有的说的不对，这是个int（不是bitmap），指向本应用的中的drawable，获取其他应用的时需要注意，不能根据这个获取到人家包里的（反正我做不到，如果能的大神指点下）
            setText(extras.getString(Notification.EXTRA_TEXT));
            setSubText(extras.getString(Notification.EXTRA_SUB_TEXT));//这个可能为空，得注意
            setPendingIntent(notification.contentIntent);
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getPostTime() {
            return postTime;
        }

        public void setPostTime(long postTime) {
            this.postTime = postTime;
        }

        public boolean isClearable() {
            return isClearable;
        }

        public void setClearable(boolean clearable) {
            isClearable = clearable;
        }

        public boolean isOngoing() {
            return isOngoing;
        }

        public void setOngoing(boolean ongoing) {
            isOngoing = ongoing;
        }

        public CharSequence getTickerText() {
            return tickerText;
        }

        public void setTickerText(CharSequence tickerText) {
            this.tickerText = tickerText;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getSubText() {
            return subText;
        }

        public void setSubText(String subText) {
            this.subText = subText;
        }

        public Bitmap getLargeIcon() {
            return largeIcon;
        }

        public void setLargeIcon(Bitmap largeIcon) {
            this.largeIcon = largeIcon;
        }

        public int getSmallIconId() {
            return smallIconId;
        }

        public void setSmallIconId(int smallIconId) {
            this.smallIconId = smallIconId;
        }

        public PendingIntent getPendingIntent() {
            return pendingIntent;
        }

        public void setPendingIntent(PendingIntent pendingIntent) {
            this.pendingIntent = pendingIntent;
        }

        @Override
        public String toString() {
            return "CustomNotification{" +
                    "packageName='" + packageName + '\'' +
                    ", id=" + id +
                    ", postTime=" + postTime +
                    ", isClearable=" + isClearable +
                    ", isOngoing=" + isOngoing +
                    ", tickerText=" + tickerText + '\'' +
                    ", title='" + title + '\'' +
                    ", text='" + text + '\'' +
                    ", subText='" + subText + '\'' +
                    ", largeIcon=" + largeIcon +
                    ", smallIconId=" + smallIconId +
                    ", pendingIntent=" + pendingIntent +
                    '}';
        }
    }
}

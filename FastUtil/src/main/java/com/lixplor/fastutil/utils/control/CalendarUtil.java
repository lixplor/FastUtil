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

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Reminders;

import com.lixplor.fastutil.utils.FastUtil;

/**
 * Created :  2016-08-11
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class CalendarUtil {

    /*
    todo
    xevent增删改查
    x任务
    x提醒
     */

    /*
    Need permission:
    <uses-permission android:name="android.permission.READ_CALENDAR"/>
    <uses-permission android:name="android.permission.WRITE_CALENDAR"/>
     */

    //Android2.2版本以后的URL，之前的就不写了
    private static final String URI_CALENDARS = "content://com.android.calendar/calendars";
    private static final String URI_EVENTS = "content://com.android.calendar/events";
    private static final String URI_REMINDERS = "content://com.android.calendar/reminders";

    private static Context sContext = FastUtil.getContext();
    private static ContentResolver sContentResolver = sContext.getContentResolver();

    private CalendarUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    public static void addCalendarAccount(ContentValues values) {
        Uri calendarUri = Calendars.CONTENT_URI;
        // TODO: 16/8/23 whats this?
        calendarUri = calendarUri.buildUpon()
                .appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
                .appendQueryParameter(Calendars.ACCOUNT_NAME, "mygmailaddress@gmail.com")
                .appendQueryParameter(Calendars.ACCOUNT_TYPE, "com.android.exchange")
                .build();
        sContentResolver.insert(calendarUri, values);
    }

    public static void getCalendarAccounts() {
        Cursor cursor = sContentResolver.query(Uri.parse(URI_CALENDARS), null, null, null, null);
        if (cursor != null) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                String calendarId = cursor.getString(cursor.getColumnIndex(Calendars._ID));
                String calendarName = cursor.getString(cursor.getColumnIndex(Calendars.NAME));
                String accountName = cursor.getString(cursor.getColumnIndex(Calendars.ACCOUNT_NAME));
            }
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
    }

    public static void getEvents() {
        Cursor cursor = sContentResolver.query(Uri.parse(URI_EVENTS), null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToLast();             //注意：这里与添加事件时的账户相对应，都是向最后一个账户添加
            String eventTitle = cursor.getString(cursor.getColumnIndex(Events.TITLE));
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
    }

    public static void addEvent(ContentValues event) {
        sContentResolver.insert(Uri.parse(URI_EVENTS), event);
    }

    public static void deleteEvent() {

    }

    public static void addReminder(ContentValues reminder) {
        sContentResolver.insert(Uri.parse(URI_REMINDERS), reminder);
    }

    public static class CalendarBuilder {

        private ContentValues mContentValues;

        public CalendarBuilder() {
            mContentValues = new ContentValues();
        }

        public CalendarBuilder setCalendarName(String calendarName) {
            mContentValues.put(Calendars.NAME, calendarName);
            return this;
        }

        public CalendarBuilder setAccountName(String accountName) {
            mContentValues.put(Calendars.ACCOUNT_NAME, accountName);
            return this;
        }

        public CalendarBuilder setAccountType(String accountType) {
            mContentValues.put(Calendars.ACCOUNT_TYPE, accountType);
            return this;
        }

        public CalendarBuilder setOwnerAccount(String account) {
            mContentValues.put(Calendars.OWNER_ACCOUNT, account);
            return this;
        }

        public CalendarBuilder setDisplayName(String displayName) {
            mContentValues.put(Calendars.CALENDAR_DISPLAY_NAME, displayName);
            return this;
        }

        public CalendarBuilder setVisible(boolean visible) {
            int value;
            if (visible) {
                value = 1;
            } else {
                value = 0;
            }
            mContentValues.put(Calendars.VISIBLE, value);
            return this;
        }

        public CalendarBuilder setTimeZone(String timeZone) {
            mContentValues.put(Calendars.CALENDAR_TIME_ZONE, timeZone);
            return this;
        }

        public CalendarBuilder setCalendarColor(int color) {
            mContentValues.put(Calendars.CALENDAR_COLOR, color);
            return this;
        }

        public CalendarBuilder setAccessLevel(int level) {
            mContentValues.put(Calendars.CALENDAR_ACCESS_LEVEL, level);
            return this;
        }

        public CalendarBuilder setSyncEvent(boolean sync) {
            int value;
            if (sync) {
                value = 1;
            } else {
                value = 0;
            }
            mContentValues.put(Calendars.SYNC_EVENTS, value);
            return this;
        }

        public CalendarBuilder setCanOrgnizerRespond(boolean can) {
            int value;
            if (can) {
                value = 1;
            } else {
                value = 0;
            }
            mContentValues.put(Calendars.CAN_ORGANIZER_RESPOND, value);
            return this;
        }
    }

    public static class EventBuilder {

        private ContentValues mContentValues;

        public EventBuilder() {
            mContentValues = new ContentValues();
        }

        public EventBuilder setCalendar(int id) {
            mContentValues.put(Events.CALENDAR_ID, id);
            return this;
        }

        public EventBuilder setEventTitle(String title) {
            mContentValues.put(Events.TITLE, title);
            return this;
        }

        public EventBuilder setEventDescription(String desc) {
            mContentValues.put(Events.DESCRIPTION, desc);
            return this;
        }

        public EventBuilder setEventLocation(String location) {
            mContentValues.put(Events.EVENT_LOCATION, location);
            return this;
        }

        public EventBuilder setAllday(boolean allday) {
            int value;
            if (allday) {
                value = 1;
            } else {
                value = 0;
            }
            mContentValues.put(Events.ALL_DAY, value);
            return this;
        }

        public EventBuilder setStartMillis(long startMillis) {
            mContentValues.put(Events.DTSTART, startMillis);
            return this;
        }

        public EventBuilder setEndMillis(long endMillis) {
            mContentValues.put(Events.DTEND, endMillis);
            return this;
        }

        public EventBuilder setTimezone(String timezone) {
            mContentValues.put(Events.EVENT_TIMEZONE, timezone);
            return this;
        }

        public EventBuilder setDuration(String duration) {
            mContentValues.put(Events.DURATION, duration);
            return this;
        }

        public EventBuilder setRecurrenceDate(String date) {
            mContentValues.put(Events.RDATE, date);
            return this;
        }

        public EventBuilder setRecurrenceRule(String rule) {
            mContentValues.put(Events.RRULE, rule);
            return this;
        }

        public EventBuilder setHasAlarm(boolean alarm) {
            int value;
            if (alarm) {
                value = 1;
            } else {
                value = 0;
            }
            mContentValues.put(Events.HAS_ALARM, value);
            return this;
        }
    }

    public static class ReminderBuilder {

        private ContentValues mContentValues;

        public ReminderBuilder() {
            mContentValues = new ContentValues();
        }

        public ReminderBuilder setEvent(int id) {
            mContentValues.put(Reminders.EVENT_ID, id);
            return this;
        }

        public ReminderBuilder setPriorMinutes(String minutes) {
            mContentValues.put(Reminders.MINUTES, minutes);
            return this;
        }
    }
}

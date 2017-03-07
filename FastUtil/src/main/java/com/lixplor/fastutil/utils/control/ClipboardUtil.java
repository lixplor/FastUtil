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

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.ClipboardManager.OnPrimaryClipChangedListener;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.lixplor.fastutil.utils.FastUtil;

/**
 * Created :  2016-08-07
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class ClipboardUtil {

    /*
    todo
    剪贴板
    复制到, 获取某个
    监听剪切板事件
     */

    private static final String LABEL = ClipboardUtil.class.getSimpleName();

    private static Context sContext = FastUtil.getContext();
    private static ClipboardManager sClipboardManager = (ClipboardManager) sContext.getSystemService(Context.CLIPBOARD_SERVICE);

    private ClipboardUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    public static ClipDescription getClipDescription() {
        return sClipboardManager.getPrimaryClipDescription();
    }

    public static ClipData getClipData() {
        return sClipboardManager.getPrimaryClip();
    }

    public static ClipData newHtmlText(CharSequence label, CharSequence text, String htmlText) {
        return ClipData.newHtmlText(label, text, htmlText);
    }

    public static ClipData newIntent(CharSequence label, Intent intent) {
        return ClipData.newIntent(label, intent);
    }

    public static ClipData newPlainText(CharSequence label, CharSequence text) {
        return ClipData.newPlainText(label, text);
    }

    public static ClipData newRawUri(CharSequence label, Uri uri) {
        return ClipData.newRawUri(label, uri);
    }

    public static ClipData newUri(ContentResolver contentResolver, CharSequence label, Uri uri) {
        return ClipData.newUri(contentResolver, label, uri);
    }

    public static void setText(CharSequence text) {
        ClipData clip = newPlainText(LABEL, text);
        sClipboardManager.setPrimaryClip(clip);
    }

    public static void setHtmlText(CharSequence text, String htmlText) {
        ClipData clip = newHtmlText(LABEL, text, htmlText);
        sClipboardManager.setPrimaryClip(clip);
    }

    public static void setUri(Uri uri) {
        ClipData clip = newRawUri(LABEL, uri);
        sClipboardManager.setPrimaryClip(clip);
    }

    public static void setIntent(Intent intent) {
        ClipData clip = newIntent(LABEL, intent);
        sClipboardManager.setPrimaryClip(clip);
    }

    private static boolean hasContent(String mimeType) {
        ClipDescription description = getClipDescription();
        ClipData clipData = getClipData();
        return clipData != null && description != null && (description.hasMimeType(mimeType));
    }

    public static String getMimeType(int index) {
        return getClipDescription().getMimeType(index);
    }

    public static boolean hasText() {
        return hasContent(ClipDescription.MIMETYPE_TEXT_PLAIN);
    }

    public static boolean hasHtml() {
        return hasContent(ClipDescription.MIMETYPE_TEXT_HTML);
    }

    public static boolean hasUri() {
        return hasContent(ClipDescription.MIMETYPE_TEXT_URILIST);
    }

    public static boolean hasIntent() {
        return hasContent(ClipDescription.MIMETYPE_TEXT_INTENT);
    }

    public static CharSequence getText() {
        if (hasContent(ClipDescription.MIMETYPE_TEXT_PLAIN))
            return getClipData().getItemAt(0).getText();
        else
            return null;
    }

    public static String getHtml() {
        if (hasContent(ClipDescription.MIMETYPE_TEXT_HTML))
            return getClipData().getItemAt(0).getHtmlText();
        else
            return null;
    }

    public static Uri getUri() {
        if (hasContent(ClipDescription.MIMETYPE_TEXT_URILIST))
            return getClipData().getItemAt(0).getUri();
        else
            return null;
    }

    public static Intent getIntent() {
        if (hasContent(ClipDescription.MIMETYPE_TEXT_INTENT))
            return getClipData().getItemAt(0).getIntent();
        else
            return null;
    }

    public static void addChangeListener(OnPrimaryClipChangedListener onPrimaryClipChangedListener) {
        sClipboardManager.addPrimaryClipChangedListener(onPrimaryClipChangedListener);
    }

    public static void removeChangeListener(OnPrimaryClipChangedListener onPrimaryClipChangedListener) {
        sClipboardManager.removePrimaryClipChangedListener(onPrimaryClipChangedListener);
    }
}

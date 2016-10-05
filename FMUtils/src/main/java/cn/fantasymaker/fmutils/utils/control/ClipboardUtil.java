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

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.ClipboardManager.OnPrimaryClipChangedListener;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import cn.fantasymaker.fmutils.utils.FMUtils;

/**
 * Created :  2016-08-07
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class ClipboardUtil {

    /*
    todo
    剪贴板
    复制到, 获取某个
    监听剪切板事件
     */

    private static final String LABEL = ClipboardUtil.class.getSimpleName();

    private static Context sContext = FMUtils.getContext();
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

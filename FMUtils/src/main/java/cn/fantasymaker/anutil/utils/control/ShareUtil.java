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

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.io.File;

import cn.fantasymaker.anutil.utils.builders.IntentUtil;
import cn.fantasymaker.anutil.utils.runtime.AppUtil;

/**
 * Created :  2016-08-11
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class ShareUtil {

     /*
    todo
    分享:
    x应用,
    x文件,
    x图片,
    音乐,
    视频,
    文档
     */

    private ShareUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }


    public static void shareApk(String chooserTitle, Activity activity, String packageName) throws PackageManager.NameNotFoundException {
        File apk = AppUtil.getApk(packageName);
        Intent intent = IntentUtil.getShareIntent(chooserTitle, apk);
        activity.startActivity(intent);
    }

    public static void shareFile(String chooserTitle, Activity activity, File file) {
        Intent intent = IntentUtil.getShareIntent(chooserTitle, file);
        activity.startActivity(intent);
    }

    public static void shareImage(String chooserTitle, Activity activity, File imageFile) {
        Intent intent = IntentUtil.getShareIntent(chooserTitle, imageFile);
        activity.startActivity(intent);
    }

    public static void shareImage(String chooserTitle, Activity activity, Uri imageUri) {
        Intent intent = IntentUtil.getShareIntent(chooserTitle, imageUri);
        activity.startActivity(intent);
    }
}

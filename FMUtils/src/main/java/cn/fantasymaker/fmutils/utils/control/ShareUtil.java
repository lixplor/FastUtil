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
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.io.File;

import cn.fantasymaker.fmutils.utils.builders.IntentUtil;
import cn.fantasymaker.fmutils.utils.runtime.AppUtil;

/**
 * Created :  2016-08-11
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
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

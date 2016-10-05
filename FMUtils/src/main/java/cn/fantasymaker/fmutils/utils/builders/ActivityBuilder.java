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

package cn.fantasymaker.fmutils.utils.builders;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created :  2016-08-07
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class ActivityBuilder {

    private Intent intent;

    public <T extends Activity> ActivityBuilder(Context context, @NonNull Class<T> clazz) {
        intent = new Intent(context, clazz);
    }

    public <T extends Serializable> ActivityBuilder putExtra(@NonNull String key, T value) {
        intent.putExtra(key, value);
        return this;
    }

    public ActivityBuilder putExtra(@NonNull String key, Parcelable value) {
        intent.putExtra(key, value);
        return this;
    }

    public ActivityBuilder putExtra(@NonNull String key, Parcelable[] value) {
        intent.putExtra(key, value);
        return this;
    }

    public <T extends Parcelable> ActivityBuilder putExtra(@NonNull String key, ArrayList<T> value) {
        intent.putExtra(key, value);
        return this;
    }

    public ActivityBuilder removeExtra(@NonNull String key) {
        intent.removeExtra(key);
        return this;
    }

    public ActivityBuilder setFlags(int flags) {
        intent.setFlags(flags);
        return this;
    }

    public ActivityBuilder addFlags(int flags) {
        intent.addFlags(flags);
        return this;
    }

    public Intent buildIntent() {
        return intent;
    }

    public void startActivity(Context context) {
        context.startActivity(intent);
    }

    public void startForResult(@NonNull Activity activity, int requestCode) {
        activity.startActivityForResult(intent, requestCode);
    }

    public void startForResult(@NonNull Activity activity, int requestCode, @Nullable Bundle options) {
        activity.startActivityForResult(intent, requestCode, options);
    }
}
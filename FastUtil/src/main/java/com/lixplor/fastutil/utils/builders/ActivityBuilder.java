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

package com.lixplor.fastutil.utils.builders;

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
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
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
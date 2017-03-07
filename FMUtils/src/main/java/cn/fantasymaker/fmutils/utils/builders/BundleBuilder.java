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

package cn.fantasymaker.fmutils.utils.builders;

import android.os.Bundle;
import android.os.IBinder;

import java.io.Serializable;

/**
 * Created :  2016-08-07
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class BundleBuilder {

    private Bundle mBundle;

    public BundleBuilder(){
        mBundle = new Bundle();
    }

    public <T extends Serializable> BundleBuilder put(String key, T value) {
        mBundle.putSerializable(key, value);
        return this;
    }

    public BundleBuilder put(Bundle bundle){
        mBundle.putAll(bundle);
        return this;
    }

    public BundleBuilder put(String key, IBinder iBinder){
        mBundle.putBinder(key, iBinder);
        return this;
    }

    public <T> T get(String key) {
        return (T) mBundle.getSerializable(key);
    }

    public Bundle build() {
        return mBundle;
    }
}

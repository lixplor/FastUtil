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

import android.os.Bundle;
import android.os.IBinder;

import java.io.Serializable;

/**
 * Created :  2016-08-07
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
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

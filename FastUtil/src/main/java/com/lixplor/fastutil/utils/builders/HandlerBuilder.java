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
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created :  2016-09-02
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class HandlerBuilder<A extends Activity> extends Handler {

    /*
    Example:
        HandlerBuilder<MainActivity> handlerBuilder = new HandlerBuilder<>(this);
        handlerBuilder.setOnHandlerMessageListener(new HandlerBuilder.OnHandlerMessageListener<MainActivity>() {
            @Override
            public void onHandleMessage(Message msg, MainActivity mainActivity) {

            }
        });
     */

    private WeakReference<A> activityWeakReference;
    private OnHandlerMessageListener<A> mOnHandlerMessageListener;

    public HandlerBuilder(A a) {
        activityWeakReference = new WeakReference<>(a);
    }

    public void setOnHandlerMessageListener(OnHandlerMessageListener<A> onHandlerMessageListener) {
        mOnHandlerMessageListener = onHandlerMessageListener;
    }

    public void release(){
        this.removeCallbacksAndMessages(null);
        activityWeakReference.clear();
        activityWeakReference = null;
    }

    @Override
    public void handleMessage(Message msg) {
        A a = activityWeakReference.get();
        if (mOnHandlerMessageListener != null) {
            mOnHandlerMessageListener.onHandleMessage(msg, a);
        }
    }

    public interface OnHandlerMessageListener<A> {
        void onHandleMessage(Message msg, A a);
    }
}

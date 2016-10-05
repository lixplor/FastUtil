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

package cn.fantasymaker.anutil.utils.builders;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created :  2016-09-02
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
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

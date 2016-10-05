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

package cn.fantasymaker.fmutils.utils.develop;

import android.os.Handler;

/**
 * Created :  2016-08-13
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class AsyncTaskUtil {


    public static void run(SimpleAsyncTask simpleAsyncTask){
        simpleAsyncTask.execute();
    }


    public static abstract class SimpleAsyncTask {

        //handle message to do
        private Handler handler = new Handler(){
            public void handleMessage(android.os.Message msg) {
                afterThreadTask();
            };
        };

        /**
         * task to be done before child thread
         */
        public abstract void preThreadTask();

        /**
         * task to be done in child thread
         */
        public abstract void inThreadTask();

        /**
         * task to be done after child thread
         */
        public abstract void afterThreadTask();

        /**
         * execute tasks in sequence
         */
        public void execute(){
            preThreadTask();
            new Thread(){
                public void run() {
                    inThreadTask();
                    handler.sendEmptyMessage(0);
                };
            }.start();
        }
    }
}

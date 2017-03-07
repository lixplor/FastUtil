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

package com.lixplor.fastutil.utils.develop;

import android.os.Handler;

/**
 * Created :  2016-08-13
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
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

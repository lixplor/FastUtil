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

package com.lixplor.fastutil.utils.handler;

/**
 * Created :  2016-07-24
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class AppCrashHandler implements Thread.UncaughtExceptionHandler {

    private static int sDelayPause = 1000 * 3;
    private static Thread.UncaughtExceptionHandler sDefaultHandler;
    private static AppCrashHandler sAppCrashHandler = new AppCrashHandler();

    private AppCrashHandler() {
        sDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    /**
     * Get singleton
     *
     * @return AppCrashHandler
     */
    public static AppCrashHandler getInstance() {
        return sAppCrashHandler;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && sDefaultHandler != null) {
            sDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(sDelayPause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (mOnHandleExceptionListener != null) {
                mOnHandleExceptionListener.onExceptionHandled(thread, ex);
            }
        }
    }

    /**
     * Custom to handle exception
     *
     * @param exception exception
     * @return true if exception has been handled; Otherwise false.
     */
    private boolean handleException(Throwable exception) {
        if (exception == null) {
            return false;
        }
        if (mOnHandleExceptionListener != null) {
            mOnHandleExceptionListener.onHandleException(exception);
        }
        return true;
    }

    /**
     * Set crash delay pause time
     *
     * @param delayPause time in millisec
     */
    public static void setDelayPause(int delayPause) {
        sDelayPause = delayPause;
    }

    /**
     * OnHandleExceptionListener
     */
    public static OnHandleExceptionListener mOnHandleExceptionListener;

    public interface OnHandleExceptionListener {

        /**
         * Callback to custom how to handle exception<br/>
         * You can do other operations such as store/upload expection to server, toast a tip to inform user
         *
         * @param exception exception
         */
        void onHandleException(Throwable exception);

        /**
         * Callback to custom what to do after exception was handled<br/>
         * You can quit app here for example
         *
         * @param thread    thread which raise exception
         * @param exception exception
         */
        void onExceptionHandled(Thread thread, Throwable exception);
    }

    /**
     * Set listener for handling exception
     *
     * @param onHandleExceptionListener onHandleExceptionListener
     */
    public static void setOnHandleExceptionListener(OnHandleExceptionListener onHandleExceptionListener) {
        mOnHandleExceptionListener = onHandleExceptionListener;
    }
}
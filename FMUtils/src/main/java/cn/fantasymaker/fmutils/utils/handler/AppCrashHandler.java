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

package cn.fantasymaker.fmutils.utils.handler;

/**
 * Created :  2016-07-24
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
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
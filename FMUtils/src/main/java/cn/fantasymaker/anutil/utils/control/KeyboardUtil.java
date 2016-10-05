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

package cn.fantasymaker.anutil.utils.control;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

import cn.fantasymaker.anutil.utils.FMUtils;

/**
 * Created :  2016-08-07
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class KeyboardUtil {

    /*
    todo
    x键盘显示,
    x隐藏,
    >获取当前状态
    >获取键盘高度
    ?获取键盘输入内容
    x监听键盘事件, 显示, 隐藏, 按键
    修改输入法?
     */

    private static int sKeyboardHeight = 0;
    private static Context sContext = FMUtils.getContext();
    private static InputMethodManager sInputMethodManager = (InputMethodManager) sContext.getSystemService(Context.INPUT_METHOD_SERVICE);
    private static MyOnGlobalLayoutListener sMyOnGlobalLayoutListener;

    private KeyboardUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    public static View getFocusedView(Activity activity) {
        return activity.getCurrentFocus();
    }

    public static void show(@NonNull View view) {
        view.requestFocus();
        sInputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    public static void show(@NonNull Activity activity) {
        show(activity.findViewById(android.R.id.content));
    }

    public static void show(@NonNull Fragment fragment) {
        show(fragment.getView());
    }

    public static void hide(@NonNull View view) {
        view.clearFocus();
        sInputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void hide(@NonNull Activity activity) {
        hide(activity.findViewById(android.R.id.content));
    }

    public static void hide(@NonNull Fragment fragment) {
        hide(fragment.getView());
    }

    public static void setOnKeyboardVisibilityChangedListener(@NonNull Activity activity, @NonNull final OnKeyboardVisibilityChangedListener onKeyboardVisibilityChangedListener) {
        View rootLayout = activity.findViewById(android.R.id.content);
        sMyOnGlobalLayoutListener = new MyOnGlobalLayoutListener(activity, onKeyboardVisibilityChangedListener);
        rootLayout.getViewTreeObserver().addOnGlobalLayoutListener(sMyOnGlobalLayoutListener);
    }

    public static void removeOnKeyboardVisibilityChangedListener(@NonNull Activity activity) {
        View rootLayout = activity.findViewById(android.R.id.content);
        rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(sMyOnGlobalLayoutListener);
    }

    public interface OnKeyboardVisibilityChangedListener {

        /**
         * Called when keyboard show or hide
         *
         * @param isKeyboardShow true if keyboard shows up; Otherwise false
         */
        void onKeyboardVisibilityChanged(boolean isKeyboardShow);

        /**
         * Called when keyboard height changed
         *
         * @param height keyboard height
         */
        void onKeyboardHeightChanged(int height);
    }

    private static class MyOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        private Activity mActivity;
        private OnKeyboardVisibilityChangedListener mOnKeyboardVisibilityChangedListener;
        private Rect rect = new Rect();
        private View decorView = mActivity.getWindow().getDecorView();
        private View rootLayout = mActivity.findViewById(android.R.id.content);

        public MyOnGlobalLayoutListener(Activity activity, OnKeyboardVisibilityChangedListener onKeyboardVisibilityChangedListener) {
            mActivity = activity;
            mOnKeyboardVisibilityChangedListener = onKeyboardVisibilityChangedListener;
        }

        @Override
        public void onGlobalLayout() {
            decorView.getWindowVisibleDisplayFrame(rect);
            int screenHeight = rootLayout.getRootView().getHeight();
            int keyboardHeight = screenHeight - rect.bottom;
            if (sKeyboardHeight != keyboardHeight) {
                sKeyboardHeight = keyboardHeight;
                if (mOnKeyboardVisibilityChangedListener != null) {
                    mOnKeyboardVisibilityChangedListener.onKeyboardHeightChanged(sKeyboardHeight);
                }
                if (sKeyboardHeight > 0) {
                    if (mOnKeyboardVisibilityChangedListener != null) {
                        mOnKeyboardVisibilityChangedListener.onKeyboardVisibilityChanged(true);
                    }
                } else {
                    if (mOnKeyboardVisibilityChangedListener != null) {
                        mOnKeyboardVisibilityChangedListener.onKeyboardVisibilityChanged(false);
                    }
                }
            }
        }
    }
}

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

package com.lixplor.fastutil.utils.control;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

import com.lixplor.fastutil.utils.FastUtil;

/**
 * Created :  2016-08-07
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
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
    private static Context sContext = FastUtil.getContext();
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

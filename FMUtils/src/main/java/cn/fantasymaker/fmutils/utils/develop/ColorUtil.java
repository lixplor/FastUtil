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

package cn.fantasymaker.fmutils.utils.develop;

import android.graphics.Color;

import java.util.Random;

/**
 * Created :  2016-07-28
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class ColorUtil {

    private ColorUtil() throws IllegalAccessException{
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    /**
     * get random color rgb value
     * @return rgb color int
     */
    public static int getRandomColor(int startValue, int varyValue){
        Random random = new Random();
        int red = random.nextInt(varyValue) + startValue;
        int green = random.nextInt(varyValue) + startValue;
        int blue = random.nextInt(varyValue) + startValue;
        return getColor(red, green, blue);
    }

    public static int parseColor(String hexColorString){
        return Color.parseColor(hexColorString);
    }

    public static String toArgbHexString(int color){
        return "#" + Integer.toHexString(getAlpha(color)) + Integer.toHexString(getRed(color)) + Integer.toHexString(getGreen(color)) + Integer.toHexString(getBlue(color));
    }

    public static String toArgbHexString(int color, boolean upperCase){
        if(upperCase){
            return "#" + Integer.toHexString(getAlpha(color)).toUpperCase() + Integer.toHexString(getRed(color)).toUpperCase() + Integer.toHexString(getGreen(color)).toUpperCase() + Integer.toHexString(getBlue(color)).toUpperCase();
        }else{
            return toArgbHexString(color);
        }
    }

    public static int[] toArgb(int color){
        int[] argb = new int[]{};
        argb[0] = getAlpha(color);
        argb[1] = getRed(color);
        argb[2] = getGreen(color);
        argb[3] = getBlue(color);
        return argb;
    }

    public static int[] toRgb(int color){
        int[] argb = new int[]{};
        argb[0] = getRed(color);
        argb[1] = getGreen(color);
        argb[2] = getBlue(color);
        return argb;
    }

    public static int getColor(int alpha, int red, int green, int blue){
        return Color.argb(alpha, red, green, blue);
    }

    public static int getColor(int red, int green, int blue){
        return Color.rgb(red, green, blue);
    }

    public static int getAlpha(int color){
        return Color.alpha(color);
    }

    public static int getRed(int color){
        return Color.red(color);
    }

    public static int getGreen(int color){
        return Color.green(color);
    }

    public static int getBlue(int color){
        return Color.blue(color);
    }

    public static float[] colorToHsv(int color){
        float[] hsv = {0f, 0f, 0f};
        Color.colorToHSV(color, hsv);
        return hsv;
    }

    public static int hsvToColor(float hsv[]){
        return Color.HSVToColor(hsv);
    }

    public static int hsvToColor(int alpha, float[] hsv){
        return Color.HSVToColor(alpha, hsv);
    }

    public static float[] rgbToHsv(int red, int green, int blue){
        float[] hsv = {0f, 0f, 0f};
        Color.RGBToHSV(red, green, blue, hsv);
        return hsv;
    }

    public static int setAlpha(int color, int alpha){
        return getColor(alpha, getRed(color), getGreen(color), getBlue(color));
    }

    public static int setRed(int color, int red){
        return getColor(getAlpha(color), red, getGreen(color), getBlue(color));
    }

    public static int setGreen(int color, int green){
        return getColor(getAlpha(color), getRed(color), green, getBlue(color));
    }

    public static int setBlue(int color, int blue){
        return getColor(getAlpha(color), getRed(color), getGreen(color), blue);
    }

    public static int setAlpha(int color, float percent){
        return getColor((int) (getAlpha(color) * percent), getRed(color), getGreen(color), getBlue(color));
    }

    public static int setRed(int color, float percent){
        return getColor(getAlpha(color), (int) (getRed(color) * percent), getGreen(color), getBlue(color));
    }

    public static int setGreen(int color, float percent){
        return getColor(getAlpha(color), getRed(color), (int) (getGreen(color) * percent), getBlue(color));
    }

    public static int setBlue(int color, float percent){
        return getColor(getAlpha(color), getRed(color), getGreen(color), (int) (getBlue(color) * percent));
    }

    public static int changeAlpha(int color, float changePercent){
        int oriAlpha = getAlpha(color);
        int newAlpha = (int) (oriAlpha * changePercent);
        return setAlpha(color, newAlpha);
    }

    public static int changeRed(int color, float changePercent){
        int oriRed = getRed(color);
        int newRed = (int) (oriRed * changePercent);
        return setRed(color, newRed);
    }

    public static int changeGreen(int color, float changePercent){
        int oriGreen = getGreen(color);
        int newGreen = (int) (oriGreen * changePercent);
        return setGreen(color, newGreen);
    }

    public static int changeBlue(int color, float changePercent){
        int oriBlue = getBlue(color);
        int newBlue = (int) (oriBlue * changePercent);
        return setBlue(color, newBlue);
    }
}

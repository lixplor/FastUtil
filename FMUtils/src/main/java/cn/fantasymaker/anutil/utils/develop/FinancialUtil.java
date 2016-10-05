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

package cn.fantasymaker.anutil.utils.develop;

import java.math.BigDecimal;

/**
 * Created :  2016-07-27
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class FinancialUtil {

    /*
    todo
    金额数字转大写
     */

    private static String[] unitStrArray = { "", "拾", "佰", "仟" };
    private static String[] numStrArray = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒",
            "捌", "玖" };

    private static String getRMBIntPart(double dNum) {
        String rMBIntPart = "";
        int i, weiShu;
        int intNum = (int) dNum;
        int yiNum = intNum / 100000000;
        int wanNum = (intNum - (yiNum * 100000000)) / 10000;
        int yuanNum = intNum - (yiNum * 100000000) - (wanNum * 10000);

        // 分3部分处理数字，每部分加上亿，万，元
        // yi
        if (yiNum != 0) {
            rMBIntPart = rMBIntPart + numStrArray[yiNum] + "亿";
        }

        // wan
        if(wanNum != 0){
            for (i = 3; i >= 0; i--) {
                weiShu = wanNum / (int) Math.pow(10, i);
                if (weiShu == 0) {
                    rMBIntPart = rMBIntPart + numStrArray[weiShu];
                } else {
                    rMBIntPart = rMBIntPart + numStrArray[weiShu] + unitStrArray[i];
                }
                wanNum = wanNum - (weiShu * (int) Math.pow(10, i));
            }
            // 去除重复的零
            while (rMBIntPart.contains("零零")) {
                rMBIntPart = rMBIntPart.replaceAll("零零", "零");
            }
            // 去掉结尾的零
            if (rMBIntPart.endsWith("零")) {
                rMBIntPart = rMBIntPart.substring(0, rMBIntPart.length() - 1);
            }
            rMBIntPart = rMBIntPart + "万";
        }

        // yuan
        if(yuanNum != 0){
            for (i = 3; i >= 0; i--) {
                weiShu = yuanNum / (int) Math.pow(10, i);
                if (weiShu == 0) {
                    rMBIntPart = rMBIntPart + numStrArray[weiShu];
                } else {
                    rMBIntPart = rMBIntPart + numStrArray[weiShu] + unitStrArray[i];
                }
                yuanNum = yuanNum - (weiShu * (int) Math.pow(10, i));
            }
            // 去除重复的零
            while (rMBIntPart.contains("零零")) {
                rMBIntPart = rMBIntPart.replaceAll("零零", "零");
            }
            // 去掉结尾的零
            if (rMBIntPart.endsWith("零")) {
                rMBIntPart = rMBIntPart.substring(0, rMBIntPart.length() - 1);
            }
            rMBIntPart = rMBIntPart + "元";
        }

        // 去掉总字符串开头的零
        if (rMBIntPart.startsWith("零")) {
            rMBIntPart = rMBIntPart.substring(1, rMBIntPart.length());
        }

        return rMBIntPart;
    }

    private static String getRMBDecPart(double dNum) {
        String rMBDecPart;
        double xiaoShu = dNum - Math.floor(dNum);
        // String转BigDecimal再转Double可提高精度
        String temp = Double.toString(xiaoShu);
        BigDecimal bDecimal = new BigDecimal(temp);
        xiaoShu = bDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        if (xiaoShu == 0) {
            rMBDecPart = "整";
            return rMBDecPart;
        }
        rMBDecPart = numStrArray[(int) ((xiaoShu * 100) / 10)] + "角";
        int fen = (int) ((xiaoShu * 100) - ((int) (xiaoShu * 100) / 10 * 10));
        if (fen != 0) {
            rMBDecPart = rMBDecPart
                    + numStrArray[(int) ((xiaoShu * 100) - ((int) (xiaoShu * 100) / 10 * 10))]
                    + "分";
        }
        return rMBDecPart;
    }

    public static String numToRMB(double num) throws NumberFormatException{
        return "人民币" + getRMBIntPart(num) + getRMBDecPart(num);
    }

    public static String numToRMB(String text) throws NumberFormatException{
        BigDecimal bDecimal = new BigDecimal(text);
        double num = bDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return numToRMB(num);
    }
}

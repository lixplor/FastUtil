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

import java.math.BigDecimal;

/**
 * Created :  2016-07-27
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
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

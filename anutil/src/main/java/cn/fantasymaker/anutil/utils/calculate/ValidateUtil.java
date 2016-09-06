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

package cn.fantasymaker.anutil.utils.calculate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created :  2016-08-08
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class ValidateUtil {

    /*
    todo
    验证常用密码格式的pattern: 纯数字 x~y位, 纯英文 x~y位, 数字+英文 x~y位, 数字+英文+指定符号 x~y位, 数字+大写英文+小写英文+指定符号 x~y位
    trim后的校验
    提取关键字
     */

    public static final String PATTERN_INT = "\\-?(\\d|([1-9]\\d+))"; //verified
    public static final String PATTERN_INT_POSITIVE = "[1-9]|([1-9]\\d+)"; //verified
    public static final String PATTERN_INT_NEGATIVE = "\\-([1-9]|[1-9]\\d+)"; //verified
    public static final String PATTERN_FLOAT = "\\-?(\\d|([1-9]\\d+))\\.\\d+"; //verified
    public static final String PATTERN_CHINESE = "[\\u4E00-\\u9FA5]+"; //verified
    public static final String PATTERN_DIGITAL = "\\d+"; //verified
    public static final String PATTERN_LETTER = "[a-zA-Z]+"; //verified
    public static final String PATTERN_LETTER_UPPERCASE = "[A-Z]+"; //verified
    public static final String PATTERN_LETTER_LOWERCASE = "[a-z]+"; //verified
    public static final String PATTERN_DIGITAL_AND_LETTER = "([a-zA-Z][a-zA-Z]*\\d+[a-zA-Z\\d]*)|(\\d\\d*[a-zA-Z]+[a-zA-Z\\d]*)"; //verified
    public static final String PATTERN_SYMBOL = "(\\p{Punct})+"; //verified
    public static final String PATTERN_BLANK = "\\s+"; //verified
    public static final String PATTERN_DATE_YYYYMMDD = "[1-9]\\d{0,3}[\\-\\./]((0?[1-9])|(1[0-2]))[\\-\\./]((0?[1-9])|([1-2]\\d)|3[0-1])"; //verified
    public static final String PATTERN_TIME = "todo"; // TODO: 16/9/6
    public static final String PATTERN_ZIPCODE = "\\d{6}"; //verified
    public static final String PATTERN_ID_CARD = "[1-9](\\d{13}|\\d{16})[a-zA-Z0-9]"; //verified
    public static final String PATTERN_QQ = "[1-9][0-9]{4,}"; //verified
    public static final String PATTERN_URL = "[a-zA-Z]+://[^\\s]*";
    public static final String PATTERN_IP_V4 = "((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])"; //verified
    public static final String PATTERN_DOMAIN_NAME = "([a-zA-Z]+://)?(([a-zA-Z0-9]+|([a-zA-Z0-9]+[a-zA-Z0-9-]+[a-zA-Z0-9]+))\\.)+[a-zA-Z]{2,}"; //verified
    public static final String PATTERN_MOBILE = "^[1][3,5,7,8]\\d{9}$"; //verified
    public static final String PATTERN_TEL_WITH_AREA = "^[0][1-9]{2,3}-[0-9]{5,10}$";
    public static final String PATTERN_TEL_WITHOUT_AREA = "^[1-9][0-9]{5,8}$";
    public static final String PATTERN_EMAIL = "[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+|_[a-zA-Z0-9]+)*@\\w+(\\.\\w{2,3}){1,3}"; //verified

    private ValidateUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    public static boolean isInt(String text) {
        return text.matches(PATTERN_INT);
    }

    public static boolean isPositiveInt(String text) {
        return text.matches(PATTERN_INT_POSITIVE);
    }

    public static boolean isNegativeInt(String text) {
        return text.matches(PATTERN_INT_NEGATIVE);
    }

    public static boolean isFloat(String text) {
        return text.matches(PATTERN_FLOAT);
    }

    public static boolean isChinese(String text) {
        return text.matches(PATTERN_CHINESE);
    }

    public static boolean isDigital(String text) {
        return text.matches(PATTERN_DIGITAL);
    }

    public static boolean isLetter(String text) {
        return text.matches(PATTERN_LETTER);
    }

    public static boolean isUppercaseLetter(String text) {
        return text.matches(PATTERN_LETTER_UPPERCASE);
    }

    public static boolean isLowercaseLetter(String text) {
        return text.matches(PATTERN_LETTER_LOWERCASE);
    }

    public static boolean isDigitalAndLetter(String text) {
        return text.matches(PATTERN_DIGITAL_AND_LETTER);
    }

    public static boolean isSymbol(String text) {
        return text.matches(PATTERN_SYMBOL);
    }

    public static boolean isBlank(String text) {
        return text.matches(PATTERN_BLANK);
    }

    public static boolean isDate(String text) {
        return text.matches(PATTERN_DATE_YYYYMMDD);
    }

    public static boolean isTime(String text) {
        return text.matches(PATTERN_TIME);
    }

    public static boolean isZipCode(String text) {
        return text.matches(PATTERN_ZIPCODE);
    }

    public static boolean isIDCard(String text) {
        return text.matches(PATTERN_ID_CARD);
    }

    public static boolean isQQ(String text) {
        return text.matches(PATTERN_QQ);
    }

    public static boolean isURL(String text) {
        return text.matches(PATTERN_URL);
    }

    public static boolean isIPv4(String text) {
        return text.matches(PATTERN_IP_V4);
    }

    public static boolean isDomainName(String text) {
        return text.matches(PATTERN_DOMAIN_NAME);
    }

    public static boolean isMobile(String text) {
        return text.matches(PATTERN_MOBILE); // 验证手机号

    }

    public static boolean isPhone(String number) {
        // TODO: 16/8/8 特殊电话号码: 110 10086 400-100-1111
        if (number.length() > 9) {
            return number.matches(PATTERN_TEL_WITH_AREA);
        } else {
            return number.matches(PATTERN_TEL_WITHOUT_AREA);
        }
    }

    public static boolean isEmail(String text) {
        return text.matches(PATTERN_EMAIL);
    }

    public static boolean isSuffixWith(String text, String suffix) {
        return text.endsWith(suffix);
    }

    public static boolean containsDigital(String text) {
        return false;
    }

    public static boolean containsLetter(String text) {
        return false;
    }

    public static boolean containsChinese(String text) {
        return false;
    }

    public static boolean containsSymbol(String text) {
        return false;
    }

    public static boolean complyFormat(String regex, String text) {
        return text.matches(regex);
    }

    public static List<String> find(String regex, String text){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }
}

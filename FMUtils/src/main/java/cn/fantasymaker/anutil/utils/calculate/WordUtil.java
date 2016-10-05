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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created :  2016-07-27
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class WordUtil {

    /*
    todo
    字数计算:
    总字数	0
    UTF-8字符数	0
    GBK字符数	0
    x中文字数	0
    x英文字数	0
    x数字字数
    x标点字数	0
    x空白字符数	0
    全角字数
     */

    private WordUtil() throws IllegalAccessException{
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    public static long count(String pattern, String text){
        long count = 0;
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(text);
        while (matcher.find()){
            count += matcher.group().length();
        }
        return count;
    }

    public static long total(String text){
        return text.length();
    }

    public static long countChinese(String text){
        return count(ValidateUtil.PATTERN_CHINESE, text);
    }

    public static long countLatin(String text){
        return count(ValidateUtil.PATTERN_LETTER, text);
    }

    public static long countDigital(String text){
        return count(ValidateUtil.PATTERN_DIGITAL, text);
    }

    public static long countSymbol(String text){
        return count(ValidateUtil.PATTERN_SYMBOL, text);
    }

    public static long countBlank(String text){
        return count(ValidateUtil.PATTERN_BLANK, text);
    }

    public static ArrayList<String> extract(String pattern, String text){
        ArrayList<String> result = new ArrayList<>();
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(text);
        while (matcher.find()){
            result.add(matcher.group());
        }
        return result;
    }

    public static ArrayList<String> extractChinese(String text){
        return extract(ValidateUtil.PATTERN_CHINESE, text);
    }

    public static ArrayList<String> extractLatin(String text){
        return extract(ValidateUtil.PATTERN_LETTER, text);
    }

    public static ArrayList<String> extractDigital(String text){
        return extract(ValidateUtil.PATTERN_DIGITAL, text);
    }

    public static ArrayList<String> extractSymbol(String text){
        return extract(ValidateUtil.PATTERN_SYMBOL, text);
    }

    public static ArrayList<String> extractBlank(String text){
        return extract(ValidateUtil.PATTERN_BLANK, text);
    }
}

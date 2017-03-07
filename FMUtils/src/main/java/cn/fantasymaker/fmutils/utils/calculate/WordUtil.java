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

package cn.fantasymaker.fmutils.utils.calculate;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created :  2016-07-27
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
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

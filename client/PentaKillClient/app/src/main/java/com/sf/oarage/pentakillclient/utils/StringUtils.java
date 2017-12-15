package com.sf.oarage.pentakillclient.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by liushihan on 2017/12/15.
 */

public class StringUtils {
    public static final char UNDERLINE = '_';

    private static final Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    public static final String DEFAULT_VALUE_LOGIN_SESSION_ORIGIN_CODE = "-1";
    private static final ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
    public static final String mEmojiChar = "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]|";
    public static final String mSpecialChar = "[`~!@#$%^&*()+=|{}''\\[\\].<>/~@#￥%……& *（）——+|{}【】‘”“’、◑ ▂ ]";
    public static final String mSpecialChar1 = "[+=/*--<>&]";
    public static final String mSpecialChar2 = "[+=*--<>&]";

    /**
     * 字符串截取
     *
     * @param str
     * @param length
     * @return
     * @throws Exception
     */
    public static String subString(String str, int length) throws Exception {

        byte[] bytes = str.getBytes("Unicode");
        int n = 0; // 表示当前的字节数
        int i = 2; // 要截取的字节数，从第3个字节开始
        for (; i < bytes.length && n < length; i++) {
            // 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
            if (i % 2 != 0) {
                n++; // 在UCS2第二个字节时n加1
            } else {
                // 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
                if (bytes[i] != 0) {
                    n++;
                }
            }
        }
        // 如果i为奇数时，处理成偶数
        if (i % 2 != 0) {
            // 该UCS2字符是汉字时，去掉这个截一半的汉字
            if (bytes[i - 1] != 0)
                i = i - 1;
                // 该UCS2字符是字母或数字，则保留该字符
            else
                i = i + 1;
        }
        return new String(bytes, 0, i, "Unicode");
    }

    /**
     * @param input
     * @return
     */
    public static String toDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    /**
     * 计算微博内容的长度 1个汉字 == 两个英文字母所占的长度 标点符号区分英文和中文
     *
     * @param c 所要统计的字符序列
     * @return 返回字符序列计算的长度
     */
    public static long calculateWeiboLength(CharSequence c) {

        double len = 0;
        for (int i = 0; i < c.length(); i++) {
            int temp = (int) c.charAt(i);
            if (temp > 0 && temp < 127) {
                len += 0.5;
            } else {
                len++;
            }
        }
        return Math.round(len);
    }

    /**
     * 分割字符串
     *
     * @param str       String 原始字符串
     * @param splitsign String 分隔符
     * @return String[] 分割后的字符串数组
     */
    public static String[] split(String str, String splitsign) {
        int index;
        if (str == null || splitsign == null)
            return null;
        ArrayList<String> al = new ArrayList<>();
        while ((index = str.indexOf(splitsign)) != -1) {
            al.add(str.substring(0, index));
            str = str.substring(index + splitsign.length());
        }
        al.add(str);
        return al.toArray(new String[0]);
    }

    /**
     * 替换字符串
     *
     * @param from   String 原始字符串
     * @param to     String 目标字符串
     * @param source String 母字符串
     * @return String 替换后的字符串
     */
    public static String replace(String from, String to, String source) {
        if (source == null || from == null || to == null)
            return null;
        StringBuilder bf = new StringBuilder("");
        int index;
        while ((index = source.indexOf(from)) != -1) {
            bf.append(source.substring(0, index) + to);
            source = source.substring(index + from.length());
        }
        bf.append(source);
        return bf.toString();
    }

    /**
     * 替换字符串，能能够在HTML页面上直接显示(替换双引号和小于号)
     *
     * @param str String 原始字符串
     * @return String 替换后的字符串
     */
    public static String htmlencode(String str) {
        if (str == null) {
            return null;
        }

        return replace("\"", "&quot;", replace("<", "&lt;", str));
    }

    /**
     * 替换字符串，将被编码的转换成原始码（替换成双引号和小于号）
     *
     * @param str String
     * @return String
     */
    public static String htmldecode(String str) {
        if (str == null) {
            return null;
        }

        return replace("&quot;", "\"", replace("&lt;", "<", str));
    }

    private static final String _BR = "<br/>";

    /**
     * 在页面上直接显示文本内容，替换小于号，空格，回车，TAB
     *
     * @param str String 原始字符串
     * @return String 替换后的字符串
     */
    public static String htmlshow(String str) {
        if (str == null) {
            return null;
        }

        str = replace("<", "&lt;", str);
        str = replace(" ", "&nbsp;", str);
        str = replace("\r\n", _BR, str);
        str = replace("\n", _BR, str);
        str = replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;", str);
        return str;
    }



    /**
     * 获取url的后缀名
     *
     * @param
     * @return
     */
    public static String getUrlFileName(String urlString) {
        String fileName = urlString.substring(urlString.lastIndexOf("/"));
        fileName = fileName.substring(1, fileName.length());
        if ("".equalsIgnoreCase(fileName)) {
            Calendar c = Calendar.getInstance();
            fileName = Integer.toString(c.get(Calendar.YEAR)) + Integer.toString(c.get(Calendar.MONTH))
                    + Integer.toString(c.get(Calendar.DAY_OF_MONTH))
                    + Integer.toString(c.get(Calendar.MINUTE));

        }
        return fileName;
    }

    public static String replaceSomeString(String str) {
        String dest = "";
        String tempStr = str;
        try {
            if (tempStr != null) {
                tempStr = tempStr.replaceAll("\r", "");
                tempStr = tempStr.replaceAll("&gt;", ">");
                tempStr = tempStr.replaceAll("&ldquo;", "“");
                tempStr = tempStr.replaceAll("&rdquo;", "”");
                tempStr = tempStr.replaceAll("&#39;", "'");
                tempStr = tempStr.replaceAll("&nbsp;", "");
                tempStr = tempStr.replaceAll("<br\\s*/>", "\n");
                tempStr = tempStr.replaceAll("&quot;", "\"");
                tempStr = tempStr.replaceAll("&lt;", "<");
                tempStr = tempStr.replaceAll("&lsquo;", "《");
                tempStr = tempStr.replaceAll("&rsquo;", "》");
                tempStr = tempStr.replaceAll("&middot;", "·");
                tempStr = tempStr.replace("&mdash;", "—");
                tempStr = tempStr.replace("&hellip;", "…");
                tempStr = tempStr.replace("&amp;", "×");
                tempStr = tempStr.replaceAll("\\s*", "");
                tempStr = tempStr.trim();
                tempStr = tempStr.replaceAll("<p>", "\n      ");
                tempStr = tempStr.replaceAll("</p>", "");
                tempStr = tempStr.replaceAll("<div.*?>", "\n      ");
                tempStr = tempStr.replaceAll("</div>", "");
                dest = tempStr;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dest;
    }

    /**
     * 清除文本里面的HTML标签
     *
     * @param htmlStr
     * @return
     */
    public static String delHTMLTag(String htmlStr) {
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
        String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
        Log.v("htmlStr", htmlStr);
        try {
            Pattern p_script = Pattern.compile(regEx_script,
                    Pattern.CASE_INSENSITIVE);
            Matcher m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签

            Pattern p_style = Pattern.compile(regEx_style,
                    Pattern.CASE_INSENSITIVE);
            Matcher m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签

            Pattern p_html = Pattern.compile(regEx_html,
                    Pattern.CASE_INSENSITIVE);
            Matcher m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签
        } catch (Exception e) {
            e.printStackTrace();
        }

        return htmlStr; // 返回文本字符串
    }

    public static String delSpace(String str) {
        if (str != null) {
            str = str.replaceAll("\r", "");
            str = str.replaceAll("\n", "");
            str = str.replace(" ", "");
        }
        return str;
    }

    /**
     * 检查字符串是否存在值，如果为true,
     *
     * @param str 待检验的字符串
     * @return 当 str 不为 null 或 "" 就返回 true
     */
    public static boolean isNotNull(String str) {
        return (str != null && !"".equalsIgnoreCase(str.trim()));
    }

    private static final ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    /**
     * 将字符串转位日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater.get().parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 以友好的方式显示时间
     *
     * @param sdate
     * @return
     */
    public static String friendly_time(String sdate) {
        Date time = toDate(sdate);
        if (time == null) {
            return "Unknown";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = dateFormater2.get().format(cal.getTime());
        String paramDate = dateFormater2.get().format(time);
        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            else
                ftime = hour + "小时前";
            return ftime;
        }

        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            else
                ftime = hour + "小时前";
        } else if (days == 1) {
            ftime = "昨天";
        } else if (days == 2) {
            ftime = "前天";
        } else if (days > 2 && days <= 10) {
            ftime = days + "天前";
        } else if (days > 10) {
            ftime = dateFormater2.get().format(time);
        }
        return ftime;
    }

    public static String trimmy(String str) {
        String dest = "";
        if (str != null) {
            str = str.replaceAll("-", "");
            str = str.replaceAll("\\+", "");
            dest = str;
        }
        return dest;
    }

    public static String replaceBlank(String str) {

        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\r");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 判断给定字符串时间是否为今日
     *
     * @param sdate
     * @return boolean
     */
    public static boolean isToday(String sdate) {
        boolean b = false;
        Date time = toDate(sdate);
        Date today = new Date();
        if (time != null) {
            String nowDate = dateFormater2.get().format(today);
            String timeDate = dateFormater2.get().format(time);
            if (nowDate.equals(timeDate)) {
                b = true;
            }
        }
        return b;
    }

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean 若输入字符串为null或空字符串，返回true
     */
    public static boolean isEmpty(String input) {
        return input == null || "".equals(input) || "null".equals(input);
    }


    /**
     * 判断List是否为空
     * @param vList
     * @return
     */
    public static boolean isEmpty(List vList){
        return null == vList || vList.isEmpty();
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (email == null || email.trim().length() == 0)
            return false;
        return emailer.matcher(email).matches();
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defValue;
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 过滤空格和输入错误逗号(,，)
     *
     * @param number 数字
     * @return String
     */
    public static String getDecimalString(String number) {
        if (isEmpty(number)) {
            return number;
        }

        String newNumber = number;

        if (number.contains(" ")) {
            newNumber = newNumber.replace(" ", "");
        }
        if (number.contains(",")) {
            newNumber = newNumber.replace(",", ".");
        }
        if (number.contains("，")) {
            newNumber = newNumber.replace("，", ".");
        }
        return newNumber;
    }

    /**
     * 对象转Float
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static float toFloat(String obj) {
        try {
            return Float.parseFloat(getDecimalString(obj));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static double toDouble(String obj) {
        try {
            return Double.parseDouble(getDecimalString(obj));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 字符串转布尔值
     *
     * @param b
     * @return 转换异常返回 false
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 字符串转bigDecimal
     *
     * @param bigDecimal
     * @return 转换异常返回
     */
    public static BigDecimal toBigDecimal(String bigDecimal) {
        try {
            if (!isEmpty(bigDecimal)) {
                return new BigDecimal(getDecimalString(bigDecimal));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BigDecimal(0);
    }


    /**
     * 判断是不是合法手机 handset 手机号码
     */
    public static boolean isHandset(String handset) {
        if (handset == null) {
            return false;
        }

        try {
            if (!"1".equals(handset.substring(0, 1))) {
                return false;
            }
            if (handset.length() != 11) {
                return false;
            }
            String check = "^[0123456789]+$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(handset);
            boolean isMatched = matcher.matches();
            return isMatched;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断输入的字符串是否为纯汉字
     *
     * @param str 传入的字符窜
     * @return 如果是纯汉字返回true, 否则返回false
     */
    public static boolean isChinese(String str) {
        Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        if (isEmpty(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断是否为浮点数，包括double和float
     *
     * @param str 传入的字符串
     * @return 是浮点数返回true, 否则返回false
     */
    public static boolean isDouble(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 是否为空白,包括null和""
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断是否是指定长度的字符串
     *
     * @param text   字符串
     * @param lenght 自定的长度
     * @return
     */
    public static boolean isLenghtStrLentht(String text, int lenght) {
        return text.length() <= lenght;
    }

    /**
     * 是否是短信的长度
     *
     * @param text
     * @return
     */
    public static boolean isSMSStrLentht(String text) {
        return text.length() <= 70;
    }

    // 判断是否为url
    public static boolean checkEmail(String email) {

        Pattern pattern = Pattern
                .compile("^\\w+([-.]\\w+)*@\\w+([-]\\w+)*\\.(\\w+([-]\\w+)*\\.)*[a-z]{2,3}$");
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    // 判断微博分享是否为是否为120个
    public static boolean isShareStrLentht(String text) {
        return text.length() <= 120;
    }

    public static String getFileNameFromUrl(String url) {

        // 名字不能只用这个
        // 通过 ‘？’ 和 ‘/’ 判断文件名
        String extName;
        String filename;
        int index = url.lastIndexOf('?');
        if (index > 1) {
            extName = url.substring(url.lastIndexOf('.') + 1, index);
        } else {
            extName = url.substring(url.lastIndexOf('.') + 1);
        }
        filename = hashKeyForDisk(url) + "." + extName;
        return filename;
        /*
         * int index = url.lastIndexOf('?'); String filename; if (index > 1) {
		 * filename = url.substring(url.lastIndexOf('/') + 1, index); } else {
		 * filename = url.substring(url.lastIndexOf('/') + 1); }
		 *
		 * if (filename == null || "".equals(filename.trim())) {// 如果获取不到文件名称
		 * filename = UUID.randomUUID() + ".apk";// 默认取一个文件名 } return filename;
		 */
    }

    /**
     * 一个散列方法,改变一个字符串(如URL)到一个散列适合使用作为一个磁盘文件名。
     */
    public static String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static byte[] getGBKBytes(String str) {
        if (str == null)
            return null;
        byte[] bytes = null;
        try {
            bytes = str.getBytes("GBK");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                bytes = str.getBytes("gbk");
            } catch (Exception e2) {
                e2.printStackTrace();
                bytes = str.getBytes();
            }
        }
        return bytes;
    }


    /**
     * @param @param  param
     * @param @return
     * @return String
     * @throws
     * @Title: underlineToCamel
     * @Description: 下划线转驼峰命名
     */
    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }


    /**
     * 将null字符串转换为空字符串
     *
     * @param empty
     * @return
     */
    public static String parseNullToEmpty(String empty) {
        return (empty + "").replace("null", "");
    }

    /**
     * 判断是否是手机号码
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(147)|(15[^4,\\D])|(17[0-8])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 根据手机号码显示名字
     *
     * @param mobile
     * @param username
     * @return
     */
    public static String showUserName(String mobile, String username) {
        return username;
    }

    /**
     * 隐藏手机号用*号代替 （固话+手机隐藏前面，显示最后4位）
     */
    public static String hidePrintMobileToStar(String mobile) {
        if (!isEmpty(mobile) && mobile.length() > 4) {
            StringBuilder sb = new StringBuilder();
            String star = "*";
            int mobileLength = mobile.length();
            int starCount = mobileLength - 4;
            for (int i = 0; i < starCount; i++) {
                sb.append(star);
            }
            sb.append(mobile.substring(mobileLength - 4));
            return sb.toString();
        }
        return mobile;
    }

    /**
     * 隐藏用户名称用*号代替 （第一位显示，之后都星号隐藏）
     */
    public static String hidePrintUserNameToStar(String userName) {
        if (!isEmpty(userName) && userName.length() > 1) {
            int userNameLength = userName.length();
            StringBuilder sb = new StringBuilder();
            String star = "*";
            if (userNameLength == 1) {
                sb.append(userName);
            } else {
                int starCount = userNameLength - 1;
                sb.append(userName.charAt(0));
                for (int i = 0; i < starCount; i++) {
                    sb.append(star);
                }
            }
            return sb.toString();
        }
        return userName;
    }

    /**
     * 隐藏地址称用*号代替 （从第九位开始隐藏，小于九位的隐藏后两位）
     */
    public static String hidePrintSecretAddressToStar(String userName) {
        if (!isEmpty(userName) && userName.length() > 1) {
            int userNameLength = userName.length();
            StringBuilder sb = new StringBuilder();
            String star = "*";
            if (userNameLength <= 3) {
                sb.append(userName);
            } else if (userNameLength <= 8) {
                sb.append(userName.substring(0, userNameLength - 2));
                sb.append(star);
                sb.append(star);
            } else {
                int starCount = userNameLength - 8;
                sb.append(userName.substring(0, 8));
                for (int i = 0; i < starCount; i++) {
                    sb.append(star);
                }
            }
            return sb.toString();
        }
        return userName;
    }


    /**
     * 隐藏名字，只显示姓
     *
     * @param username
     * @return
     */
    public static String showUserName(String username) {
        if (!isEmpty(username)) {
            try {
                return subString(username, 3) + "*";
            } catch (Exception e) {
                e.printStackTrace();
                return username;
            }
        }
        return username;
    }

    public static byte[] string2Bytes(String str) {
        try {
//            TLog.d("IncodeDecodePackager", "req:" + str);
//            TLog.s("REQ", str);
            return str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str.getBytes();
        }
    }

    public static String stringFilter(String str) {
        try {
            String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……& amp;*（）——+|{}【】‘；：”“’。，、？_\"\"|-]";//要过滤掉的字符
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            return m.replaceAll("").trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String trimLeft(String str) {
        if (str != null && str.startsWith(" ")) {
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (" ".equals(Character.toString(c))) {
                    str = str.substring(i + 1, str.length());
                    trimLeft(str);
                } else {
                    break;
                }
            }
        }
        return str;
    }

    public static String list2Str(List<String> list) {
        if (null == list || list.isEmpty()) {
            return "";
        }

        StringBuilder vBuild = new StringBuilder();
        for (String str : list) {
            vBuild.append(str);
            vBuild.append(",");
        }

        if (vBuild.length() > 0) {
            vBuild.deleteCharAt(vBuild.length() - 1);
        }

        return vBuild.toString();
    }

    public static String list2Str(Set<String> list) {
        if (null == list || list.isEmpty()) {
            return "";
        }

        StringBuilder vBuild = new StringBuilder();
        for (String str : list) {
            vBuild.append(str);
            vBuild.append(",");
        }

        if (vBuild.length() > 0) {
            vBuild.deleteCharAt(vBuild.length() - 1);
        }

        return vBuild.toString();
    }

    public static String strArray2Str(String[] list) {
        if (null == list || list.length == 0) {
            return "";
        }

        StringBuilder vBuild = new StringBuilder();
        for (String str : list) {
            vBuild.append(str);
            vBuild.append(",");
        }

        if (vBuild.length() > 0) {
            vBuild.deleteCharAt(vBuild.length() - 1);
        }

        return vBuild.toString();
    }

    public static String str2StrBuilder(String errCode, String errMsg) {

        return new StringBuilder().append(errCode).append(File.separator).append(errMsg).toString();
    }


    public static boolean isContainsInArray(String str, String[] strs) {
        if (null == strs || strs.length == 0) {
            return false;
        }

        if (isEmpty(str)) {
            return false;
        }


        for (String vv : strs) {
            if (str.equals(vv)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据网点获取城市名称
     *
     * @param originCode 网点代码
     * @return
     */
    public static String getCityCodeString(String originCode) {
        if (isEmpty(originCode)
                || DEFAULT_VALUE_LOGIN_SESSION_ORIGIN_CODE.equals(originCode)) {
            return DEFAULT_VALUE_LOGIN_SESSION_ORIGIN_CODE;
        }

        String left3Chars = originCode.substring(0, 3);
        if (left3Chars.matches("[a-zA-Z]{3}")) {
            return left3Chars;
        }

        Pattern pattern = Pattern.compile("\\d{3,4}");
        Matcher matcher = pattern.matcher(originCode);
        if (matcher.find()) {
            return matcher.group();
        }
        return DEFAULT_VALUE_LOGIN_SESSION_ORIGIN_CODE;
    }

    /**
     * 将文件转字符串
     *
     * @param file
     * @return
     */
    public static String parseFileToString(Context context, String file) {
        AssetManager am = context.getAssets();
        try {
            InputStream is = am.open(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder buff = new StringBuilder();
            String command;
            while ((command = br.readLine()) != null) {
                buff.append(command);
                buff.append("\n");
            }
            br.close();
            is.close();
            return buff.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getStr(String str) {
        return !isEmpty(str) ? str : "";
    }

    public static String getHidePhone(String mobile) {
        if (!isEmpty(mobile) && isMobileNO(mobile)) {
            String mStart = mobile.substring(0, 3);
            String mReplace = mobile.substring(0, 7);
            return mobile.replace(mReplace, mStart + "****");
        }
        return mobile;
    }

    public static String formatPrice(double price) {
        DecimalFormat decimalFormat = new DecimalFormat("##.#######");
        return decimalFormat.format(price);
    }

    /**
     * Emoji表情过滤
     */
    public static final InputFilter emojiFilter = new InputFilter() {
        Pattern emoji = Pattern.compile(mEmojiChar + mSpecialChar1, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Matcher emojiMatcher = emoji.matcher(source);
            if (emojiMatcher.find()) {
                return "";
            }
            return null;
        }
    };

    public static InputFilter getInputFilter(String... expressions) {
        StringBuilder builder = new StringBuilder();
        if (expressions != null && expressions.length > 0) {
            for (int i = 0; i < expressions.length; i++) {
                builder.append(expressions[i]);
            }
        }
        final Pattern pattern = Pattern.compile(builder.toString(), Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

        InputFilter inputFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                Matcher matcher = pattern.matcher(source);
                if (matcher.find()) {
                    return "";
                }
                return null;
            }
        };
        return inputFilter;
    }

    /**
     * 判断是否为Emoji表情正则表达式
     *
     * @param string
     * @return
     */
    public static boolean isEmoji0(String string) {
        Pattern p = Pattern.compile("/[\u1F60-\u1F64]|[\u2702-\u27B0]|[\u1F68-\u1F6C]|[\u1F30-\u1F70]|[\u2600-\u26ff]/g");
        Matcher m = p.matcher(string);
        return m.matches();
    }

    /**
     * 判断是否为Emoji表情正则表达式
     *
     * @param string
     * @return
     */
    public boolean isEmoji1(String string) {
        Pattern p = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(string);
        return m.find();
    }

    /**
     * 检测是否有emoji表情
     *
     * @param source
     * @return
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { //如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是Emoji
     *
     * @param codePoint 比较的单个字符
     * @return
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) ||
                (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000)
                && (codePoint <= 0x10FFFF));
    }


    public static List<JSONArray> spliteJsonArray(JSONArray array, int max) throws JSONException {
        if (array == null)
            return Collections.emptyList();

        if (max == 1) {
            return getJsonArrayListWhenSplitByOne(array);
        }

        List<JSONArray> vList = new ArrayList<>();
        if (array.length() <= max || max <= 0) {
            vList.add(array);
            return vList;
        }

        return spliteJsonArrayOfValidParams(array, max);
    }

    @NonNull
    private static List<JSONArray> getJsonArrayListWhenSplitByOne(JSONArray array) throws JSONException {
        List<JSONArray> vList = new ArrayList<>();
        for (int index = 0; index < array.length(); ++index) {
            JSONArray array1 = new JSONArray();
            array1.put(array.get(index));
            vList.add(array1);
        }
        return vList;
    }

    private static List<JSONArray>  spliteJsonArrayOfValidParams(JSONArray array, int max) throws JSONException {
        List<JSONArray> vList = new ArrayList<>();

        JSONArray array1 = new JSONArray();
        for (int index = 0; index < array.length(); ++index) {
            if (index % max == 0) {
                if (index != 0) {
                    vList.add(array1);
                }
                array1 = new JSONArray();
            }

            array1.put(array.get(index));
        }

        if (array.length() % max != 0) {
            vList.add(array1);
        }

        return vList;
    }

    /**
     * double转成带两位小数点的字符串
     *
     * @param mD
     * @return
     */
    public static String getDoubleToStr(double mD) {
        String mStr = "0.00";
        if (mD != 0) {
            try {
                mStr = String.format("%.2f", mD);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mStr;
    }

    /**
     * 数组去除重复数据
     */
    public static List<String> getFilterList(String[] mArray) {
        List<String> mList = new ArrayList<>();
        if (null != mArray && mArray.length > 0) {
            for (int i = 0; i < mArray.length; i++) {
                if (!mList.contains(mArray[i])) {
                    mList.add(mArray[i]);
                }
            }
        }
        return mList;
    }


    /**
     * 运单号显示处理：每3个字符加空格
     *
     * @param waybill
     * @return
     */
    public static String addSpaceOfWaybill(String waybill) {
        if (waybill == null || waybill.isEmpty()) {
            return "";
        }

        StringBuilder vBuilder = new StringBuilder();
        for (int i = 0; i < waybill.length(); i++) {
            vBuilder.append(waybill.charAt(i));

            if (i % 3 == 2) {
                vBuilder.append(" ");
            }
        }

        return vBuilder.toString();
    }


    /**
     * 大陆号码或香港号码均可
     */
    public static boolean isPhoneLegal(String str)throws PatternSyntaxException {
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    public static boolean isHKPhoneLegal(String str)throws PatternSyntaxException {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}

package com.gyh.common.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

/**
 *
 * @author 作者 gyh
 * @version 创建时间：2018年6月30日 下午1:13:17
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 将含有下划线的字符串转成驼峰式命名法 (列：abc_def_ghi 转成 abcDefGhi)
     *
     * @param string
     * @return
     */
    public static String underlineCaseToUpper(String string) {
        if (StringUtils.contains(string, '_')) {
            String[] str = StringUtils.split(string, '_');
            StringBuffer sb = new StringBuffer(str[0]);
            for (int i = 1; i < str.length; i++) {
                sb.append(StringUtils.capitalize(str[i]));
            }
            return sb.toString();
        }
        return string;
    }

    /**
     * 首字母大写，列，abc_def_ghi 转成 AbcDefGhi
     *
     * @param string
     * @return
     */
    public static String initialCaseToUpper(String string) {
        if (StringUtils.contains(string, '_')) {
            String[] str = StringUtils.split(string, '_');
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < str.length; i++) {
                sb.append(StringUtils.capitalize(str[i]));
            }
            return sb.toString();
        }
        return StringUtils.capitalize(string);
    }

    /**
     * 判断传进来的字符串是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (str.length() == 0) {
            return false;
        }
        int sz = str.length();
        boolean hasExp = false;
        boolean hasDecPoint = false;
        boolean allowSigns = false;
        boolean foundDigit = false;
        // deal with any possible sign up front
        int start = (str.charAt(0) == '-') ? 1 : 0;
        if (sz > start + 1) {
            if (str.charAt(start) == '0' && str.charAt(start + 1) == 'x') {
                int i = start + 2;
                if (i == sz) {
                    return false; // str == "0x"
                }
                // checking hex (it can't be anything else)
                for (; i < str.length(); i++) {
                    char ch = str.charAt(i);
                    if ((ch < '0' || ch > '9')
                            && (ch < 'a' || ch > 'f')
                            && (ch < 'A' || ch > 'F')) {
                        return false;
                    }
                }
                return true;
            }
        }
        sz--; // don't want to loop to the last char, check it afterwords
        // for type qualifiers
        int i = start;
        // loop to the next to last char or to the last char if we need another digit to
        // make a valid number (e.g. chars[0..5] = "1234E")
        while (i < sz || (i < sz + 1 && allowSigns && !foundDigit)) {
            char ch = str.charAt(i);
            if (ch >= '0' && ch <= '9') {
                foundDigit = true;
                allowSigns = false;

            } else if (ch == '.') {
                if (hasDecPoint || hasExp) {
                    // two decimal points or dec in exponent
                    return false;
                }
                hasDecPoint = true;
            } else if (ch == 'e' || ch == 'E') {
                // we've already taken care of hex.
                if (hasExp) {
                    // two E's
                    return false;
                }
                if (!foundDigit) {
                    return false;
                }
                hasExp = true;
                allowSigns = true;
            } else if (ch == '+' || ch == '-') {
                if (!allowSigns) {
                    return false;
                }
                allowSigns = false;
                foundDigit = false; // we need a digit after the E
            } else {
                return false;
            }
            i++;
        }
        if (i < str.length()) {
            char ch = str.charAt(i);

            if (ch >= '0' && ch <= '9') {
                // no type qualifier, OK
                return true;
            }
            if (ch == 'e' || ch == 'E') {
                // can't have an E at the last byte
                return false;
            }
            if (!allowSigns
                    && (ch == 'd'
                    || ch == 'D'
                    || ch == 'f'
                    || ch == 'F')) {
                return foundDigit;
            }
            if (ch == 'l'
                    || ch == 'L') {
                // not allowing L with an exponent
                return foundDigit && !hasExp;
            }
            // last character is illegal
            return false;
        }
        // allowSigns is true iff the val ends in 'E'
        // found digit it to make sure weird stuff like '.' and '1E-' doesn't pass
        return !allowSigns && foundDigit;
    }

    /**
     * 处理 GZIP 压缩的数据
     *
     * @param str
     * @return
     * @throws IOException
     */
    public static String conventFromGzip(String str) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in;
        GZIPInputStream gunzip = null;
        in = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
        gunzip = new GZIPInputStream(in);
        byte[] buffer = new byte[256];
        int n;
        while ((n = gunzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }
        return out.toString();
    }
}
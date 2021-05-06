package com.snow.learn.util;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
  * 字符串操作 <功能详细描述>
  * 
  * @author
  * @version
  * @see [相关类/方法]
  * @since [产品/模块版本]
  */
public abstract class StringUtil {
    private static final char[] DIGITS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] DIGITS_NOCASE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public StringUtil(){

    }
    public static boolean isEmpty(String str){
        return str ==null || str.length() == 0;
    }
    public static boolean isNotEmpty(String str){
        return str !=null && str.length() > 0;
    }

    public static boolean isAllEmpty(String...strings){
        if(strings == null){
            return true;
        } else {
            String[] var1 = strings;
            int var2 = strings.length;
            for (int var3 = 0; var3 < var2; ++var3) {
                String string = var1[var3];
                if(isNotEmpty(string)){
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean isAnyEmpty(String...strings){
        if(strings == null){
            return true;
        } else {
            String[] var1 = strings;
            int var2 = strings.length;
            for (int var3 = 0; var3 < var2; ++var3) {
                String string = var1[var3];
                if(isEmpty(string)){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean isBlank(String str){
        int length;
        if(str != null && (length = str.length()) != 0){
            for (int i = 0; i < length; ++i) {
                if(!Character.isWhitespace(str.charAt(i))){
                    return false;
                }
            }
            return true;
        }else {
            return true;
        }
    }

    public static boolean isNotBlank(String str){
        int length;
        if(str != null && (length = str.length()) != 0){
            for (int i = 0; i < length; ++i) {
                if(!Character.isWhitespace(str.charAt(i))){
                    return true;
                }
            }
            return false;
        }else {
            return false;
        }
    }





    /**
     * 解决split函数的一个问题： 比如有个字符串String a =
     * "a,b,c";那么a.split(",");返回的是一个String型的数组长度为3， 若字符串a = "a,,c"
     * 执行a.split(",");返回的也是长度为3的字符串数组， 若a = "a,,"
     * 执行a.split(",");此时返回的是个长度为1的字符串数组,把后面的就给去了， 如果程序中用到了后面的字符，就会引起数组越界的错误，
     * 可以将a = "a,,"在加一个字符a = "a,,,end"，这样虽然改变了数组的长度但是不会产生数组越界的错误。
     * */
    public static String[] strSplit(String str) {

        if (StringUtil.isBlank(str)) {
            return null;
        }
        if (',' == (str.charAt(str.length() - 1))) {
            str += ",END";
        }
        return str.split(",");
    }

    public static String[] strSplit(String str, int len) {
        if (StringUtil.isBlank(str)) {
            return new String[len];
        }
        return strSplit(str);
    }
    /**
     * md5加密 StringUtil.encryptMD5()<BR>
     *
     * @param data
     * @return
     */
    public static byte[] encryptMD5(String data) throws Exception {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            bytes = md.digest(data.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new IOException(e.getMessage());
        }
        return bytes;
    }
    /**
     * 生成length位随机数
     *
     * @param length
     * @return
     */
    public static String getCharAndNumr(int length) {
        StringBuilder val = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 取得大写字母还是小写字母
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val.append((char) (choice + random.nextInt(26)));
            } else {
                // 数字
                val.append(random.nextInt(10));
            }
        }
        return val.toString();
    }
    /**
     * 去除空格
     *
     * @param str
     * @return
     */
    /*public static String trim(String str) {
        if (!StringUtils.isNotBlank(str)) {
            return null;
        } else {
            return str.replaceAll(" ", "");
        }
    }*/
    /**
     * 用户名显示规则修改为只显示首尾各一位 中间位数统一用三位“***”替换
     *
     * @param str
     * @return
     */
    public static String replaceStar(String str,int starNum) {
        int strLen = str.length();
        if (strLen == 0 || "null".equals(str.toLowerCase())) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str, 0, 1);
        for (int i = 0; i < starNum; ++i) {
            sb.append("*");
        }
        if(strLen != 2){
            sb.append(str, strLen - 1, strLen);
        }
        return sb.toString();
    }


}
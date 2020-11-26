package com.puffer.admin.common.util;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 自定义算数工具类
 *
 * @author WangYongJi
 * @date 2019/7/11 8:53
 */
public class PasswordKit {

    public static   String createPassword(String password, String salt) {
        return getMD5(password + salt);
    }

    public static String getMD5(String str) {
        String ret = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            ret = new BigInteger(1, md.digest()).toString(16);
            if (ret.length() == 31) {
                ret = "0" + ret;
            } else if (ret.length() == 30) {
                ret = "00" + ret;
            }
            return ret.toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}

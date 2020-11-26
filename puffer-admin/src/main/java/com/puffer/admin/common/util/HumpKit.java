package com.puffer.admin.common.util;

/**
 * 驼峰 下划线 互转
 *
 * @author WangYongJi
 */
public class HumpKit {
    /**
     * 测试
     */
//    public static void main(String[] args) {
//        //下划线转驼峰
//        System.out.println(_ToHump("login_time"));//loginTime
//        System.out.println(_ToHump("Login_time"));//loginTime
//        System.out.println(_ToHump("login_Time"));//loginTme
//        System.out.println(_ToHump("_login_time"));//loginTime
//        System.out.println(_ToHump("login_time_"));//loginTime
//        System.out.println(_ToHump("_login_time_"));//loginTime
//
//        System.out.println(_ToHump("login_time", true));//LoginTime
//        System.out.println(_ToHump("Login_time", true));//LoginTime
//        System.out.println(_ToHump("login_Time", true));//LoginTime
//        System.out.println(_ToHump("_login_time", true));//LoginTime
//        System.out.println(_ToHump("login_time_", true));//LoginTime
//        System.out.println(_ToHump("_login_time_", true));//LoginTime
//
//
//        //   驼峰转下划线
//        //   驼峰转下划线
//        System.out.println(humpTo_("LoginTime"));//_login_time
//        System.out.println(humpTo_("Logintime"));//_logintime
//        System.out.println(humpTo_("loginTime"));//login_time
//
//
//    }


    /**
     * 下划线转驼峰,首字母小写
     */
    public static String _ToHump(String str) {
        return _ToHump(str, false);
    }

    //可选首字母大小写
    public static String _ToHump(String str, Boolean firstUpCase) {
        if (str == null || "".equals(str)) return str;

        String[] split = str.split("_");
        if (split.length == 0) return str;

        StringBuilder sb = new StringBuilder();
        for (String content : split) {
            if (content.trim().equals("")) continue;

            //首字母大写
            String firstStr = content.substring(0, 1).toUpperCase();
            sb.append(firstStr);
            if (content.length() == 1) continue;

            //其余小写
            sb.append(content.substring(1).toLowerCase());
        }

        // 首字母小写
        if (firstUpCase == null || firstUpCase == false) {
            return sb.toString().substring(0, 1).toLowerCase() + sb.toString().substring(1);
        }
        // 首字母大写
        return sb.toString().substring(0, 1).toUpperCase() + sb.toString().substring(1);
    }

    /**
     * 驼峰转下划线
     */
    public static String humpTo_(String str) {
        if (str == null || "".equals(str)) return "";
        //截取下划线分成数组，
        char[] charArray = str.toCharArray();
        StringBuilder buffer = new StringBuilder();
        //处理字符串
        for (int i = 0, l = charArray.length; i < l; i++) {
            if (charArray[i] >= 65 && charArray[i] <= 90) {
                buffer.append("_").append(charArray[i] += 32);
            } else {
                buffer.append(charArray[i]);
            }
        }
        String s = buffer.toString();
        if (s.indexOf("_") == 0) return s.substring(1);
        return s;
    }


}

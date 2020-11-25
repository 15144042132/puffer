package com.puffer.admin.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * 自定义算数工具类
 *
 * @author WangYongJi
 * @date 2019/7/11 8:53
 */
public class MathKit {

    public static String SALT = "MathKit_SALT";

    /*** 返回26个英文字母 ***/
    public static ArrayList<String> letterArray() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            arrayList.add((char) ('a' + i) + "_");
        }
        return arrayList;
    }

    /**
     * 计算MD5
     *
     * @param str 需要计算的字符串
     * @return 大写MD5值
     */
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

    public static String getMD5Three(String path) {
        String ret = null;
        BigInteger bi = null;
        try {
            byte[] buffer = new byte[8192];
            int len = 0;
            MessageDigest md = MessageDigest.getInstance("MD5");
            File f = new File(path);
            FileInputStream fis = new FileInputStream(f);
            while ((len = fis.read(buffer)) != -1) {
                md.update(buffer, 0, len);
            }
            fis.close();
            byte[] b = md.digest();
            bi = new BigInteger(1, b);
            ret = bi.toString(16);
            if (ret.length() == 31) {
                ret = "0" + ret;
            } else if (ret.length() == 30) {
                ret = "00" + ret;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret.toUpperCase();
    }

    /**
     * 路由是否匹配
     */
    public static boolean matchPath(String pattern, String uri) {
        PathMatcher matcher = new AntPathMatcher();
        return matcher.match(pattern, uri);
    }

    public static String getSha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes(StandardCharsets.UTF_8));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * --- 长青
     * 获取无线层级路由
     *
     * @param parent_obj 根节点对象
     * @param allList    去除根节点的全部数据
     */
    private static void getChildrenC(JSONObject parent_obj, JSONArray allList) {
        JSONArray childred_list = new JSONArray();
        for (int i = 0; i < allList.size(); i++) {
            JSONObject now_obj = (JSONObject) allList.get(i);
            String now_pid = now_obj.getString("pid");
            //查得到
            if (now_pid.equals(parent_obj.getString("id"))) {
                childred_list.add(now_obj);
                getChildrenC(now_obj, allList);
            }
        }
        if (childred_list.size() != 0) {
            parent_obj.put("children", childred_list);
        }
    }

    /**
     * --- 桎梏
     * 获取无线层级路由
     *
     * @param parent_obj 根节点对象
     * @param allList    去除根节点的全部数据
     */
    private static JSONArray getChildrenW(JSONObject parent_obj, JSONArray allList) {
        JSONArray childred_list = new JSONArray();
        for (int i = 0; i < allList.size(); i++) {
            JSONObject now_obj = (JSONObject) allList.get(i);
            String now_pid = now_obj.getString("pid");
            //查得到
            if (now_pid.equals(parent_obj.getString("id"))) {
                childred_list.add(now_obj); // 同级别添加
                getChildrenW(now_obj, allList);//继续递归
            }
            //非同级别不作处理
            else {

            }
        }
        if (childred_list.size() != 0) {
            parent_obj.put("children", childred_list);
        }
        return childred_list;
    }

    /**
     * 测试无线层级路由
     * 启动main方法调用即可
     */
//    public static void testGetChildren() {
//        JSONArray allList = getTestChildrenPara();
//        //
//        JSONObject parent_obj = new JSONObject();
//        parent_obj.put("name", "2");
//        parent_obj.put("pid", "0");
//        parent_obj.put("id", "2");
//        getChildrenC(parent_obj, allList);
//        //
//        //
//        //
//        parent_obj = new JSONObject();
//        parent_obj.put("name", "2");
//        parent_obj.put("pid", "0");
//        parent_obj.put("id", "2");
//        JSONArray children_w = getChildrenW(parent_obj, allList);
//    }

    //
//    private static JSONArray getTestChildrenPara() {
//        JSONArray jsonArray = new JSONArray();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("id", "1");
//        jsonObject.put("pid", "0");
//        jsonObject.put("name", "name_1");
//        jsonArray.add(jsonObject);
//        jsonObject = new JSONObject();
//        jsonObject.put("id", "2");
//        jsonObject.put("pid", "0");
//        jsonObject.put("name", "name_2");
//        jsonArray.add(jsonObject);
//        jsonObject = new JSONObject();
//        jsonObject.put("id", "3");
//        jsonObject.put("pid", "1");
//        jsonObject.put("name", "name_3");
//        jsonArray.add(jsonObject);
//        jsonObject = new JSONObject();
//        jsonObject.put("id", "4");
//        jsonObject.put("pid", "3");
//        jsonObject.put("name", "name_4");
//        jsonArray.add(jsonObject);
//        jsonObject = new JSONObject();
//        jsonObject.put("id", "5");
//        jsonObject.put("pid", "2");
//        jsonObject.put("name", "name_5");
//        jsonArray.add(jsonObject);
//        jsonObject = new JSONObject();
//        jsonObject.put("id", "6");
//        jsonObject.put("pid", "5");
//        jsonObject.put("name", "name_6");
//        jsonArray.add(jsonObject);
//        jsonObject = new JSONObject();
//        jsonObject.put("id", "7");
//        jsonObject.put("pid", "6");
//        jsonObject.put("name", "name_7");
//        jsonArray.add(jsonObject);
//
//
//        jsonObject = new JSONObject();
//        jsonObject.put("id", "1000");
//        jsonObject.put("pid", "0");
//        jsonObject.put("name", "name_1000");
//        jsonArray.add(jsonObject);
//
//        jsonObject = new JSONObject();
//        jsonObject.put("id", "1001");
//        jsonObject.put("pid", "2");
//        jsonObject.put("name", "name_1001");
//        jsonArray.add(jsonObject);
//
//        return jsonArray;
//    }

    /**
     * 截取随机数 0-10000000
     *
     * @param length 长度 1-10
     */
    public static String random(int length) {
        String random = Math.random() + "";
        return random.substring(2, 2 + length);
    }


    private static char[] ops = new char[]{'+', '-', '*'};

    /**
     * 简单计算 用于生成验证码
     *
     * @param exp "1+3-8"
     * @return -4
     */
    public static int calculate(String exp) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            return (Integer) engine.eval(exp);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 生成随机的数学公式
     * + - *
     *
     * @return "1-2*3 ?"
     */
    public static String createMathFormula() {
        Random random = new Random();
        int num1 = random.nextInt(10);
        int num2 = random.nextInt(10);
        int num3 = random.nextInt(10);
        char op1 = ops[random.nextInt(3)];
        char op2 = ops[random.nextInt(3)];
        return ("" + num1 + op1 + num2 + op2 + num3);
    }


    /**
     * 两个经纬度之间的距离
     * <p>
     * 参数 ： 经度1 纬度1 经度2 纬度2
     */
    private static String distance(double lat1, double lng1,
                                   double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);

        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        return new DecimalFormat("0.000").format(s);
    }

    /**
     * 返回两个经纬度之间的米数
     */
    public static Long getDistanceMi(Object lat1, Object lng1,
                                     Object lat2, Object lng2) {
        return Long.parseLong(distance(
                Double.parseDouble(lat1 + ""),
                Double.parseDouble(lng1 + ""),
                Double.parseDouble(lat2 + ""),
                Double.parseDouble(lng2 + ""))
        ) * 1000;
    }

    private static final double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

}

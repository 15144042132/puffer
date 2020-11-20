package com.sting.security.rbac;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.sting.core.spring.ContextKit;
import com.sting.security.rbac.config.SecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 凭证签发工具
 *
 * @author WangYongJi
 */
@Slf4j
@Component
public class JwtKit {
    //签名算法
    private static Algorithm algorithm256 = null;
    //签名验证工具
    private static JWTVerifier jwtVerifier = null;

    /**
     * 生成Token
     *
     * @param parameter 携带参数
     * @param hour      失效时间(小时)
     * @return 签名后的字符串
     */
    public static String createToken(Map<String, Object> parameter, Object hour) {
        //默认两小时
        if (hour == null) {
            hour = 2;
        }
        long time = Long.parseLong(hour.toString()) * 60 * 60 * 1000;

        Date withExpiresAt = new Date(new Date().getTime() + time);
        if (parameter == null) {
            parameter = new HashMap<>(1);
        }
        return JWT.create()
                //可读取的参数信息（别存太多）
                .withClaim("parameter", JSON.toJSONString(parameter))
                //超时时间
                .withExpiresAt(withExpiresAt)
                //signature
                .sign(getAlgorithm256());
    }

    //检查失效
    public static boolean check(Object token) {
        if (ObjectUtils.isEmpty(token)) return false;
        try {
            getJwtVerifier().verify(token.toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取整个参数对象
     */
    public static JSONObject getParam(String token) {
        return JSON.parseObject(getClaims(token).get("parameter").asString());
    }

    /**
     * 获取指定 Key 的 Value
     */
    public static Object getParam(String token, String key) {
        return JSON.parseObject(getClaims(token).get("parameter").asString()).get(key);
    }

    //获取用户ID
    public static String getUserId(String token) {
        Object userId = getParam(token, "userId");
        return userId.toString();
    }

    //获取用户账号
    public static String getUserAccount(String token) {
        Object userId = getParam(token, "userAccount");
        return userId.toString();
    }

    //创建时间
    public static String getCreateTime(String token) {
        return getParam(token, "createTime").toString();
    }

    //获取用户ID
    public static List<Object> getRoleIds(String token) {
        return (List<Object>) getParam(token, "roleIds");
    }

    //获取用户登录IP
    public static String getLoginIp(String token) {
        return getParam(token, "loginIp").toString();
    }

    //获取Claims
    private static Map<String, Claim> getClaims(String token) {
        return getJwtVerifier().verify(token).getClaims();
    }

    private static Algorithm getAlgorithm256() {
        if (algorithm256 != null) {
            synchronized (Object.class) {
                algorithm256 = Algorithm.HMAC256(ContextKit.getBean(SecurityConfig.class).systemSecret().getValue());
            }
        }
        return algorithm256;
    }

    private static JWTVerifier getJwtVerifier() {
        if (jwtVerifier == null) {
            synchronized (Object.class) {
                jwtVerifier = JWT.require(getAlgorithm256()).build();
            }
        }
        return jwtVerifier;
    }


}
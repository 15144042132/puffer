package com.puffer.admin.common.util;

import com.sting.security.rbac.JwtKit;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BaseService {

    /**
     * 当前登录用户ID
     */
    default String userId() {
        return JwtKit.getUserId(token());
    }

    /**
     * 当前登录用户角色
     */
    default List<Object> roleIds() {
        return JwtKit.getRoleIds(token());
    }


    default String token() {
        HttpServletRequest request = RequestKit.request();
        return request.getHeader("token");
    }

//
//    /**
//     * 根据 MD5(明文账号+明文密码)，返回加密后的字符串
//     *
//     * @param accountAndPasswordToMd5 MD5(明文账号+明文密码)
//     * @return 数据库中所存字符串
//     */
//    default String getPassword(String accountAndPasswordToMd5) {
//        String str = accountAndPasswordToMd5.toUpperCase() + dictService().getValue("system_server_salt", "u123whuhdu1sag22yfw333oqi55hjasd");
//        return MathKit.getMD5(str);
//    }
//
//    /**
//     * 根据明文账号密码，返回加密后的字符串
//     *
//     * @param accountPlainText  明文账号
//     * @param passwordPlainText 明文密码
//     * @return 数据库中所存字符串
//     */
//    default String getPassword(String accountPlainText, String passwordPlainText) {
//        return getPassword(MathKit.getMD5(accountPlainText + passwordPlainText));
//    }
//
//
//    default DictService dictService() {
//        return ContextKit.getBean(DictService.class);
//    }

}

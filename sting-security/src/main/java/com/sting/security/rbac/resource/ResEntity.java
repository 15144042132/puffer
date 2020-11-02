package com.sting.security.rbac.resource;

import lombok.Data;

/**
 * 资源实体
 *
 * @author 王永吉
 */
@Data
public class ResEntity {
    //地址
    String url = "";
    //资源名
    String name = "";
    //父级
    String parentName = "";
}
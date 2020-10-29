package com.sting.security.rbac;

import com.alibaba.fastjson.JSON;
import com.sting.core.spring.EnvKit;
import com.sting.db.dao.StDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 资源处理器
 */
@Configuration
@RestControllerAdvice
public class ResourceHandler implements ApplicationContextAware {
    @Autowired
    private StDao dao;
    @Autowired
    private ResourceHandler handler;

    private ApplicationContext context;
    private ArrayList<ResEntity> cList = null;
    private ArrayList<ResEntity> mList = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        handler.intiRes();
        handler.createTable();
        handler.refreshResource();

        System.out.println("  ");
        System.out.println(JSON.toJSONString(cList));
        System.out.println("  ");
        System.out.println(JSON.toJSONString(mList));
        System.out.println("  ");
    }

    //扫描所有资源
    private synchronized void intiRes() {
        ArrayList<ResEntity> moduleList = new ArrayList<>();
        ArrayList<ResEntity> resList = new ArrayList<>();
        Collection<Object> values = context.getBeansWithAnnotation(ResC.class).values();
        for (Object bean : values) {
            Class<?> aClass = bean.getClass();
            ResC resC = AnnotationUtils.findAnnotation(aClass, ResC.class);
            RequestMapping controllerMapping = AnnotationUtils.findAnnotation(aClass, RequestMapping.class);
            if (resC == null || controllerMapping == null) continue;

            //遍历所有资源
            for (String controllerValue : controllerMapping.value()) {
                //class
                String controllerPath = EnvKit.contextPath() + controllerValue;
                ResEntity moduleEntity = new ResEntity();
                moduleEntity.setName(resC.value());
                moduleEntity.setParentName(resC.parent());
                if (StringUtils.isBlank(resC.parent())) {
                    moduleEntity.setParentName(resC.value());
                }
                moduleEntity.setUrl(controllerPath);
                moduleList.add(moduleEntity);

                //method
                Method[] declaredMethods = aClass.getDeclaredMethods();
                for (Method method : declaredMethods) {
                    RequestMapping requestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
                    ResM resMethod = AnnotationUtils.findAnnotation(method, ResM.class);
                    if (requestMapping == null || resMethod == null) continue;

                    //遍历所有资源
                    for (String methodValue : requestMapping.value()) {
                        String methodPath = controllerPath + methodValue;
                        ResEntity resEntity = new ResEntity();
                        resEntity.setUrl(methodPath);
                        resEntity.setName(resMethod.value());
                        resEntity.setParentName(resC.value());
                        resList.add(resEntity);
                    }
                }
            }
        }
        cList = moduleList;
        mList = resList;
    }

    //建表
    private synchronized void createTable() {
        dao.insert(sys_role);
        dao.insert(sys_user);
        dao.insert(sys_resource);
        dao.insert(sys_link_role_user);
        dao.insert(sys_link_role_resource);
    }

    //更新数据库
    private synchronized void refreshResource() {

    }


    private static final String sys_link_role_resource = "CREATE TABLE IF NOT EXISTS `sys_link_role_resource`  (\n" +
            "  `role_id` bigint UNSIGNED NOT NULL,\n" +
            "  `resource_id` bigint UNSIGNED NOT NULL,\n" +
            "  PRIMARY KEY (`role_id`, `resource_id`) USING BTREE,\n" +
            "  UNIQUE INDEX `role_id`(`role_id`, `resource_id`) USING BTREE COMMENT '联合唯一索引'\n" +
            ") ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '关联表（角色-资源）' ROW_FORMAT = COMPACT;\n";

    private static final String sys_link_role_user = "CREATE TABLE IF NOT EXISTS `sys_link_role_user`  (\n" +
            "  `role_id` bigint UNSIGNED NOT NULL,\n" +
            "  `user_id` bigint UNSIGNED NOT NULL,\n" +
            "  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,\n" +
            "  UNIQUE INDEX `menu_id`(`user_id`, `role_id`) USING BTREE COMMENT '联合唯一索引'\n" +
            ") ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '关联表（角色-菜单）' ROW_FORMAT = COMPACT;\n";

    private static final String sys_resource = "CREATE TABLE IF NOT EXISTS `sys_resource`  (\n" +
            "  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',\n" +
            "  `pid` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT 'pid',\n" +
            "  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',\n" +
            "  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源地址',\n" +
            "  `sort` int UNSIGNED NULL DEFAULT 100 COMMENT '排序(默认=100)',\n" +
            "  `create_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据创建者',\n" +
            "  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '数据创建时间',\n" +
            "  `update_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据修改者',\n" +
            "  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '数据更新时间',\n" +
            "  `is_use` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否可用(1=可用 , 2=不可用)',\n" +
            "  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '2' COMMENT '逻辑删除(1=已删除 , 2=未删除)',\n" +
            "  PRIMARY KEY (`id`) USING BTREE,\n" +
            "  UNIQUE INDEX `url`(`url`) USING BTREE COMMENT '资源唯一索引'\n" +
            ") ENGINE = InnoDB AUTO_INCREMENT = 304 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源表' ROW_FORMAT = COMPACT;\n";

    private static final String sys_role = "CREATE TABLE IF NOT EXISTS `sys_role`  (\n" +
            "  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',\n" +
            "  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',\n" +
            "  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一标记，用作见名知意(不可重复，不可修改)',\n" +
            "  `detail` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',\n" +
            "  `sort` int UNSIGNED NULL DEFAULT 100 COMMENT '排序(默认=100)',\n" +
            "  `create_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据创建者',\n" +
            "  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '数据创建时间',\n" +
            "  `update_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据修改者',\n" +
            "  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '数据更新时间',\n" +
            "  `is_use` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否可用(1=可用 , 2=不可用)',\n" +
            "  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '2' COMMENT '逻辑删除(1=已删除 , 2=未删除)',\n" +
            "  PRIMARY KEY (`id`) USING BTREE,\n" +
            "  UNIQUE INDEX `name`(`name`) USING BTREE COMMENT '角色名唯一',\n" +
            "  UNIQUE INDEX `code`(`code`) USING BTREE COMMENT '角色code唯一'\n" +
            ") ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = COMPACT;\n";

    private static final String sys_user = "CREATE TABLE IF NOT EXISTS `sys_user`  (\n" +
            "  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',\n" +
            "  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',\n" +
            "  `account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',\n" +
            "  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',\n" +
            "  `head` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',\n" +
            "  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',\n" +
            "  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '座机号',\n" +
            "  `id_card` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',\n" +
            "  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',\n" +
            "  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭住址',\n" +
            "  `states` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '用户状态（1=正常，2=因为违规已停用）',\n" +
            "  `login_ip` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后一次登录IP',\n" +
            "  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次登录时间',\n" +
            "  `refresh_token` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '刷新Token',\n" +
            "  `sort` int UNSIGNED NULL DEFAULT 100 COMMENT '排序(默认=100)',\n" +
            "  `create_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据创建者',\n" +
            "  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '数据创建时间',\n" +
            "  `update_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据修改者',\n" +
            "  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '数据更新时间',\n" +
            "  `is_use` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否可用(1=可用 , 2=不可用)',\n" +
            "  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '2' COMMENT '逻辑删除(1=已删除 , 2=未删除)',\n" +
            "  PRIMARY KEY (`id`) USING BTREE,\n" +
            "  UNIQUE INDEX `account`(`account`) USING BTREE COMMENT '账号唯一',\n" +
            "  UNIQUE INDEX `phone`(`phone`) USING BTREE COMMENT '手机号唯一',\n" +
            "  UNIQUE INDEX `emaile`(`email`) USING BTREE COMMENT '邮箱唯一'\n" +
            ") ENGINE = InnoDB AUTO_INCREMENT = 137 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = COMPACT;\n";


}

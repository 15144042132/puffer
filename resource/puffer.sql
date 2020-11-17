/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 127.0.0.1:3306
 Source Schema         : puffer

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 17/11/2020 15:06:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for 0_template
-- ----------------------------
DROP TABLE IF EXISTS `0_template`;
CREATE TABLE `0_template`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `detail` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `sort` int UNSIGNED NULL DEFAULT 100 COMMENT '排序(默认=100)',
  `create_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '数据创建时间',
  `update_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据修改者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '数据更新时间',
  `is_use` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否可用(1=可用 , 2=不可用)',
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '2' COMMENT '逻辑删除(1=已删除 , 2=未删除)',
  `version` bigint UNSIGNED NULL DEFAULT 0 COMMENT '版本号（预留）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '一张模板表-新建表时，复制这张表然后改名' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 0_template
-- ----------------------------

-- ----------------------------
-- Table structure for sys_link_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_link_role_resource`;
CREATE TABLE `sys_link_role_resource`  (
  `role_id` bigint UNSIGNED NOT NULL,
  `resource_id` bigint UNSIGNED NOT NULL,
  PRIMARY KEY (`role_id`, `resource_id`) USING BTREE,
  UNIQUE INDEX `role_id`(`role_id`, `resource_id`) USING BTREE COMMENT '联合唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '关联表（角色-资源）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_link_role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for sys_link_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_link_role_user`;
CREATE TABLE `sys_link_role_user`  (
  `role_id` bigint UNSIGNED NOT NULL,
  `user_id` bigint UNSIGNED NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  UNIQUE INDEX `menu_id`(`user_id`, `role_id`) USING BTREE COMMENT '联合唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '关联表（角色-菜单）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_link_role_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `pid` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT 'pid',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源地址',
  `sort` int UNSIGNED NULL DEFAULT 100 COMMENT '排序(默认=100)',
  `create_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '数据创建时间',
  `update_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据修改者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '数据更新时间',
  `is_use` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否可用(1=可用 , 2=不可用)',
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '2' COMMENT '逻辑删除(1=已删除 , 2=未删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `url`(`url`) USING BTREE COMMENT '资源唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES (1, 0, '资源管理', '/resource', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (2, 0, '角色管理', '/role', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (3, 0, '系统管理', '/system', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (4, 0, '用户管理', '/user', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (5, 1, '读取资源', '/resource/read', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (6, 2, '删除角色', '/role/delete', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (7, 2, '添加角色', '/role/insert', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (8, 3, '刷新缓存', '/system/refreshCache', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (9, 4, '删除用户', '/user/delete', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (10, 4, '添加用户', '/user/insert', 100, 0, NULL, 0, NULL, '1', '2');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一标记，用作见名知意(不可重复，不可修改)',
  `detail` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `sort` int UNSIGNED NULL DEFAULT 100 COMMENT '排序(默认=100)',
  `create_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '数据创建时间',
  `update_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据修改者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '数据更新时间',
  `is_use` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否可用(1=可用 , 2=不可用)',
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '2' COMMENT '逻辑删除(1=已删除 , 2=未删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE COMMENT '角色名唯一',
  UNIQUE INDEX `code`(`code`) USING BTREE COMMENT '角色code唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_security_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_security_config`;
CREATE TABLE `sys_security_config`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '配置名称',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '键',
  `value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '值',
  `des` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `sort` int UNSIGNED NULL DEFAULT 100 COMMENT '排序(默认=100)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_security_config
-- ----------------------------
INSERT INTO `sys_security_config` VALUES (1, 'ID<1000为框架保留配置，不可使用', 'zero', 'zero', NULL, 100);
INSERT INTO `sys_security_config` VALUES (2, '系统秘钥(可以考虑定期更换)', 'system_secret', '67c7beb18fafd77f1319739fa683bc5e', NULL, 100);
INSERT INTO `sys_security_config` VALUES (3, '登录时，允许错误输入密码最大次数', 'login_error_input_max_count', '10', NULL, 100);
INSERT INTO `sys_security_config` VALUES (4, '初始化状态（1=已完成，2=项目重启后执行初始化）', 'init_status', '1', NULL, 100);
INSERT INTO `sys_security_config` VALUES (5, 'root账号，项目首次启动后，必须修改', 'root_account', 'root', NULL, 100);
INSERT INTO `sys_security_config` VALUES (6, 'root密码，项目首次启动后，必须修改', 'root_password', '????????', NULL, 100);
INSERT INTO `sys_security_config` VALUES (7, '请求访问来源控制', 'access_control_allow_origin', '*', NULL, 100);
INSERT INTO `sys_security_config` VALUES (8, '请求头控制', 'access_control_allow_headers', 'authorization,Authorization,Token,token,Auth,DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type', NULL, 100);
INSERT INTO `sys_security_config` VALUES (9, '请求允许方法', 'access_control_allow_methods', 'POST,GET,OPTIONS,DELETE', NULL, 100);
INSERT INTO `sys_security_config` VALUES (10, '请求最大连接时长', 'access_control_max_age', '3600', NULL, 100);
INSERT INTO `sys_security_config` VALUES (12, '请求的响应是否允许暴露给页面', 'access_control_allow_credentials', 'true', NULL, 100);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `head` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '座机号',
  `id_card` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭住址',
  `states` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '用户状态（1=正常，2=因为违规已停用）',
  `login_ip` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后一次登录IP',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次登录时间',
  `refresh_token` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '刷新Token',
  `sort` int UNSIGNED NULL DEFAULT 100 COMMENT '排序(默认=100)',
  `create_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '数据创建时间',
  `update_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据修改者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '数据更新时间',
  `is_use` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否可用(1=可用 , 2=不可用)',
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '2' COMMENT '逻辑删除(1=已删除 , 2=未删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `account`(`account`) USING BTREE COMMENT '账号唯一',
  UNIQUE INDEX `phone`(`phone`) USING BTREE COMMENT '手机号唯一',
  UNIQUE INDEX `emaile`(`email`) USING BTREE COMMENT '邮箱唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;

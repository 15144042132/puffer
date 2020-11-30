/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 127.0.0.1:3306
 Source Schema         : puffer

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 30/11/2020 15:03:06
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '一张模板表-新建表时，复制这张表然后改名' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 0_template
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '配置名称',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '键',
  `value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '值',
  `des` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `sort` int UNSIGNED NULL DEFAULT 100 COMMENT '排序(默认=100)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '安全配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '初始化状态（1=已完成，2=项目重启后执行初始化）', 'init_status', '1', NULL, 100);
INSERT INTO `sys_config` VALUES (2, '系统秘钥(可以考虑定期更换)', 'security_system_secret', '67c7beb18fafd77f1319739fa683bc5e', NULL, 100);
INSERT INTO `sys_config` VALUES (3, '登录时，允许错误输入密码最大次数', 'security_login_error_input_max_count', '999', NULL, 100);
INSERT INTO `sys_config` VALUES (5, 'root账号，项目首次启动后，必须修改', 'security_root_account_is_create', 'true', NULL, 100);
INSERT INTO `sys_config` VALUES (7, '请求访问来源控制', 'security_access_control_allow_origin', '*', NULL, 100);
INSERT INTO `sys_config` VALUES (8, '请求头控制', 'security_access_control_allow_headers', 'authorization,Authorization,Token,token,Auth,DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type', NULL, 100);
INSERT INTO `sys_config` VALUES (9, '请求允许方法', 'security_access_control_allow_methods', 'POST,GET,OPTIONS,DELETE', NULL, 100);
INSERT INTO `sys_config` VALUES (10, '请求最大连接时长', 'security_access_control_max_age', '3600', NULL, 100);
INSERT INTO `sys_config` VALUES (12, '请求的响应是否允许暴露给页面', 'security_access_control_allow_credentials', 'true', NULL, 100);
INSERT INTO `sys_config` VALUES (13, '开放路径', 'security_public_url', '/**/static/**,/**/public/**', NULL, 100);
INSERT INTO `sys_config` VALUES (14, '权限检查是否开启', 'security_rbac_is_open', 'true', NULL, 100);
INSERT INTO `sys_config` VALUES (15, '单一登录是否开启', 'security_single_login_is_open', 'false', NULL, 100);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '键',
  `value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '值',
  `des` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `sort` int UNSIGNED NULL DEFAULT 100 COMMENT '排序(默认=100)',
  `create_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '数据创建时间',
  `update_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据修改者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '数据更新时间',
  `is_use` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否可用(1=可用 , 2=不可用)',
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '2' COMMENT '逻辑删除(1=已删除 , 2=未删除)',
  `version` bigint UNSIGNED NULL DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 300023 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, '请用下划线分隔', 'c_o_d_e', 'Value', NULL, 100, 0, NULL, 0, '2020-04-08 11:09:12', '1', '2', 0);
INSERT INTO `sys_dict` VALUES (10010, '文件上传地址-Linux', 'upload_file_path_linux', '/opt/gx_statistics/upload_file/', NULL, 100, 0, NULL, 0, '2020-04-11 16:17:40', '1', '2', 0);
INSERT INTO `sys_dict` VALUES (10011, '文件上传地址-windows', 'upload_file_path_windows', 'D://upload_file/', NULL, 100, 0, NULL, 0, '2020-04-11 16:17:40', '1', '2', 0);

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
INSERT INTO `sys_link_role_resource` VALUES (1, 14);
INSERT INTO `sys_link_role_resource` VALUES (1, 15);
INSERT INTO `sys_link_role_resource` VALUES (1, 16);
INSERT INTO `sys_link_role_resource` VALUES (1, 17);
INSERT INTO `sys_link_role_resource` VALUES (1, 18);
INSERT INTO `sys_link_role_resource` VALUES (1, 19);
INSERT INTO `sys_link_role_resource` VALUES (1, 20);
INSERT INTO `sys_link_role_resource` VALUES (1, 21);
INSERT INTO `sys_link_role_resource` VALUES (1, 22);
INSERT INTO `sys_link_role_resource` VALUES (1, 23);
INSERT INTO `sys_link_role_resource` VALUES (1, 24);
INSERT INTO `sys_link_role_resource` VALUES (1, 25);
INSERT INTO `sys_link_role_resource` VALUES (1, 26);
INSERT INTO `sys_link_role_resource` VALUES (1, 27);
INSERT INTO `sys_link_role_resource` VALUES (1, 28);
INSERT INTO `sys_link_role_resource` VALUES (1, 29);
INSERT INTO `sys_link_role_resource` VALUES (1, 30);
INSERT INTO `sys_link_role_resource` VALUES (1, 31);
INSERT INTO `sys_link_role_resource` VALUES (1, 32);
INSERT INTO `sys_link_role_resource` VALUES (1, 33);
INSERT INTO `sys_link_role_resource` VALUES (1, 34);
INSERT INTO `sys_link_role_resource` VALUES (1, 35);
INSERT INTO `sys_link_role_resource` VALUES (1, 36);
INSERT INTO `sys_link_role_resource` VALUES (1, 37);
INSERT INTO `sys_link_role_resource` VALUES (1, 38);
INSERT INTO `sys_link_role_resource` VALUES (1, 39);
INSERT INTO `sys_link_role_resource` VALUES (1, 40);
INSERT INTO `sys_link_role_resource` VALUES (1, 41);
INSERT INTO `sys_link_role_resource` VALUES (1, 42);
INSERT INTO `sys_link_role_resource` VALUES (1, 43);
INSERT INTO `sys_link_role_resource` VALUES (1, 44);
INSERT INTO `sys_link_role_resource` VALUES (1, 45);
INSERT INTO `sys_link_role_resource` VALUES (1, 46);
INSERT INTO `sys_link_role_resource` VALUES (1, 47);
INSERT INTO `sys_link_role_resource` VALUES (1, 48);
INSERT INTO `sys_link_role_resource` VALUES (1, 50);
INSERT INTO `sys_link_role_resource` VALUES (1, 51);
INSERT INTO `sys_link_role_resource` VALUES (1, 52);
INSERT INTO `sys_link_role_resource` VALUES (1, 53);

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
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES (14, 0, '字典管理', '/system/dict', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (15, 0, '菜单管理', '/system/menu', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (16, 0, '资源管理', '/system/resource', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (17, 0, '角色管理', '/system/role', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (18, 0, '用户管理', '/system/user', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (19, 14, '修改字典', '/system/dict/update', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (20, 14, '删除字典', '/system/dict/delete', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (21, 14, '字典列表查询', '/system/dict/list', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (22, 14, '添加字典', '/system/dict/insert', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (23, 14, '字典详情查询', '/system/dict/info', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (24, 14, '字典分页查询', '/system/dict/page', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (25, 15, '修改菜单', '/system/menu/update', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (26, 15, '删除菜单', '/system/menu/delete', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (27, 15, '菜单列表', '/system/menu/list', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (28, 15, '添加菜单', '/system/menu/insert', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (29, 15, '菜单详情', '/system/menu/info', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (30, 15, '菜单分页查询', '/system/menu/page', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (31, 16, '修改资源', '/system/resource/update', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (32, 16, '删除资源', '/system/resource/delete', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (33, 16, '资源列表查询', '/system/resource/list', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (34, 16, '添加资源', '/system/resource/insert', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (35, 16, '资源详情查询', '/system/resource/info', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (36, 16, '资源分页查询', '/system/resource/page', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (37, 17, '修改角色', '/system/role/update', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (38, 17, '删除角色', '/system/role/delete', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (39, 17, '角色列表查询', '/system/role/list', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (40, 17, '添加觉散热', '/system/role/insert', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (41, 17, '角色详情查询', '/system/role/info', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (42, 17, '角色分页查询', '/system/role/page', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (43, 18, '修改用户', '/system/user/update', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (44, 18, '删除用户', '/system/user/delete', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (45, 18, '用户列表查询', '/system/user/list', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (46, 18, '添加用户', '/system/user/insert', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (47, 18, '用户详情查询', '/system/user/info', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (48, 18, '字用户分页查询', '/system/user/page', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (50, 18, '退出登录', '/system/user/public/logout', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (51, 18, '锁定用户', '/system/user/lockUser', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (52, 18, '用户登录', '/system/user/public/login', 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_resource` VALUES (53, 18, '添加root账户', '/system/user/public/insertRoot', 100, 0, NULL, 0, NULL, '1', '2');

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
INSERT INTO `sys_role` VALUES (1, 'ROOT', 'root', NULL, 100, 0, NULL, 0, NULL, '1', '2');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '随机盐',
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, NULL, '1234$$$qwer', 'F149C44223E3EAB743AD79BF0E5FED3B', '707B4A52ED3803AD69DD2906E3FCCB60', NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 100, 0, '2020-11-30 14:15:55', 0, '2020-11-30 14:15:55', '1', '2');

SET FOREIGN_KEY_CHECKS = 1;

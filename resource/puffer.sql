/*
 Navicat Premium Data Transfer

 Source Server         : 本地虚拟机-192.168.244.129-root-1234qwer
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 192.168.244.129:3306
 Source Schema         : puffer

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 13/10/2020 10:59:47
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '一张模板表-新建表时，复制这张表然后改名' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of 0_template
-- ----------------------------

-- ----------------------------
-- Table structure for company_info
-- ----------------------------
DROP TABLE IF EXISTS `company_info`;
CREATE TABLE `company_info`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` bigint NOT NULL DEFAULT 0 COMMENT '用户ID',
  `user_account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业账号',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业名称',
  `industry_id` bigint NULL DEFAULT NULL COMMENT '所属行业',
  `company_scale` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业规模-单选',
  `unit_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业性质-复选',
  `company_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '公司简介',
  `company_images` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '公司简介图片-两张',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业地址',
  `telephone` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '固定电话',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号1',
  `phone2` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号2',
  `contact_people1` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人1',
  `contact_people2` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人2',
  `contact_people1_job` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人1职务',
  `contact_people2_job` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人2职务',
  `business_license` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '营业执照',
  `sort` int UNSIGNED NULL DEFAULT 100 COMMENT '排序(默认=100)',
  `create_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '数据创建时间',
  `update_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据修改者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '数据更新时间',
  `is_use` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否可用(1=可用 , 2=不可用)',
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '2' COMMENT '逻辑删除(1=已删除 , 2=未删除)',
  `version` bigint UNSIGNED NULL DEFAULT 0 COMMENT '版本号（预留）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id`) USING BTREE COMMENT '一个账号一个企业',
  UNIQUE INDEX `user_account`(`user_account`) USING BTREE COMMENT '账号不能重复',
  UNIQUE INDEX `name`(`name`) USING BTREE COMMENT '企业名不能重复'
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业详情' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of company_info
-- ----------------------------
INSERT INTO `company_info` VALUES (1, 47, 'admin001', '吉林省科英激光股份有限公司', 80, '50-150人', '国企', '<p>&nbsp; &nbsp; &nbsp; 企业通过了<span style=\"font-size: 18pt;\">ISO9001国际质量体系认证</span>及ISO13485医疗器械国际体系认证。科英激光公司生产的所有产品均已经通过CFDA审批，企业的部分产品已经通过了ROHS和CE认证。</p>\n<p>&nbsp; &nbsp; &nbsp; 企业通过了<span style=\"font-size: 18pt;\">ISO9001国际质量体系认证</span>及ISO13485医疗器械国际体系认证。科英激光公司生产的所有产品均已经通过CFDA审批，企业的部分产品已经通过了ROHS和CE认证。</p>\n<p>&nbsp; &nbsp; &nbsp; 企业通过了<span style=\"font-size: 18pt;\">ISO9001国际质量体系认证</span>及ISO13485医疗器械国际体系认证。科英激光公司生产的所有产品均已经通过CFDA审批，企业的部分产品已经通过了ROHS和CE认证。</p>\n<p>&nbsp; &nbsp; &nbsp;</p>', '[{\"id\":\"5\",\"name\":\"营业执照.jpg\",\"url\":\"http://101.200.45.31:9001/static/image/D2F22718D343FFB0F692DAEE24B4DDB9.jpg\",\"suffix\":\"jpg\",\"size\":\"166735\",\"md5\":\"D2F22718D343FFB0F692DAEE24B4DDB9\",\"type\":\"image\",\"path\":null,\"sort\":null,\"createUid\":\"47\",\"createTime\":\"2020-05-12 09:23:34\",\"updateUid\":\"47\",\"updateTime\":\"2020-05-12 09:23:34\",\"isUse\":null,\"isDelete\":null,\"uid\":1589246613551,\"status\":\"success\"},{\"id\":\"22\",\"name\":\"_壁纸.jpg\",\"url\":\"http://101.200.45.31:9001/static/image/0A16EDD7C86E736D1CC1FD5D73A55C1B.jpg\",\"suffix\":\"jpg\",\"size\":\"12995\",\"md5\":\"0A16EDD7C86E736D1CC1FD5D73A55C1B\",\"type\":\"image\",\"path\":null,\"sort\":null,\"createUid\":\"1\",\"createTime\":\"2020-05-14 08:34:58\",\"updateUid\":\"1\",\"updateTime\":\"2020-05-14 08:34:58\",\"isUse\":null,\"isDelete\":null,\"uid\":1589416497868,\"status\":\"success\"}]', '', '85182128', '15144042132', '15144042132', '张女士', '1', '经理', '1', '[{\"id\":\"6\",\"name\":\"营业执照.jpg\",\"url\":\"http://101.200.45.31:9001/static/image/D2F22718D343FFB0F692DAEE24B4DDB9.jpg\",\"suffix\":\"jpg\",\"size\":\"166735\",\"md5\":\"D2F22718D343FFB0F692DAEE24B4DDB9\",\"type\":\"image\",\"path\":null,\"sort\":null,\"createUid\":\"47\",\"createTime\":\"2020-05-12 09:27:47\",\"updateUid\":\"47\",\"updateTime\":\"2020-05-12 09:27:47\",\"isUse\":null,\"isDelete\":null,\"uid\":1589246866643,\"status\":\"success\"},{\"id\":\"23\",\"name\":\"_壁纸.jpg\",\"url\":\"http://101.200.45.31:9001/static/image/0A16EDD7C86E736D1CC1FD5D73A55C1B.jpg\",\"suffix\":\"jpg\",\"size\":\"12995\",\"md5\":\"0A16EDD7C86E736D1CC1FD5D73A55C1B\",\"type\":\"image\",\"path\":null,\"sort\":null,\"createUid\":\"1\",\"createTime\":\"2020-05-14 08:35:06\",\"updateUid\":\"1\",\"updateTime\":\"2020-05-14 08:35:06\",\"isUse\":null,\"isDelete\":null,\"uid\":1589416505587,\"status\":\"success\"}]', 100, 1, '2020-04-26 09:01:38', 1, '2020-04-26 09:01:38', '1', '2', 0);
INSERT INTO `company_info` VALUES (2, 48, 'admin002', '测试企业', 69, '10000人以上', '民营企业,上市公司', '<p><span class=\"mce-nbsp-wrap\" contenteditable=\"false\">&nbsp;&nbsp;&nbsp;</span><span class=\"mce-nbsp-wrap\" contenteditable=\"false\">&nbsp;&nbsp;&nbsp;</span>测试-缩进略略略略略略略略略</p>\n<p>略略略略略略略略略略略略略略略略略略略略</p>', '[{\"id\":\"32\",\"name\":\"_壁纸.jpg\",\"url\":\"http://101.200.45.31:9001/static/image/0A16EDD7C86E736D1CC1FD5D73A55C1B.jpg\",\"suffix\":\"jpg\",\"size\":\"12995\",\"md5\":\"0A16EDD7C86E736D1CC1FD5D73A55C1B\",\"type\":\"image\",\"path\":null,\"sort\":null,\"createUid\":\"48\",\"createTime\":\"2020-05-15 13:58:51\",\"updateUid\":\"48\",\"updateTime\":\"2020-05-15 13:58:51\",\"isUse\":null,\"isDelete\":null,\"uid\":1589522331011,\"status\":\"success\"}]', '测试地址2', '87553038', '15144042132', '15144042132', '小明', '小溪', '职务1', '职务2', '[{\"id\":\"33\",\"name\":\"_壁纸.jpg\",\"url\":\"http://101.200.45.31:9001/static/image/0A16EDD7C86E736D1CC1FD5D73A55C1B.jpg\",\"suffix\":\"jpg\",\"size\":\"12995\",\"md5\":\"0A16EDD7C86E736D1CC1FD5D73A55C1B\",\"type\":\"image\",\"path\":null,\"sort\":null,\"createUid\":\"48\",\"createTime\":\"2020-05-15 13:58:54\",\"updateUid\":\"48\",\"updateTime\":\"2020-05-15 13:58:54\",\"isUse\":null,\"isDelete\":null,\"uid\":1589522334880,\"status\":\"success\"}]', 100, 1, '2020-04-26 09:48:59', 1, '2020-04-26 09:48:59', '1', '2', 0);
INSERT INTO `company_info` VALUES (4, 50, 'changchundrylkj', '长春迪瑞医疗科技股份有限公司', 45, '50-150人', '民营企业', '11111', '[{\"id\":\"14\",\"name\":\"微信图片_20200508094802.jpg\",\"url\":\"http://101.200.45.31:9001/static/image/120D2E77BD5DADA32D32A67148097212.jpg\",\"suffix\":\"jpg\",\"size\":\"42291\",\"md5\":\"120D2E77BD5DADA32D32A67148097212\",\"type\":\"image\",\"path\":null,\"sort\":null,\"createUid\":\"1\",\"createTime\":\"2020-05-12 10:54:55\",\"updateUid\":\"1\",\"updateTime\":\"2020-05-12 10:54:55\",\"isUse\":null,\"isDelete\":null,\"uid\":1589252095825,\"status\":\"success\"}]', '1111', '1111', NULL, NULL, NULL, NULL, NULL, NULL, '[{\"id\":\"15\",\"name\":\"微信图片_20200508094819.jpg\",\"url\":\"http://101.200.45.31:9001/static/image/2A59B9C38DB842F6C61EA42A38324171.jpg\",\"suffix\":\"jpg\",\"size\":\"46525\",\"md5\":\"2A59B9C38DB842F6C61EA42A38324171\",\"type\":\"image\",\"path\":null,\"sort\":null,\"createUid\":\"1\",\"createTime\":\"2020-05-12 10:55:07\",\"updateUid\":\"1\",\"updateTime\":\"2020-05-12 10:55:07\",\"isUse\":null,\"isDelete\":null,\"uid\":1589252107933,\"status\":\"success\"}]', 100, 1, '2020-05-12 09:20:54', 1, '2020-05-12 09:20:54', '1', '2', 0);
INSERT INTO `company_info` VALUES (5, 51, 'cchtgdjs', '长春汇通光电技术有限公司', 69, '50-150人', '民营企业', '222222', '[{\"id\":\"8\",\"name\":\"QQ图片20190715133206.jpg\",\"url\":\"http://101.200.45.31:9001/static/image/83858D75E910974C1E2952012AF6F3FB.jpg\",\"suffix\":\"jpg\",\"size\":\"1781\",\"md5\":\"83858D75E910974C1E2952012AF6F3FB\",\"type\":\"image\",\"path\":null,\"sort\":null,\"createUid\":\"51\",\"createTime\":\"2020-05-12 10:22:18\",\"updateUid\":\"51\",\"updateTime\":\"2020-05-12 10:22:18\",\"isUse\":null,\"isDelete\":null,\"uid\":1589250138119,\"status\":\"success\"}]', '11111', '5211', NULL, NULL, '2', NULL, NULL, NULL, '[{\"id\":\"9\",\"name\":\"QQ图片20190715133206.jpg\",\"url\":\"http://101.200.45.31:9001/static/image/83858D75E910974C1E2952012AF6F3FB.jpg\",\"suffix\":\"jpg\",\"size\":\"1781\",\"md5\":\"83858D75E910974C1E2952012AF6F3FB\",\"type\":\"image\",\"path\":null,\"sort\":null,\"createUid\":\"51\",\"createTime\":\"2020-05-12 10:22:36\",\"updateUid\":\"51\",\"updateTime\":\"2020-05-12 10:22:36\",\"isUse\":null,\"isDelete\":null,\"uid\":1589250155679,\"status\":\"success\"}]', 100, 1, '2020-05-12 09:52:22', 1, '2020-05-12 09:52:22', '1', '2', 0);
INSERT INTO `company_info` VALUES (6, 52, 'yyqc1234', '越洋企业', 80, '150-500人', '合资企业,上市公司', 'Tina 2020/4/9 17:00:48\n长春仁实\n\nTina 2020/4/16 9:42:12\n吉林省肯普汽车零部件有限公司\n产品工程师1名\n质量工程师各1名\n要求：40岁以下，男士，同行业同岗位五年以上工作经验。待遇：薪资5～6k，十四薪，双休，五险一金。长春市内有班车。\n地址：范家屯\n电话：0434～5067308、13756059292\n联系人：李女士\n\n', '[]', '高新区', '85182128', '1236545', '12335', '王女士', '张女士', '经理', '经理', '[{\"id\":\"13\",\"name\":\"营业执照.jpg\",\"url\":\"http://101.200.45.31:9001/static/image/D2F22718D343FFB0F692DAEE24B4DDB9.jpg\",\"suffix\":\"jpg\",\"size\":\"166735\",\"md5\":\"D2F22718D343FFB0F692DAEE24B4DDB9\",\"type\":\"image\",\"path\":null,\"sort\":null,\"createUid\":\"1\",\"createTime\":\"2020-05-12 10:52:07\",\"updateUid\":\"1\",\"updateTime\":\"2020-05-12 10:52:07\",\"isUse\":null,\"isDelete\":null,\"uid\":1589251926570,\"status\":\"success\"}]', 100, 1, '2020-05-12 10:16:04', 1, '2020-05-12 10:16:04', '1', '2', 0);
INSERT INTO `company_info` VALUES (7, 53, '123456789', '123456789', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 100, 1, '2020-05-12 10:25:31', 1, '2020-05-12 10:25:31', '1', '2', 0);
INSERT INTO `company_info` VALUES (9, 55, 'yqjc1234', '一汽轿车', 80, '5000-10000人', '国企', '国企', '[{\"id\":\"17\",\"name\":\"营业执照.jpg\",\"url\":\"http://101.200.45.31:9001/static/image/D2F22718D343FFB0F692DAEE24B4DDB9.jpg\",\"suffix\":\"jpg\",\"size\":\"166735\",\"md5\":\"D2F22718D343FFB0F692DAEE24B4DDB9\",\"type\":\"image\",\"path\":null,\"sort\":null,\"createUid\":\"1\",\"createTime\":\"2020-05-12 10:58:02\",\"updateUid\":\"1\",\"updateTime\":\"2020-05-12 10:58:02\",\"isUse\":null,\"isDelete\":null,\"uid\":1589252281952,\"status\":\"success\"}]', '高新区', '12345678', '123456878', '123456', '女士', '王先生', '经理', '主管', '[]', 100, 1, '2020-05-12 10:33:52', 1, '2020-05-12 10:33:52', '1', '2', 0);
INSERT INTO `company_info` VALUES (10, 56, 'zdqc1234', '住电汽车线束', 80, '500-1000人', '上市公司,民营企业', '汽车零部件企业', '[]', '高新区', '12345678', '12345645', '1234656', '王先生', '王先生', '董事长', '车间主任', '[]', 100, 1, '2020-05-12 10:34:36', 1, '2020-05-12 10:34:36', '1', '2', 0);
INSERT INTO `company_info` VALUES (11, 57, 'fady1234', '富奥东阳汽车', 80, '1000-5000人', '港澳台公司', '一家汽车企业', '[]', '高新区', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[]', 100, 1, '2020-05-12 10:35:55', 1, '2020-05-12 10:35:55', '1', '2', 0);
INSERT INTO `company_info` VALUES (12, 58, 'jlhrxnygf', '吉林宏日新能源股份有限公司 ', 73, '150-500人', '民营企业', '\n吉林宏日新能源股份有限公司成立于2006年，系新三板创新层（宏日股份：839740）。宏日在农林业生物质成型燃料生产、成型燃料供热及资源收集、生物质专用锅炉和配套相关设备研发等方面取得了显著成果，拥有多项自主知识产权。并用自有技术开发供热市场，拥有分布式供热用户30多户，总供热面积超过500万m2，签约面积达2400万m2，年供应蒸汽量30余万吨，运营锅炉吨位达到700吨，分布于吉林、山东、陕西、北京各省市。\n', '[]', '高新', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[]', 100, 1, '2020-05-12 10:36:33', 1, '2020-05-12 10:36:33', '1', '2', 0);
INSERT INTO `company_info` VALUES (13, 59, 'ylqc1234', '英利汽车', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 100, 1, '2020-05-12 10:36:53', 1, '2020-05-12 10:36:53', '1', '2', 0);
INSERT INTO `company_info` VALUES (14, 60, 'changchunjsyy', '长春金赛药业有限责任公司', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 100, 1, '2020-05-12 10:39:27', 1, '2020-05-12 10:39:27', '1', '2', 0);
INSERT INTO `company_info` VALUES (15, 61, 'changchunbkswkj', '百克生物科技股份公司', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 100, 1, '2020-05-12 10:39:57', 1, '2020-05-12 10:39:57', '1', '2', 0);
INSERT INTO `company_info` VALUES (16, 62, 'jilinlhzy', '吉林省利华制药有限公司', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 100, 1, '2020-05-12 10:40:29', 1, '2020-05-12 10:40:29', '1', '2', 0);
INSERT INTO `company_info` VALUES (17, 63, 'changchunlysyy', '长春雷允上药业有限公司', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 100, 1, '2020-05-12 10:40:50', 1, '2020-05-12 10:40:50', '1', '2', 0);
INSERT INTO `company_info` VALUES (18, 64, 'jlsdtkyk', '吉林省登泰克牙科材料有限公司', 73, '150-500人', '民营企业', '   企业成立于2005年，地址位于长春市高新区锦湖大路1357E号，高新创业孵化产业园内，拥有办公及生产场地3100平方米，员工62人，注册资本2200万元，主营I 类、II 类、III 类 6863 口腔科材料、非医用类树脂、粘接剂、弹性体的生产（凭环保证经营）及销售、进出口贸易等业务，上市销售产品包括通用光固化树脂、', '[{\"id\":\"18\",\"name\":\"QQ图片20190715133206.jpg\",\"url\":\"http://101.200.45.31:9001/static/image/83858D75E910974C1E2952012AF6F3FB.jpg\",\"suffix\":\"jpg\",\"size\":\"1781\",\"md5\":\"83858D75E910974C1E2952012AF6F3FB\",\"type\":\"image\",\"path\":null,\"sort\":null,\"createUid\":\"1\",\"createTime\":\"2020-05-12 10:59:12\",\"updateUid\":\"1\",\"updateTime\":\"2020-05-12 10:59:12\",\"isUse\":null,\"isDelete\":null,\"uid\":1589252351430,\"status\":\"success\"}]', '高新', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[]', 100, 1, '2020-05-12 10:43:37', 1, '2020-05-12 10:43:37', '1', '2', 0);
INSERT INTO `company_info` VALUES (19, 65, 'jlszhgxdf', '吉林省正豪改性淀粉科技开发有限公司', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 100, 1, '2020-05-12 10:44:35', 1, '2020-05-12 10:44:35', '1', '2', 0);
INSERT INTO `company_info` VALUES (20, 66, 'cchgbmjs', '长春汉高表面技术有限公司', 72, '50-150人', '民营企业', '  吉林粮食集团与德国汉高有限公司合资企业，主要产品为工业用粘合剂、密封涂胶、汽车表面防护产品等产品。', '[{\"id\":\"16\",\"name\":\"QQ图片20190715133206.jpg\",\"url\":\"http://101.200.45.31:9001/static/image/83858D75E910974C1E2952012AF6F3FB.jpg\",\"suffix\":\"jpg\",\"size\":\"1781\",\"md5\":\"83858D75E910974C1E2952012AF6F3FB\",\"type\":\"image\",\"path\":null,\"sort\":null,\"createUid\":\"1\",\"createTime\":\"2020-05-12 10:56:44\",\"updateUid\":\"1\",\"updateTime\":\"2020-05-12 10:56:44\",\"isUse\":null,\"isDelete\":null,\"uid\":1589252203890,\"status\":\"success\"}]', '长春高新', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[]', 100, 1, '2020-05-12 10:51:02', 1, '2020-05-12 10:51:02', '1', '2', 0);
INSERT INTO `company_info` VALUES (21, 67, 'wanyikeji', '长春市万易科技有限公司', 21, '50-150人', '民营企业', '公司成立于2004年，注册资本3000万，是长春市政府参股企业，是吉林省政府、共青团中央、中国青企协重点发展的高新技术企业，是全国领先的智慧城市解决方案提供商。公司致力于提供规划咨询、解决方案、产品研发到IT运维的“端到端”软件及信息服务，是依托北京大学、清华大学、吉林大学、长春理工大学等著名高校前沿技术成立的高新技术企业；是科技部、国务院国资委、中华全国总工会联合授予的“国家创新型试点企业”，拥有“国家电子商务集成创新试点企业”、“吉林省科技小巨人企业”、“吉林省电子商务示范企业”等荣誉。', '[]', '长春市高新区顺达路688号超维集团6楼 ', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[]', 100, 1, '2020-05-12 12:19:54', 1, '2020-05-12 12:19:54', '1', '2', 0);
INSERT INTO `company_info` VALUES (22, 68, '11119999', '123123213', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 100, 1, '2020-05-14 08:33:06', 1, '2020-05-14 08:33:06', '1', '2', 0);

-- ----------------------------
-- Table structure for company_job
-- ----------------------------
DROP TABLE IF EXISTS `company_job`;
CREATE TABLE `company_job`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `company_id` bigint NOT NULL COMMENT '企业ID',
  `job_s` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '所有招聘岗位，逗号分割字符串',
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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业对应的全部招聘岗位信息' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of company_job
-- ----------------------------
INSERT INTO `company_job` VALUES (1, 2, '1,测试岗位1,测试岗位2,测试岗位3', NULL, 100, 1, '2020-04-26 10:26:10', 1, '2020-04-26 10:26:10', '1', '2', 0);
INSERT INTO `company_job` VALUES (2, 21, '行政专员', NULL, 100, 1, '2020-05-12 12:44:21', 1, '2020-05-12 12:44:21', '1', '2', 0);
INSERT INTO `company_job` VALUES (3, 1, '1', NULL, 100, 1, '2020-05-14 13:24:07', 1, '2020-05-14 13:24:07', '1', '2', 0);

-- ----------------------------
-- Table structure for company_recruit_job_info
-- ----------------------------
DROP TABLE IF EXISTS `company_recruit_job_info`;
CREATE TABLE `company_recruit_job_info`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `data_month` date NOT NULL COMMENT '数据录入月份（2020-02-02）',
  `company_id` bigint NOT NULL COMMENT '企业ID',
  `job_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位名称',
  `industry` bigint NULL DEFAULT NULL COMMENT '岗位所属行业类别',
  `industry_h` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位所属行业类别回显',
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别（男，女）',
  `people_age` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年龄段（字典）',
  `learn_level` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学历（字典）',
  `profession` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业',
  `experience` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经验',
  `lack_count` int NULL DEFAULT NULL COMMENT '招聘人数',
  `intention_count` int NULL DEFAULT NULL COMMENT '意向人数',
  `recruit_count` int NULL DEFAULT NULL COMMENT '成功人数',
  `month_salary` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '月薪范围',
  `other` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '其他待遇及条件',
  `des` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `people_count` int NULL DEFAULT NULL COMMENT '人数',
  `sort` int UNSIGNED NULL DEFAULT 100 COMMENT '排序(默认=100)',
  `create_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '数据创建时间',
  `update_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据修改者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '数据更新时间',
  `is_use` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否可用(1=可用 , 2=不可用)',
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '2' COMMENT '逻辑删除(1=已删除 , 2=未删除)',
  `version` bigint UNSIGNED NULL DEFAULT 0 COMMENT '版本号（预留）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业招聘岗位信息' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of company_recruit_job_info
-- ----------------------------
INSERT INTO `company_recruit_job_info` VALUES (1, '2020-04-01', 2, '1', 68, '[\"16\",\"68\"]', '男', '25-30周岁', '博士', '1', '1', 1, 1, 1, '1', '1', NULL, NULL, 100, 1, '2020-04-26 10:26:10', 1, '2020-04-26 10:26:10', '1', '2', 0);
INSERT INTO `company_recruit_job_info` VALUES (2, '2020-05-01', 21, '行政专员', 21, '[\"1\",\"21\"]', '男', '25-30周岁', '本科', '计算机', '1年以上经验', 1, 2, NULL, '2000', '无', NULL, NULL, 100, 1, '2020-05-12 12:44:21', 1, '2020-05-12 12:44:21', '1', '2', 0);
INSERT INTO `company_recruit_job_info` VALUES (3, '2020-05-01', 1, '1', 80, '[\"80\"]', '男', '16-24周岁', '研究生', '1', '1', 1, 1, 1, '1', '1', NULL, NULL, 100, 1, '2020-05-14 13:24:06', 1, '2020-05-14 13:24:06', '1', '2', 0);
INSERT INTO `company_recruit_job_info` VALUES (5, '2020-05-01', 2, '测试岗位1', 80, '[\"80\"]', '男', '16-24周岁', '博士', '测试专业', '不限', 10, 10, 10, '5k-10k', '五险一金，双休', NULL, NULL, 100, 1, '2020-05-19 09:57:27', 1, '2020-05-19 09:57:27', '1', '2', 0);
INSERT INTO `company_recruit_job_info` VALUES (6, '2020-05-01', 2, '测试岗位2', 44, '[\"11\",\"44\"]', '男', '25-30周岁', '中专（高中）', '无', '无', 2, 2, 2, '无', '无', NULL, NULL, 100, 1, '2020-05-19 09:57:50', 1, '2020-05-19 09:57:50', '1', '2', 0);
INSERT INTO `company_recruit_job_info` VALUES (7, '2020-05-01', 2, '测试岗位3', 45, '[\"11\",\"45\"]', '女', '60周岁以上', '研究生', '没有要求', '没有要求', 1, 1, 1, '无', '没有要求', NULL, NULL, 100, 1, '2020-05-19 10:05:01', 1, '2020-05-19 10:05:01', '1', '2', 0);

-- ----------------------------
-- Table structure for dynamic_human_info
-- ----------------------------
DROP TABLE IF EXISTS `dynamic_human_info`;
CREATE TABLE `dynamic_human_info`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `company_id` bigint NOT NULL COMMENT '企业ID',
  `data_month` date NOT NULL COMMENT '数据录入月份（2020-02-02）',
  `before_month_end_work_count` int NULL DEFAULT NULL COMMENT '上月末从业人员数',
  `this_month_lack_count` int NULL DEFAULT NULL COMMENT '本月缺工人数',
  `this_month_end_work_count` int NULL DEFAULT NULL COMMENT '月末从业人员数',
  `this_month_less_count` int NULL DEFAULT NULL COMMENT '本月减员人员数',
  `real_work_count` int NULL DEFAULT NULL COMMENT '本期实际招工人员数',
  `des` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `sort` int UNSIGNED NULL DEFAULT 100 COMMENT '排序(默认=100)',
  `create_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '数据创建时间',
  `update_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据修改者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '数据更新时间',
  `is_use` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否可用(1=可用 , 2=不可用)',
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '2' COMMENT '逻辑删除(1=已删除 , 2=未删除)',
  `version` bigint UNSIGNED NULL DEFAULT 0 COMMENT '版本号（预留）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '动态人力资源信息' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of dynamic_human_info
-- ----------------------------
INSERT INTO `dynamic_human_info` VALUES (1, 1, '2020-04-01', 12, 1, 12, 1, 1, '描述信息', 100, 1, '2020-04-26 09:57:53', 47, '2020-04-26 13:52:12', '1', '2', 0);
INSERT INTO `dynamic_human_info` VALUES (2, 1, '2020-01-01', 5, 2, 3, 3, 1, NULL, 100, 47, '2020-04-26 10:48:37', 47, '2020-04-26 10:48:37', '1', '2', 0);
INSERT INTO `dynamic_human_info` VALUES (3, 1, '2020-03-01', 1, 1, 1, 1, 1, NULL, 100, 47, '2020-04-26 14:06:13', 47, '2020-04-26 14:06:13', '1', '2', 0);
INSERT INTO `dynamic_human_info` VALUES (4, 1, '2020-05-01', 1, 2, 1, 1, 1, '1', 100, 47, '2020-05-12 09:51:50', 47, '2020-05-19 00:44:17', '1', '2', 0);
INSERT INTO `dynamic_human_info` VALUES (5, 4, '2020-05-01', 1, 1, 1, 1, 1, '1', 100, 1, '2020-05-18 11:25:38', 1, '2020-05-18 11:25:38', '1', '2', 0);
INSERT INTO `dynamic_human_info` VALUES (6, 12, '2020-05-01', 1, 1, 1, 1, 1, '1', 100, 1, '2020-05-18 13:28:11', 1, '2020-05-18 13:28:11', '1', '2', 0);
INSERT INTO `dynamic_human_info` VALUES (7, 2, '2020-05-01', 1, 1, 1, 1, 1, '1', 100, 1, '2020-05-19 09:55:53', 1, '2020-05-19 09:55:53', '1', '2', 0);
INSERT INTO `dynamic_human_info` VALUES (8, 6, '2020-05-01', 1, 1, 1, 1, 1, '1', 100, 1, '2020-05-19 11:23:28', 1, '2020-05-19 11:23:28', '1', '2', 0);

-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原文件名',
  `url` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '访问地址',
  `suffix` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '后缀',
  `size` bigint NULL DEFAULT NULL COMMENT '文件大小(KB)',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件MD5',
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件类型（图片 image; 视频 video; 其他 other）',
  `path` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '冗余字段',
  `sort` int UNSIGNED NULL DEFAULT 100 COMMENT '排序',
  `create_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '数据创建时间',
  `update_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据修改者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '数据更新时间',
  `is_use` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否可用(1=可用 , 2=不可用)',
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '2' COMMENT '逻辑删除(1=已删除 , 2=未删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `md5`(`md5`) USING BTREE COMMENT 'MD5普通索引'
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件详细信息' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of file_info
-- ----------------------------
INSERT INTO `file_info` VALUES (1, '_壁纸.jpg', 'http://127.0.0.1:8090/static/image/0A16EDD7C86E736D1CC1FD5D73A55C1B.jpg', 'jpg', 12995, '0A16EDD7C86E736D1CC1FD5D73A55C1B', 'image', NULL, 100, 47, '2020-04-26 13:59:27', 47, '2020-04-26 13:59:27', '1', '2');
INSERT INTO `file_info` VALUES (2, '_壁纸.jpg', 'http://127.0.0.1:8090/static/image/0A16EDD7C86E736D1CC1FD5D73A55C1B.jpg', 'jpg', 12995, '0A16EDD7C86E736D1CC1FD5D73A55C1B', 'image', NULL, 100, 47, '2020-04-26 13:59:47', 47, '2020-04-26 13:59:47', '1', '2');
INSERT INTO `file_info` VALUES (3, '_壁纸.jpg', 'http://127.0.0.1:8090/static/image/0A16EDD7C86E736D1CC1FD5D73A55C1B.jpg', 'jpg', 12995, '0A16EDD7C86E736D1CC1FD5D73A55C1B', 'image', NULL, 100, 47, '2020-04-30 08:27:01', 47, '2020-04-30 08:27:01', '1', '2');
INSERT INTO `file_info` VALUES (4, '_壁纸.jpg', 'http://101.200.45.31:9001/static/image/0A16EDD7C86E736D1CC1FD5D73A55C1B.jpg', 'jpg', 12995, '0A16EDD7C86E736D1CC1FD5D73A55C1B', 'image', NULL, 100, 1, '2020-04-30 08:30:18', 1, '2020-04-30 08:30:18', '1', '2');
INSERT INTO `file_info` VALUES (5, '营业执照.jpg', 'http://101.200.45.31:9001/static/image/D2F22718D343FFB0F692DAEE24B4DDB9.jpg', 'jpg', 166735, 'D2F22718D343FFB0F692DAEE24B4DDB9', 'image', NULL, 100, 47, '2020-05-12 09:23:34', 47, '2020-05-12 09:23:34', '1', '2');
INSERT INTO `file_info` VALUES (6, '营业执照.jpg', 'http://101.200.45.31:9001/static/image/D2F22718D343FFB0F692DAEE24B4DDB9.jpg', 'jpg', 166735, 'D2F22718D343FFB0F692DAEE24B4DDB9', 'image', NULL, 100, 47, '2020-05-12 09:27:47', 47, '2020-05-12 09:27:47', '1', '2');
INSERT INTO `file_info` VALUES (7, '微信图片_20200508094755.jpg', 'http://101.200.45.31:9001/static/image/759588B24BF2EA952D21FE2F982BE409.jpg', 'jpg', 346605, '759588B24BF2EA952D21FE2F982BE409', 'image', NULL, 100, 50, '2020-05-12 10:15:04', 50, '2020-05-12 10:15:04', '1', '2');
INSERT INTO `file_info` VALUES (8, 'QQ图片20190715133206.jpg', 'http://101.200.45.31:9001/static/image/83858D75E910974C1E2952012AF6F3FB.jpg', 'jpg', 1781, '83858D75E910974C1E2952012AF6F3FB', 'image', NULL, 100, 51, '2020-05-12 10:22:19', 51, '2020-05-12 10:22:19', '1', '2');
INSERT INTO `file_info` VALUES (9, 'QQ图片20190715133206.jpg', 'http://101.200.45.31:9001/static/image/83858D75E910974C1E2952012AF6F3FB.jpg', 'jpg', 1781, '83858D75E910974C1E2952012AF6F3FB', 'image', NULL, 100, 51, '2020-05-12 10:22:36', 51, '2020-05-12 10:22:36', '1', '2');
INSERT INTO `file_info` VALUES (10, '微信图片_20200508094755.jpg', 'http://101.200.45.31:9001/static/image/759588B24BF2EA952D21FE2F982BE409.jpg', 'jpg', 346605, '759588B24BF2EA952D21FE2F982BE409', 'image', NULL, 100, 50, '2020-05-12 10:23:14', 50, '2020-05-12 10:23:14', '1', '2');
INSERT INTO `file_info` VALUES (11, '营业执照.jpg', 'http://101.200.45.31:9001/static/image/D2F22718D343FFB0F692DAEE24B4DDB9.jpg', 'jpg', 166735, 'D2F22718D343FFB0F692DAEE24B4DDB9', 'image', NULL, 100, 59, '2020-05-12 10:40:34', 59, '2020-05-12 10:40:34', '1', '2');
INSERT INTO `file_info` VALUES (12, '营业执照.jpg', 'http://101.200.45.31:9001/static/image/D2F22718D343FFB0F692DAEE24B4DDB9.jpg', 'jpg', 166735, 'D2F22718D343FFB0F692DAEE24B4DDB9', 'image', NULL, 100, 59, '2020-05-12 10:41:40', 59, '2020-05-12 10:41:40', '1', '2');
INSERT INTO `file_info` VALUES (13, '营业执照.jpg', 'http://101.200.45.31:9001/static/image/D2F22718D343FFB0F692DAEE24B4DDB9.jpg', 'jpg', 166735, 'D2F22718D343FFB0F692DAEE24B4DDB9', 'image', NULL, 100, 1, '2020-05-12 10:52:07', 1, '2020-05-12 10:52:07', '1', '2');
INSERT INTO `file_info` VALUES (14, '微信图片_20200508094802.jpg', 'http://101.200.45.31:9001/static/image/120D2E77BD5DADA32D32A67148097212.jpg', 'jpg', 42291, '120D2E77BD5DADA32D32A67148097212', 'image', NULL, 100, 1, '2020-05-12 10:54:56', 1, '2020-05-12 10:54:56', '1', '2');
INSERT INTO `file_info` VALUES (15, '微信图片_20200508094819.jpg', 'http://101.200.45.31:9001/static/image/2A59B9C38DB842F6C61EA42A38324171.jpg', 'jpg', 46525, '2A59B9C38DB842F6C61EA42A38324171', 'image', NULL, 100, 1, '2020-05-12 10:55:08', 1, '2020-05-12 10:55:08', '1', '2');
INSERT INTO `file_info` VALUES (16, 'QQ图片20190715133206.jpg', 'http://101.200.45.31:9001/static/image/83858D75E910974C1E2952012AF6F3FB.jpg', 'jpg', 1781, '83858D75E910974C1E2952012AF6F3FB', 'image', NULL, 100, 1, '2020-05-12 10:56:44', 1, '2020-05-12 10:56:44', '1', '2');
INSERT INTO `file_info` VALUES (17, '营业执照.jpg', 'http://101.200.45.31:9001/static/image/D2F22718D343FFB0F692DAEE24B4DDB9.jpg', 'jpg', 166735, 'D2F22718D343FFB0F692DAEE24B4DDB9', 'image', NULL, 100, 1, '2020-05-12 10:58:02', 1, '2020-05-12 10:58:02', '1', '2');
INSERT INTO `file_info` VALUES (18, 'QQ图片20190715133206.jpg', 'http://101.200.45.31:9001/static/image/83858D75E910974C1E2952012AF6F3FB.jpg', 'jpg', 1781, '83858D75E910974C1E2952012AF6F3FB', 'image', NULL, 100, 1, '2020-05-12 10:59:12', 1, '2020-05-12 10:59:12', '1', '2');
INSERT INTO `file_info` VALUES (19, 'QQ图片20190715133206.jpg', 'http://101.200.45.31:9001/static/image/83858D75E910974C1E2952012AF6F3FB.jpg', 'jpg', 1781, '83858D75E910974C1E2952012AF6F3FB', 'image', NULL, 100, 54, '2020-05-12 11:15:38', 54, '2020-05-12 11:15:38', '1', '2');
INSERT INTO `file_info` VALUES (20, '_壁纸.jpg', 'http://101.200.45.31:9001/static/image/0A16EDD7C86E736D1CC1FD5D73A55C1B.jpg', 'jpg', 12995, '0A16EDD7C86E736D1CC1FD5D73A55C1B', 'image', NULL, 100, 1, '2020-05-14 08:34:30', 1, '2020-05-14 08:34:30', '1', '2');
INSERT INTO `file_info` VALUES (21, '_壁纸.jpg', 'http://101.200.45.31:9001/static/image/0A16EDD7C86E736D1CC1FD5D73A55C1B.jpg', 'jpg', 12995, '0A16EDD7C86E736D1CC1FD5D73A55C1B', 'image', NULL, 100, 1, '2020-05-14 08:34:33', 1, '2020-05-14 08:34:33', '1', '2');
INSERT INTO `file_info` VALUES (22, '_壁纸.jpg', 'http://101.200.45.31:9001/static/image/0A16EDD7C86E736D1CC1FD5D73A55C1B.jpg', 'jpg', 12995, '0A16EDD7C86E736D1CC1FD5D73A55C1B', 'image', NULL, 100, 1, '2020-05-14 08:34:59', 1, '2020-05-14 08:34:59', '1', '2');
INSERT INTO `file_info` VALUES (23, '_壁纸.jpg', 'http://101.200.45.31:9001/static/image/0A16EDD7C86E736D1CC1FD5D73A55C1B.jpg', 'jpg', 12995, '0A16EDD7C86E736D1CC1FD5D73A55C1B', 'image', NULL, 100, 1, '2020-05-14 08:35:07', 1, '2020-05-14 08:35:07', '1', '2');
INSERT INTO `file_info` VALUES (24, '微信图片_20200224111849.jpg', 'http://101.200.45.31:9001/static/image/43BA0B15303252DC8847FFE1CDFEBB30.jpg', 'jpg', 107835, '43BA0B15303252DC8847FFE1CDFEBB30', 'image', NULL, 100, 1, '2020-05-14 15:27:12', 1, '2020-05-14 15:27:12', '1', '2');
INSERT INTO `file_info` VALUES (25, '微信图片_20200224111849.jpg', 'http://101.200.45.31:9001/static/image/43BA0B15303252DC8847FFE1CDFEBB30.jpg', 'jpg', 107835, '43BA0B15303252DC8847FFE1CDFEBB30', 'image', NULL, 100, 1, '2020-05-14 15:27:29', 1, '2020-05-14 15:27:29', '1', '2');
INSERT INTO `file_info` VALUES (26, '微信图片_20200224111849.jpg', 'http://101.200.45.31:9001/static/image/43BA0B15303252DC8847FFE1CDFEBB30.jpg', 'jpg', 107835, '43BA0B15303252DC8847FFE1CDFEBB30', 'image', NULL, 100, 1, '2020-05-14 15:29:16', 1, '2020-05-14 15:29:16', '1', '2');
INSERT INTO `file_info` VALUES (27, '微信图片_20200224111849.jpg', 'http://101.200.45.31:9001/static/image/43BA0B15303252DC8847FFE1CDFEBB30.jpg', 'jpg', 107835, '43BA0B15303252DC8847FFE1CDFEBB30', 'image', NULL, 100, 1, '2020-05-14 15:29:19', 1, '2020-05-14 15:29:19', '1', '2');
INSERT INTO `file_info` VALUES (28, '微信图片_20200224111849.jpg', 'http://101.200.45.31:9001/static/image/43BA0B15303252DC8847FFE1CDFEBB30.jpg', 'jpg', 107835, '43BA0B15303252DC8847FFE1CDFEBB30', 'image', NULL, 100, 1, '2020-05-14 15:30:05', 1, '2020-05-14 15:30:05', '1', '2');
INSERT INTO `file_info` VALUES (29, '微信图片_20200224111849.jpg', 'http://101.200.45.31:9001/static/image/43BA0B15303252DC8847FFE1CDFEBB30.jpg', 'jpg', 107835, '43BA0B15303252DC8847FFE1CDFEBB30', 'image', NULL, 100, 1, '2020-05-14 15:30:12', 1, '2020-05-14 15:30:12', '1', '2');
INSERT INTO `file_info` VALUES (30, '微信图片_20200224111849.jpg', 'http://101.200.45.31:9001/static/image/43BA0B15303252DC8847FFE1CDFEBB30.jpg', 'jpg', 107835, '43BA0B15303252DC8847FFE1CDFEBB30', 'image', NULL, 100, 48, '2020-05-14 15:38:51', 48, '2020-05-14 15:38:51', '1', '2');
INSERT INTO `file_info` VALUES (31, '微信图片_20200224111849.jpg', 'http://101.200.45.31:9001/static/image/43BA0B15303252DC8847FFE1CDFEBB30.jpg', 'jpg', 107835, '43BA0B15303252DC8847FFE1CDFEBB30', 'image', NULL, 100, 48, '2020-05-15 08:29:45', 48, '2020-05-15 08:29:45', '1', '2');
INSERT INTO `file_info` VALUES (32, '_壁纸.jpg', 'http://101.200.45.31:9001/static/image/0A16EDD7C86E736D1CC1FD5D73A55C1B.jpg', 'jpg', 12995, '0A16EDD7C86E736D1CC1FD5D73A55C1B', 'image', NULL, 100, 48, '2020-05-15 13:58:51', 48, '2020-05-15 13:58:51', '1', '2');
INSERT INTO `file_info` VALUES (33, '_壁纸.jpg', 'http://101.200.45.31:9001/static/image/0A16EDD7C86E736D1CC1FD5D73A55C1B.jpg', 'jpg', 12995, '0A16EDD7C86E736D1CC1FD5D73A55C1B', 'image', NULL, 100, 48, '2020-05-15 13:58:55', 48, '2020-05-15 13:58:55', '1', '2');

-- ----------------------------
-- Table structure for in_work_human_info
-- ----------------------------
DROP TABLE IF EXISTS `in_work_human_info`;
CREATE TABLE `in_work_human_info`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `data_month` date NOT NULL COMMENT '数据录入月份（2020-02-02）',
  `company_id` bigint NOT NULL COMMENT '企业ID',
  `in_work_people_count` int NULL DEFAULT NULL COMMENT '当前月份在职人员总数',
  `job_type` json NULL COMMENT '岗位类别（字典）{\"类型1\":\"10\", \"类型2\":\"30\"}',
  `people_type` json NULL COMMENT '人员类型（字典）{\"类型1\":\"10\", \"类型2\":\"30\"}',
  `learn_level` json NULL COMMENT '学历（字典）{\"类型1\":\"10\", \"类型2\":\"30\"}',
  `sex` json NULL COMMENT '性别（男，女）{\"类型1\":\"10\", \"类型2\":\"30\"}',
  `people_age` json NULL COMMENT '年龄段（字典）{\"类型1\":\"10\", \"类型2\":\"30\"}',
  `politics_face` json NULL COMMENT '政治面貌（字典）{\"类型1\":\"10\", \"类型2\":\"30\"}',
  `des` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `sort` int UNSIGNED NULL DEFAULT 100 COMMENT '排序(默认=100)',
  `create_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '数据创建时间',
  `update_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据修改者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '数据更新时间',
  `is_use` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否可用(1=可用 , 2=不可用)',
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '2' COMMENT '逻辑删除(1=已删除 , 2=未删除)',
  `version` bigint UNSIGNED NULL DEFAULT 0 COMMENT '版本号（预留）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '在职人员信息' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of in_work_human_info
-- ----------------------------
INSERT INTO `in_work_human_info` VALUES (1, '2020-04-01', 1, NULL, '{\"技术岗\": \"1\", \"普通岗\": \"1\", \"管理岗\": \"1\"}', '{\"被征地农民\": \"1\", \"应届高校毕业生\": \"1\", \"有经验的工作人员\": \"1\"}', '{\"博士\": \"1\", \"大专\": \"1\", \"本科\": \"1\", \"研究生\": \"1\", \"初中及以下\": \"1\", \"中专（高中）\": \"1\"}', '{\"女\": \"1\", \"男\": \"1\"}', '{\"16-24周岁\": \"1\", \"25-30周岁\": \"1\", \"31-35周岁\": \"1\", \"35-45周岁\": \"1\", \"45-55周岁\": \"1\", \"55-60周岁\": \"1\", \"60周岁以上\": \"1\"}', '{\"其它\": \"1\", \"中国共产党党员\": \"1\"}', NULL, 100, 48, '2020-04-26 10:04:25', 47, '2020-04-26 14:07:11', '1', '2', 0);
INSERT INTO `in_work_human_info` VALUES (2, '2020-03-01', 1, NULL, '{\"技术岗\": \"1\", \"普通岗\": \"1\", \"管理岗\": \"3\"}', '{\"被征地农民\": \"1\", \"应届高校毕业生\": \"1\", \"有经验的工作人员\": \"1\"}', '{\"博士\": \"1\", \"大专\": \"1\", \"本科\": \"1\", \"研究生\": \"1\", \"初中及以下\": \"1\", \"中专（高中）\": \"1\"}', '{\"女\": \"1\", \"男\": \"1\"}', '{\"16-24周岁\": \"1\", \"25-30周岁\": \"1\", \"31-35周岁\": \"1\", \"35-45周岁\": \"1\", \"45-55周岁\": \"1\", \"55-60周岁\": \"1\", \"60周岁以上\": \"1\"}', '{\"其它\": \"1\", \"中国共产党党员\": \"1\"}', NULL, 100, 47, '2020-04-26 14:08:43', 47, '2020-04-26 14:08:43', '1', '2', 0);
INSERT INTO `in_work_human_info` VALUES (3, '2020-05-01', 2, 20, '{\"技术岗\": 11, \"普通岗\": 1, \"管理岗\": 1}', '{\"被征地农民\": 1, \"应届高校毕业生\": 18, \"有经验的工作人员\": 1}', '{\"博士\": 1, \"大专\": 1, \"本科\": 1, \"研究生\": 1, \"初中及以下\": 15, \"中专（高中）\": 1}', '{\"女\": 19, \"男\": 1}', '{\"16-24周岁\": 1, \"25-30周岁\": 14, \"31-35周岁\": 1, \"35-45周岁\": 1, \"45-55周岁\": 1, \"55-60周岁\": 1, \"60周岁以上\": 1}', '{\"其它\": 19, \"中国共产党党员\": 1}', NULL, 100, 47, '2020-05-12 09:44:13', 48, '2020-05-14 17:18:18', '1', '2', 0);

-- ----------------------------
-- Table structure for industry_type
-- ----------------------------
DROP TABLE IF EXISTS `industry_type`;
CREATE TABLE `industry_type`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `pid` bigint NOT NULL DEFAULT 0 COMMENT 'PID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `sort` int UNSIGNED NULL DEFAULT 100 COMMENT '排序(默认=100)',
  `create_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '数据创建时间',
  `update_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据修改者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '数据更新时间',
  `is_use` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否可用(1=可用 , 2=不可用)',
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '2' COMMENT '逻辑删除(1=已删除 , 2=未删除)',
  `version` bigint UNSIGNED NULL DEFAULT 0 COMMENT '版本号（预留）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 81 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '行业类别' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of industry_type
-- ----------------------------
INSERT INTO `industry_type` VALUES (1, 0, '计算机/互联网/通信/电子', 100, 0, NULL, 0, NULL, '1', '2', 0);
INSERT INTO `industry_type` VALUES (2, 1, '测试', 100, 0, NULL, 0, '2020-04-13 13:39:07', '1', '2', 0);
INSERT INTO `industry_type` VALUES (9, 0, '会计/金融/银行/保险', 100, 44, '2020-04-14 16:06:59', 44, '2020-04-14 16:06:59', '1', '2', 0);
INSERT INTO `industry_type` VALUES (10, 0, '贸易/消费/制造/运营', 100, 44, '2020-04-14 16:07:23', 44, '2020-04-14 16:07:23', '1', '2', 0);
INSERT INTO `industry_type` VALUES (11, 0, '制药/医疗', 100, 44, '2020-04-14 16:07:45', 44, '2020-04-14 16:07:45', '1', '2', 0);
INSERT INTO `industry_type` VALUES (12, 0, '广告/媒体', 100, 44, '2020-04-14 16:07:57', 44, '2020-04-14 16:07:57', '1', '2', 0);
INSERT INTO `industry_type` VALUES (13, 0, '房地产/建筑', 100, 44, '2020-04-14 16:08:05', 44, '2020-04-14 16:08:05', '1', '2', 0);
INSERT INTO `industry_type` VALUES (14, 0, '专业服务/教育/培训', 100, 44, '2020-04-14 16:08:21', 44, '2020-04-14 16:08:21', '1', '2', 0);
INSERT INTO `industry_type` VALUES (15, 0, '服务业', 100, 44, '2020-04-14 16:08:26', 44, '2020-04-14 16:08:26', '1', '2', 0);
INSERT INTO `industry_type` VALUES (16, 0, '物流/运输', 100, 44, '2020-04-14 16:08:36', 44, '2020-04-14 16:08:36', '1', '2', 0);
INSERT INTO `industry_type` VALUES (17, 0, '能源/环保/化工', 100, 44, '2020-04-14 16:08:53', 44, '2020-04-14 16:08:53', '1', '2', 0);
INSERT INTO `industry_type` VALUES (18, 0, '政府/非盈利组织/其他', 100, 44, '2020-04-14 16:09:08', 44, '2020-04-14 16:09:08', '1', '2', 0);
INSERT INTO `industry_type` VALUES (19, 1, '计算机软件', 100, 44, '2020-04-14 16:10:50', 44, '2020-04-14 16:10:50', '1', '2', 0);
INSERT INTO `industry_type` VALUES (20, 1, '计算机硬件', 100, 44, '2020-04-14 16:11:01', 44, '2020-04-14 16:11:01', '1', '2', 0);
INSERT INTO `industry_type` VALUES (21, 1, '计算机服务(系统、数据服务、维修)', 100, 44, '2020-04-14 16:11:31', 44, '2020-04-14 16:11:31', '1', '2', 0);
INSERT INTO `industry_type` VALUES (22, 1, '通信/电信/网络设备', 100, 44, '2020-04-14 16:11:43', 44, '2020-04-14 16:11:43', '1', '2', 0);
INSERT INTO `industry_type` VALUES (23, 1, '通信/电信运营、增值服务', 100, 44, '2020-04-14 16:11:54', 44, '2020-04-14 16:11:54', '1', '2', 0);
INSERT INTO `industry_type` VALUES (24, 1, '互联网/电子商务', 100, 44, '2020-04-14 16:12:06', 44, '2020-04-14 16:12:06', '1', '2', 0);
INSERT INTO `industry_type` VALUES (25, 1, '网络游戏', 100, 44, '2020-04-14 16:12:14', 44, '2020-04-14 16:12:14', '1', '2', 0);
INSERT INTO `industry_type` VALUES (26, 1, '电子技术/半导体/集成电路', 100, 44, '2020-04-14 16:12:31', 44, '2020-04-14 16:12:31', '1', '2', 0);
INSERT INTO `industry_type` VALUES (27, 1, '仪器仪表/工业自动化', 100, 44, '2020-04-14 16:12:43', 44, '2020-04-14 16:12:43', '1', '2', 0);
INSERT INTO `industry_type` VALUES (28, 9, '会计/审计', 100, 44, '2020-04-14 16:12:58', 44, '2020-04-14 16:12:58', '1', '2', 0);
INSERT INTO `industry_type` VALUES (29, 9, '金融/投资/证券', 100, 44, '2020-04-14 16:13:12', 44, '2020-04-14 16:13:12', '1', '2', 0);
INSERT INTO `industry_type` VALUES (30, 9, '银行', 100, 44, '2020-04-14 16:13:17', 44, '2020-04-14 16:13:17', '1', '2', 0);
INSERT INTO `industry_type` VALUES (31, 9, '保险', 100, 44, '2020-04-14 16:13:21', 44, '2020-04-14 16:13:21', '1', '2', 0);
INSERT INTO `industry_type` VALUES (32, 9, '信托/担保/拍卖/典当', 100, 44, '2020-04-14 16:13:40', 44, '2020-04-14 16:13:40', '1', '2', 0);
INSERT INTO `industry_type` VALUES (33, 10, '贸易/进出口', 100, 44, '2020-04-14 16:13:55', 44, '2020-04-14 16:13:55', '1', '2', 0);
INSERT INTO `industry_type` VALUES (34, 10, '批发/零售', 100, 44, '2020-04-14 16:14:06', 44, '2020-04-14 16:14:06', '1', '2', 0);
INSERT INTO `industry_type` VALUES (35, 10, '快速消费品(视频、饮料、化妆品)', 100, 44, '2020-04-14 16:14:28', 44, '2020-04-14 16:14:28', '1', '2', 0);
INSERT INTO `industry_type` VALUES (36, 10, '服装/纺织/皮革', 100, 44, '2020-04-14 16:14:49', 44, '2020-04-14 16:14:49', '1', '2', 0);
INSERT INTO `industry_type` VALUES (37, 10, '家具/加点/玩具/礼品', 100, 44, '2020-04-14 16:16:26', 44, '2020-04-14 16:16:26', '1', '2', 0);
INSERT INTO `industry_type` VALUES (38, 10, '奢侈品/收藏品/工艺品/珠宝', 100, 44, '2020-04-14 16:16:42', 44, '2020-04-14 16:16:42', '1', '2', 0);
INSERT INTO `industry_type` VALUES (39, 10, '办公用品及设备', 100, 44, '2020-04-14 16:17:35', 44, '2020-04-14 16:17:35', '1', '2', 0);
INSERT INTO `industry_type` VALUES (40, 10, '机械/设备/重工', 100, 44, '2020-04-14 16:17:48', 44, '2020-04-14 16:17:48', '1', '2', 0);
INSERT INTO `industry_type` VALUES (41, 10, '汽车', 100, 44, '2020-04-14 16:17:50', 44, '2020-04-14 16:17:50', '1', '2', 0);
INSERT INTO `industry_type` VALUES (42, 10, '汽车零配件', 100, 44, '2020-04-14 16:17:55', 44, '2020-04-14 16:17:55', '1', '2', 0);
INSERT INTO `industry_type` VALUES (43, 11, '制药/生物工程', 100, 44, '2020-04-14 16:18:16', 44, '2020-04-14 16:18:16', '1', '2', 0);
INSERT INTO `industry_type` VALUES (44, 11, '医疗/护理/卫生', 100, 44, '2020-04-14 16:18:32', 44, '2020-04-14 16:18:32', '1', '2', 0);
INSERT INTO `industry_type` VALUES (45, 11, '医疗设备/器械', 100, 44, '2020-04-14 16:18:41', 44, '2020-04-14 16:18:41', '1', '2', 0);
INSERT INTO `industry_type` VALUES (46, 12, '广告', 100, 44, '2020-04-14 16:18:48', 44, '2020-04-14 16:18:48', '1', '2', 0);
INSERT INTO `industry_type` VALUES (47, 12, '公关/市场推广/会展', 100, 44, '2020-04-14 16:19:08', 44, '2020-04-14 16:19:08', '1', '2', 0);
INSERT INTO `industry_type` VALUES (48, 12, '影视/媒体/艺术/文化传播', 100, 44, '2020-04-14 16:19:23', 44, '2020-04-14 16:19:23', '1', '2', 0);
INSERT INTO `industry_type` VALUES (49, 12, '文字媒体/出版', 100, 44, '2020-04-14 16:21:46', 44, '2020-04-14 16:21:46', '1', '2', 0);
INSERT INTO `industry_type` VALUES (50, 12, '印刷/包装/造纸', 100, 44, '2020-04-14 16:22:01', 44, '2020-04-14 16:22:01', '1', '2', 0);
INSERT INTO `industry_type` VALUES (51, 13, '房地产', 100, 44, '2020-04-14 16:22:10', 44, '2020-04-14 16:22:10', '1', '2', 0);
INSERT INTO `industry_type` VALUES (52, 13, '建筑/建材/工程', 100, 44, '2020-04-14 16:22:20', 44, '2020-04-14 16:22:20', '1', '2', 0);
INSERT INTO `industry_type` VALUES (53, 13, '家居/室内设计/装潢', 100, 44, '2020-04-14 16:22:40', 44, '2020-04-14 16:22:40', '1', '2', 0);
INSERT INTO `industry_type` VALUES (54, 13, '物业管理/商业中心', 100, 44, '2020-04-14 16:22:54', 44, '2020-04-14 16:22:54', '1', '2', 0);
INSERT INTO `industry_type` VALUES (55, 13, '中介服务', 100, 44, '2020-04-14 16:22:57', 44, '2020-04-14 16:22:57', '1', '2', 0);
INSERT INTO `industry_type` VALUES (56, 13, '租赁服务', 100, 44, '2020-04-14 16:23:05', 44, '2020-04-14 16:23:05', '1', '2', 0);
INSERT INTO `industry_type` VALUES (57, 14, '专业服务(咨询、人力资源、财会)', 100, 44, '2020-04-14 16:23:37', 44, '2020-04-14 16:23:37', '1', '2', 0);
INSERT INTO `industry_type` VALUES (58, 14, '外包服务', 100, 44, '2020-04-14 16:23:45', 44, '2020-04-14 16:23:45', '1', '2', 0);
INSERT INTO `industry_type` VALUES (59, 14, '检测，认证', 100, 44, '2020-04-14 16:23:50', 44, '2020-04-14 16:23:50', '1', '2', 0);
INSERT INTO `industry_type` VALUES (60, 14, '法律', 100, 44, '2020-04-14 16:23:54', 44, '2020-04-14 16:23:54', '1', '2', 0);
INSERT INTO `industry_type` VALUES (61, 14, '教育/培训/院校', 100, 44, '2020-04-14 16:24:03', 44, '2020-04-14 16:24:03', '1', '2', 0);
INSERT INTO `industry_type` VALUES (62, 14, '学术/科研', 100, 44, '2020-04-14 16:24:10', 44, '2020-04-14 16:24:10', '1', '2', 0);
INSERT INTO `industry_type` VALUES (63, 15, '餐饮业', 100, 44, '2020-04-14 16:24:22', 44, '2020-04-14 16:24:22', '1', '2', 0);
INSERT INTO `industry_type` VALUES (64, 15, '酒店/旅游', 100, 44, '2020-04-14 16:24:27', 44, '2020-04-14 16:24:27', '1', '2', 0);
INSERT INTO `industry_type` VALUES (65, 15, '娱乐/休闲/体育', 100, 44, '2020-04-14 16:24:38', 44, '2020-04-14 16:24:38', '1', '2', 0);
INSERT INTO `industry_type` VALUES (66, 15, '美容/保健', 100, 44, '2020-04-14 16:24:48', 44, '2020-04-14 16:24:48', '1', '2', 0);
INSERT INTO `industry_type` VALUES (67, 15, '生活服务', 100, 44, '2020-04-14 16:24:54', 44, '2020-04-14 16:24:54', '1', '2', 0);
INSERT INTO `industry_type` VALUES (68, 16, '交通/运输/物流', 100, 44, '2020-04-14 16:25:14', 44, '2020-04-14 16:25:14', '1', '2', 0);
INSERT INTO `industry_type` VALUES (69, 16, '航天/航空', 100, 44, '2020-04-14 16:25:24', 44, '2020-04-14 16:25:24', '1', '2', 0);
INSERT INTO `industry_type` VALUES (70, 17, '石油/化工/矿产/地址', 100, 44, '2020-04-14 16:25:51', 44, '2020-04-14 16:25:51', '1', '2', 0);
INSERT INTO `industry_type` VALUES (71, 17, '采掘业/冶炼', 100, 44, '2020-04-14 16:26:04', 44, '2020-04-14 16:26:04', '1', '2', 0);
INSERT INTO `industry_type` VALUES (72, 17, '电气/电力/水利', 100, 44, '2020-04-14 16:26:20', 44, '2020-04-14 16:26:20', '1', '2', 0);
INSERT INTO `industry_type` VALUES (73, 17, '新能源', 100, 44, '2020-04-14 16:26:23', 44, '2020-04-14 16:26:23', '1', '2', 0);
INSERT INTO `industry_type` VALUES (74, 17, '原材料加工', 100, 44, '2020-04-14 16:26:28', 44, '2020-04-14 16:26:28', '1', '2', 0);
INSERT INTO `industry_type` VALUES (75, 17, '环保', 100, 44, '2020-04-14 16:26:30', 44, '2020-04-14 16:26:30', '1', '2', 0);
INSERT INTO `industry_type` VALUES (76, 18, '政府/公共事业', 100, 44, '2020-04-14 16:26:50', 44, '2020-04-14 16:26:50', '1', '2', 0);
INSERT INTO `industry_type` VALUES (77, 18, '非盈利组织', 100, 44, '2020-04-14 16:26:54', 44, '2020-04-14 16:26:54', '1', '2', 0);
INSERT INTO `industry_type` VALUES (78, 18, '农/林/牧/渔', 100, 44, '2020-04-14 16:27:11', 44, '2020-04-14 16:27:11', '1', '2', 0);
INSERT INTO `industry_type` VALUES (79, 18, '多元化业务集团公司', 100, 44, '2020-04-14 16:27:21', 44, '2020-04-14 16:27:21', '1', '2', 0);
INSERT INTO `industry_type` VALUES (80, 0, '汽车及零部件', 100, 1, '2020-05-12 10:38:39', 1, '2020-05-12 10:38:39', '1', '2', 0);

-- ----------------------------
-- Table structure for recruit_info
-- ----------------------------
DROP TABLE IF EXISTS `recruit_info`;
CREATE TABLE `recruit_info`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `company_id` bigint NOT NULL COMMENT '企业ID',
  `data_month` date NOT NULL COMMENT '数据录入月份（2020-02-02）',
  `des` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `sort` int UNSIGNED NULL DEFAULT 100 COMMENT '排序(默认=100)',
  `create_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '数据创建时间',
  `update_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据修改者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '数据更新时间',
  `is_use` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否可用(1=可用 , 2=不可用)',
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '2' COMMENT '逻辑删除(1=已删除 , 2=未删除)',
  `version` bigint UNSIGNED NULL DEFAULT 0 COMMENT '版本号（预留）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `company_id`(`company_id`, `data_month`) USING BTREE COMMENT '企业+月份做唯一所以条件'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '高新区用人单位招聘信息' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of recruit_info
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 300024 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, '请用下划线分隔', 'a_b_c_d', '主库', NULL, 100, 0, NULL, 0, '2020-04-08 11:09:12', '1', '2', 0);
INSERT INTO `sys_dict` VALUES (10001, '是否开启权限认证', 'system_enable_permission_authentication', 'true', NULL, 100, 0, NULL, 0, NULL, '1', '2', 0);
INSERT INTO `sys_dict` VALUES (10002, 'token失效时长', 'system_jwt_token_time_out', '2', '单位小时', 100, 0, NULL, 0, NULL, '1', '2', 0);
INSERT INTO `sys_dict` VALUES (10004, '服务端-版本号', 'server_version', '318', NULL, 100, 0, NULL, 0, '2020-05-19 15:08:47', '1', '2', 0);
INSERT INTO `sys_dict` VALUES (10005, '用户默密码', 'user_default_password', '1234qwer', NULL, 100, 0, NULL, 0, '2020-04-11 16:17:40', '1', '2', 0);
INSERT INTO `sys_dict` VALUES (10009, '盐值', 'system_server_salt', 'F9B5F1A6C9B9A58631C4A85619C3EF93C687906D36CBC2CF9', NULL, 100, 0, NULL, 0, NULL, '1', '2', 0);
INSERT INTO `sys_dict` VALUES (10010, '文件上传地址-Linux', 'upload_file_path_linux', '/opt/gx_statistics/upload_file/', NULL, 100, 0, NULL, 0, '2020-04-11 16:17:40', '1', '2', 0);
INSERT INTO `sys_dict` VALUES (10011, '文件上传地址-windows', 'upload_file_path_windows', 'D://upload_file/', NULL, 100, 0, NULL, 0, '2020-04-11 16:17:40', '1', '2', 0);
INSERT INTO `sys_dict` VALUES (10012, '文件访问域名', 'upload_file_access_url', 'http://101.200.45.31:9001', NULL, 100, 0, NULL, 0, '2020-04-11 16:17:40', '1', '2', 0);
INSERT INTO `sys_dict` VALUES (10013, '文件上传大小-单位M', 'upload_file_max_size', '3', '单位M', 100, 0, NULL, 0, '2020-04-11 16:17:40', '1', '2', 0);
INSERT INTO `sys_dict` VALUES (20001, 'WebSocket地址', 'web_socket_url', 'http://127.0.0.1:8090/socket', NULL, 100, 0, NULL, 0, NULL, '1', '2', 0);
INSERT INTO `sys_dict` VALUES (20002, '是否输出日志', 'admin_show_log', 'false', NULL, 100, 0, NULL, 0, NULL, '1', '2', 0);
INSERT INTO `sys_dict` VALUES (20003, 'Admin-版本号', 'admin_version', '318', NULL, 100, 0, NULL, 0, '2020-05-19 15:08:47', '1', '2', 0);
INSERT INTO `sys_dict` VALUES (20007, NULL, '1', '1', NULL, 100, 0, '2020-03-10 13:25:42', 0, '2020-03-10 13:25:42', '1', '2', 0);
INSERT INTO `sys_dict` VALUES (20008, NULL, '2', '1', NULL, 100, 0, '2020-03-10 13:25:42', 0, '2020-03-10 13:25:42', '1', '2', 0);
INSERT INTO `sys_dict` VALUES (20009, NULL, '3', '1', NULL, 100, 0, '2020-03-10 13:25:42', 0, '2020-03-10 13:25:42', '1', '2', 0);
INSERT INTO `sys_dict` VALUES (20010, NULL, '4', '1', NULL, 100, 0, '2020-03-10 13:25:42', 0, '2020-03-10 13:25:42', '1', '2', 0);
INSERT INTO `sys_dict` VALUES (20011, NULL, '5', '1', NULL, 100, 0, '2020-03-10 13:25:42', 0, '2020-03-10 13:25:42', '1', '2', 0);
INSERT INTO `sys_dict` VALUES (300001, '企业类型', 'company_type', '[\"国企\", \"民营企业\", \"港澳台公司\", \"合资企业\", \"外商独资企业\", \"上市公司\", \"政府机关\", \"事业单位\", \"外企代表处\", \"其它\"]', 'JSON数组', 100, 0, '2020-03-10 13:25:42', 0, '2020-03-10 13:25:42', '1', '2', 0);
INSERT INTO `sys_dict` VALUES (300002, '企业规模', 'company_scale', '[\"少于50人\", \"50-150人\", \"150-500人\", \"500-1000人\", \"1000-5000人\", \"5000-10000人\", \"10000人以上\"]', 'JSON数组', 100, 0, '2020-03-10 13:25:42', 0, '2020-03-10 13:25:42', '1', '2', 0);
INSERT INTO `sys_dict` VALUES (300010, '人员类型', 'people_type', '[\"有经验的工作人员\", \"应届高校毕业生\", \"被征地农民\"]', 'JSON数组', 100, 0, '2020-03-10 13:25:42', 0, '2020-03-10 13:25:42', '1', '2', 0);
INSERT INTO `sys_dict` VALUES (300011, '人员年龄段', 'people_age', '[\"16-24周岁\", \"25-30周岁\", \"31-35周岁\", \"35-45周岁\", \"45-55周岁\", \"55-60周岁\", \"60周岁以上\"]', 'JSON数组', 100, 0, '2020-03-10 13:25:42', 0, '2020-03-10 13:25:42', '1', '2', 0);
INSERT INTO `sys_dict` VALUES (300012, '政治面貌', 'politics_face', '[\"中国共产党党员\",\"其它\"]', 'JSON数组', 100, 0, '2020-03-10 13:25:42', 0, '2020-03-10 13:25:42', '1', '2', 0);
INSERT INTO `sys_dict` VALUES (300021, '单位性质不用了，使用企业类型', 'unit_type', '[\"民营\",\"上市\",\"股份制\"]', 'JSON数组', 100, 0, '2020-03-10 13:25:42', 0, '2020-03-10 13:25:42', '1', '2', 0);
INSERT INTO `sys_dict` VALUES (300022, '岗位类别', 'job_type', '[\"管理岗\", \"技术岗\", \"普通岗\"]', 'JSON数组', 100, 0, '2020-03-10 13:25:42', 0, '2020-03-10 13:25:42', '1', '2', 0);
INSERT INTO `sys_dict` VALUES (300023, '学历', 'learn_level', '[\"博士\", \"研究生\", \"本科\", \"大专\", \"中专（高中）\", \"初中及以下\"]', 'JSON数组', 100, 0, '2020-03-10 13:25:42', 0, '2020-03-10 13:25:42', '1', '2', 0);

-- ----------------------------
-- Table structure for sys_link_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_link_role_menu`;
CREATE TABLE `sys_link_role_menu`  (
  `role_id` bigint UNSIGNED NOT NULL,
  `menu_id` bigint UNSIGNED NOT NULL,
  PRIMARY KEY (`menu_id`, `role_id`) USING BTREE,
  UNIQUE INDEX `menu_id`(`menu_id`, `role_id`) USING BTREE COMMENT '联合唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '关联表（角色-菜单）' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_link_role_menu
-- ----------------------------
INSERT INTO `sys_link_role_menu` VALUES (2, 68);
INSERT INTO `sys_link_role_menu` VALUES (2, 69);
INSERT INTO `sys_link_role_menu` VALUES (2, 70);
INSERT INTO `sys_link_role_menu` VALUES (2, 71);
INSERT INTO `sys_link_role_menu` VALUES (1, 72);
INSERT INTO `sys_link_role_menu` VALUES (1, 73);
INSERT INTO `sys_link_role_menu` VALUES (1, 74);
INSERT INTO `sys_link_role_menu` VALUES (2, 75);
INSERT INTO `sys_link_role_menu` VALUES (2, 76);
INSERT INTO `sys_link_role_menu` VALUES (1, 77);
INSERT INTO `sys_link_role_menu` VALUES (1, 78);
INSERT INTO `sys_link_role_menu` VALUES (1, 79);
INSERT INTO `sys_link_role_menu` VALUES (1, 80);
INSERT INTO `sys_link_role_menu` VALUES (1, 81);
INSERT INTO `sys_link_role_menu` VALUES (1, 82);
INSERT INTO `sys_link_role_menu` VALUES (1, 92);
INSERT INTO `sys_link_role_menu` VALUES (1, 93);
INSERT INTO `sys_link_role_menu` VALUES (1, 94);
INSERT INTO `sys_link_role_menu` VALUES (1, 95);
INSERT INTO `sys_link_role_menu` VALUES (1, 96);
INSERT INTO `sys_link_role_menu` VALUES (1, 97);
INSERT INTO `sys_link_role_menu` VALUES (1, 98);
INSERT INTO `sys_link_role_menu` VALUES (1, 99);
INSERT INTO `sys_link_role_menu` VALUES (1, 100);
INSERT INTO `sys_link_role_menu` VALUES (1, 101);
INSERT INTO `sys_link_role_menu` VALUES (1, 102);
INSERT INTO `sys_link_role_menu` VALUES (1, 103);
INSERT INTO `sys_link_role_menu` VALUES (1, 104);
INSERT INTO `sys_link_role_menu` VALUES (1, 105);

-- ----------------------------
-- Table structure for sys_link_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_link_role_resource`;
CREATE TABLE `sys_link_role_resource`  (
  `role_id` bigint UNSIGNED NOT NULL,
  `resource_id` bigint UNSIGNED NOT NULL,
  PRIMARY KEY (`role_id`, `resource_id`) USING BTREE,
  UNIQUE INDEX `role_id`(`role_id`, `resource_id`) USING BTREE COMMENT '联合唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '关联表（角色-资源）' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_link_role_resource
-- ----------------------------
INSERT INTO `sys_link_role_resource` VALUES (1, 1);
INSERT INTO `sys_link_role_resource` VALUES (1, 2);
INSERT INTO `sys_link_role_resource` VALUES (1, 3);
INSERT INTO `sys_link_role_resource` VALUES (1, 4);
INSERT INTO `sys_link_role_resource` VALUES (1, 93);
INSERT INTO `sys_link_role_resource` VALUES (1, 94);
INSERT INTO `sys_link_role_resource` VALUES (1, 96);
INSERT INTO `sys_link_role_resource` VALUES (1, 97);
INSERT INTO `sys_link_role_resource` VALUES (1, 98);
INSERT INTO `sys_link_role_resource` VALUES (1, 110);
INSERT INTO `sys_link_role_resource` VALUES (1, 111);
INSERT INTO `sys_link_role_resource` VALUES (1, 112);
INSERT INTO `sys_link_role_resource` VALUES (1, 113);
INSERT INTO `sys_link_role_resource` VALUES (1, 114);
INSERT INTO `sys_link_role_resource` VALUES (1, 115);
INSERT INTO `sys_link_role_resource` VALUES (1, 116);
INSERT INTO `sys_link_role_resource` VALUES (1, 118);
INSERT INTO `sys_link_role_resource` VALUES (1, 120);
INSERT INTO `sys_link_role_resource` VALUES (1, 121);
INSERT INTO `sys_link_role_resource` VALUES (1, 122);
INSERT INTO `sys_link_role_resource` VALUES (1, 123);
INSERT INTO `sys_link_role_resource` VALUES (1, 124);
INSERT INTO `sys_link_role_resource` VALUES (1, 125);
INSERT INTO `sys_link_role_resource` VALUES (1, 126);
INSERT INTO `sys_link_role_resource` VALUES (1, 127);
INSERT INTO `sys_link_role_resource` VALUES (1, 128);
INSERT INTO `sys_link_role_resource` VALUES (1, 129);
INSERT INTO `sys_link_role_resource` VALUES (1, 130);
INSERT INTO `sys_link_role_resource` VALUES (1, 131);
INSERT INTO `sys_link_role_resource` VALUES (1, 132);
INSERT INTO `sys_link_role_resource` VALUES (1, 137);
INSERT INTO `sys_link_role_resource` VALUES (1, 138);
INSERT INTO `sys_link_role_resource` VALUES (1, 139);
INSERT INTO `sys_link_role_resource` VALUES (1, 197);
INSERT INTO `sys_link_role_resource` VALUES (1, 199);
INSERT INTO `sys_link_role_resource` VALUES (1, 200);
INSERT INTO `sys_link_role_resource` VALUES (1, 201);
INSERT INTO `sys_link_role_resource` VALUES (1, 202);
INSERT INTO `sys_link_role_resource` VALUES (1, 203);
INSERT INTO `sys_link_role_resource` VALUES (1, 204);
INSERT INTO `sys_link_role_resource` VALUES (1, 206);
INSERT INTO `sys_link_role_resource` VALUES (1, 207);
INSERT INTO `sys_link_role_resource` VALUES (1, 210);
INSERT INTO `sys_link_role_resource` VALUES (1, 211);
INSERT INTO `sys_link_role_resource` VALUES (1, 212);
INSERT INTO `sys_link_role_resource` VALUES (1, 213);
INSERT INTO `sys_link_role_resource` VALUES (1, 214);
INSERT INTO `sys_link_role_resource` VALUES (1, 215);
INSERT INTO `sys_link_role_resource` VALUES (1, 216);
INSERT INTO `sys_link_role_resource` VALUES (1, 217);
INSERT INTO `sys_link_role_resource` VALUES (1, 218);
INSERT INTO `sys_link_role_resource` VALUES (1, 219);
INSERT INTO `sys_link_role_resource` VALUES (1, 220);
INSERT INTO `sys_link_role_resource` VALUES (1, 221);
INSERT INTO `sys_link_role_resource` VALUES (1, 222);
INSERT INTO `sys_link_role_resource` VALUES (1, 223);
INSERT INTO `sys_link_role_resource` VALUES (1, 224);
INSERT INTO `sys_link_role_resource` VALUES (1, 225);
INSERT INTO `sys_link_role_resource` VALUES (1, 226);
INSERT INTO `sys_link_role_resource` VALUES (1, 227);
INSERT INTO `sys_link_role_resource` VALUES (1, 235);
INSERT INTO `sys_link_role_resource` VALUES (1, 236);
INSERT INTO `sys_link_role_resource` VALUES (1, 237);
INSERT INTO `sys_link_role_resource` VALUES (1, 245);
INSERT INTO `sys_link_role_resource` VALUES (1, 246);
INSERT INTO `sys_link_role_resource` VALUES (1, 247);
INSERT INTO `sys_link_role_resource` VALUES (1, 248);
INSERT INTO `sys_link_role_resource` VALUES (1, 249);
INSERT INTO `sys_link_role_resource` VALUES (1, 250);
INSERT INTO `sys_link_role_resource` VALUES (1, 251);
INSERT INTO `sys_link_role_resource` VALUES (1, 252);
INSERT INTO `sys_link_role_resource` VALUES (1, 253);
INSERT INTO `sys_link_role_resource` VALUES (1, 254);
INSERT INTO `sys_link_role_resource` VALUES (1, 255);
INSERT INTO `sys_link_role_resource` VALUES (1, 256);
INSERT INTO `sys_link_role_resource` VALUES (1, 257);
INSERT INTO `sys_link_role_resource` VALUES (1, 258);
INSERT INTO `sys_link_role_resource` VALUES (1, 259);
INSERT INTO `sys_link_role_resource` VALUES (1, 260);
INSERT INTO `sys_link_role_resource` VALUES (1, 261);
INSERT INTO `sys_link_role_resource` VALUES (1, 272);
INSERT INTO `sys_link_role_resource` VALUES (1, 273);
INSERT INTO `sys_link_role_resource` VALUES (1, 274);
INSERT INTO `sys_link_role_resource` VALUES (1, 275);
INSERT INTO `sys_link_role_resource` VALUES (1, 276);
INSERT INTO `sys_link_role_resource` VALUES (1, 277);
INSERT INTO `sys_link_role_resource` VALUES (1, 280);
INSERT INTO `sys_link_role_resource` VALUES (1, 283);
INSERT INTO `sys_link_role_resource` VALUES (1, 284);
INSERT INTO `sys_link_role_resource` VALUES (1, 289);
INSERT INTO `sys_link_role_resource` VALUES (1, 290);
INSERT INTO `sys_link_role_resource` VALUES (1, 293);
INSERT INTO `sys_link_role_resource` VALUES (1, 296);
INSERT INTO `sys_link_role_resource` VALUES (1, 297);
INSERT INTO `sys_link_role_resource` VALUES (1, 298);
INSERT INTO `sys_link_role_resource` VALUES (1, 299);
INSERT INTO `sys_link_role_resource` VALUES (1, 300);
INSERT INTO `sys_link_role_resource` VALUES (1, 301);
INSERT INTO `sys_link_role_resource` VALUES (1, 302);
INSERT INTO `sys_link_role_resource` VALUES (1, 303);
INSERT INTO `sys_link_role_resource` VALUES (1, 304);
INSERT INTO `sys_link_role_resource` VALUES (2, 216);
INSERT INTO `sys_link_role_resource` VALUES (2, 219);
INSERT INTO `sys_link_role_resource` VALUES (2, 221);
INSERT INTO `sys_link_role_resource` VALUES (2, 223);
INSERT INTO `sys_link_role_resource` VALUES (2, 226);
INSERT INTO `sys_link_role_resource` VALUES (2, 236);
INSERT INTO `sys_link_role_resource` VALUES (2, 246);
INSERT INTO `sys_link_role_resource` VALUES (2, 247);
INSERT INTO `sys_link_role_resource` VALUES (2, 250);
INSERT INTO `sys_link_role_resource` VALUES (2, 252);
INSERT INTO `sys_link_role_resource` VALUES (2, 254);
INSERT INTO `sys_link_role_resource` VALUES (2, 257);
INSERT INTO `sys_link_role_resource` VALUES (2, 259);
INSERT INTO `sys_link_role_resource` VALUES (2, 260);
INSERT INTO `sys_link_role_resource` VALUES (2, 275);

-- ----------------------------
-- Table structure for sys_link_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_link_role_user`;
CREATE TABLE `sys_link_role_user`  (
  `role_id` bigint UNSIGNED NOT NULL,
  `user_id` bigint UNSIGNED NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  UNIQUE INDEX `menu_id`(`user_id`, `role_id`) USING BTREE COMMENT '联合唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '关联表（角色-菜单）' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_link_role_user
-- ----------------------------
INSERT INTO `sys_link_role_user` VALUES (1, 1);
INSERT INTO `sys_link_role_user` VALUES (2, 2);
INSERT INTO `sys_link_role_user` VALUES (1, 3);
INSERT INTO `sys_link_role_user` VALUES (2, 30);
INSERT INTO `sys_link_role_user` VALUES (2, 31);
INSERT INTO `sys_link_role_user` VALUES (2, 32);
INSERT INTO `sys_link_role_user` VALUES (2, 33);
INSERT INTO `sys_link_role_user` VALUES (2, 35);
INSERT INTO `sys_link_role_user` VALUES (2, 37);
INSERT INTO `sys_link_role_user` VALUES (2, 39);
INSERT INTO `sys_link_role_user` VALUES (2, 40);
INSERT INTO `sys_link_role_user` VALUES (2, 41);
INSERT INTO `sys_link_role_user` VALUES (2, 42);
INSERT INTO `sys_link_role_user` VALUES (2, 43);
INSERT INTO `sys_link_role_user` VALUES (2, 44);
INSERT INTO `sys_link_role_user` VALUES (2, 45);
INSERT INTO `sys_link_role_user` VALUES (2, 46);
INSERT INTO `sys_link_role_user` VALUES (2, 47);
INSERT INTO `sys_link_role_user` VALUES (2, 48);
INSERT INTO `sys_link_role_user` VALUES (2, 49);
INSERT INTO `sys_link_role_user` VALUES (2, 50);
INSERT INTO `sys_link_role_user` VALUES (2, 51);
INSERT INTO `sys_link_role_user` VALUES (2, 52);
INSERT INTO `sys_link_role_user` VALUES (2, 53);
INSERT INTO `sys_link_role_user` VALUES (2, 54);
INSERT INTO `sys_link_role_user` VALUES (2, 55);
INSERT INTO `sys_link_role_user` VALUES (2, 56);
INSERT INTO `sys_link_role_user` VALUES (2, 57);
INSERT INTO `sys_link_role_user` VALUES (2, 58);
INSERT INTO `sys_link_role_user` VALUES (2, 59);
INSERT INTO `sys_link_role_user` VALUES (2, 60);
INSERT INTO `sys_link_role_user` VALUES (2, 61);
INSERT INTO `sys_link_role_user` VALUES (2, 62);
INSERT INTO `sys_link_role_user` VALUES (2, 63);
INSERT INTO `sys_link_role_user` VALUES (2, 64);
INSERT INTO `sys_link_role_user` VALUES (2, 65);
INSERT INTO `sys_link_role_user` VALUES (2, 66);
INSERT INTO `sys_link_role_user` VALUES (2, 67);
INSERT INTO `sys_link_role_user` VALUES (2, 68);
INSERT INTO `sys_link_role_user` VALUES (2, 69);
INSERT INTO `sys_link_role_user` VALUES (2, 70);
INSERT INTO `sys_link_role_user` VALUES (2, 71);
INSERT INTO `sys_link_role_user` VALUES (2, 72);

-- ----------------------------
-- Table structure for sys_log_login
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_login`;
CREATE TABLE `sys_log_login`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '用户ID',
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录IP',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 186 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '一张模板表-新建表时，复制这张表然后改名' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_log_login
-- ----------------------------
INSERT INTO `sys_log_login` VALUES (1, 1, '10.0.1.249', '2020-04-27 09:49:09');
INSERT INTO `sys_log_login` VALUES (2, 1, '10.0.1.249', '2020-04-27 09:54:44');
INSERT INTO `sys_log_login` VALUES (3, 1, '10.0.1.249', '2020-04-27 10:01:51');
INSERT INTO `sys_log_login` VALUES (4, 1, '10.0.1.249', '2020-04-27 10:45:04');
INSERT INTO `sys_log_login` VALUES (5, 1, '127.0.0.1', '2020-04-27 11:22:43');
INSERT INTO `sys_log_login` VALUES (6, 1, '127.0.0.1', '2020-04-27 13:01:29');
INSERT INTO `sys_log_login` VALUES (7, 1, '127.0.0.1', '2020-04-27 13:35:28');
INSERT INTO `sys_log_login` VALUES (8, 1, '127.0.0.1', '2020-04-27 13:37:57');
INSERT INTO `sys_log_login` VALUES (9, 1, '127.0.0.1', '2020-04-27 13:38:14');
INSERT INTO `sys_log_login` VALUES (10, 1, '127.0.0.1', '2020-04-27 16:52:08');
INSERT INTO `sys_log_login` VALUES (11, 1, '127.0.0.1', '2020-04-28 10:06:07');
INSERT INTO `sys_log_login` VALUES (12, 1, '127.0.0.1', '2020-04-28 14:11:09');
INSERT INTO `sys_log_login` VALUES (13, 1, '127.0.0.1', '2020-04-28 15:19:49');
INSERT INTO `sys_log_login` VALUES (14, 1, '127.0.0.1', '2020-04-28 15:20:22');
INSERT INTO `sys_log_login` VALUES (15, 1, '127.0.0.1', '2020-04-28 15:27:59');
INSERT INTO `sys_log_login` VALUES (16, 1, '106.41.69.25', '2020-04-29 16:36:36');
INSERT INTO `sys_log_login` VALUES (17, 1, '106.41.69.25', '2020-04-29 16:56:29');
INSERT INTO `sys_log_login` VALUES (18, 1, '106.41.69.25', '2020-04-30 08:24:21');
INSERT INTO `sys_log_login` VALUES (19, 47, '106.41.69.25', '2020-04-30 08:26:44');
INSERT INTO `sys_log_login` VALUES (20, 1, '106.41.69.25', '2020-04-30 08:29:41');
INSERT INTO `sys_log_login` VALUES (21, 47, '106.41.69.25', '2020-04-30 08:40:00');
INSERT INTO `sys_log_login` VALUES (22, 47, '106.41.69.25', '2020-04-30 08:40:44');
INSERT INTO `sys_log_login` VALUES (23, 47, '117.136.59.130', '2020-04-30 08:42:11');
INSERT INTO `sys_log_login` VALUES (24, 1, '106.41.69.25', '2020-04-30 09:10:53');
INSERT INTO `sys_log_login` VALUES (25, 1, '106.41.69.25', '2020-04-30 15:42:13');
INSERT INTO `sys_log_login` VALUES (26, 1, '106.41.69.25', '2020-04-30 16:45:09');
INSERT INTO `sys_log_login` VALUES (27, 1, '106.41.69.25', '2020-04-30 16:47:04');
INSERT INTO `sys_log_login` VALUES (28, 1, '106.41.69.25', '2020-04-30 16:53:19');
INSERT INTO `sys_log_login` VALUES (29, 1, '106.41.69.25', '2020-04-30 16:53:53');
INSERT INTO `sys_log_login` VALUES (30, 1, '106.41.69.25', '2020-05-06 13:20:54');
INSERT INTO `sys_log_login` VALUES (31, 1, '106.41.69.25', '2020-05-06 16:20:24');
INSERT INTO `sys_log_login` VALUES (32, 1, '106.41.69.25', '2020-05-09 10:22:59');
INSERT INTO `sys_log_login` VALUES (33, 47, '106.41.69.25', '2020-05-09 10:23:22');
INSERT INTO `sys_log_login` VALUES (34, 1, '106.41.69.25', '2020-05-09 10:27:31');
INSERT INTO `sys_log_login` VALUES (35, 1, '139.212.98.250', '2020-05-09 13:07:28');
INSERT INTO `sys_log_login` VALUES (36, 47, '219.149.214.162', '2020-05-09 16:27:01');
INSERT INTO `sys_log_login` VALUES (37, 1, '219.149.214.162', '2020-05-09 16:28:02');
INSERT INTO `sys_log_login` VALUES (38, 1, '106.41.69.25', '2020-05-09 16:31:34');
INSERT INTO `sys_log_login` VALUES (39, 1, '106.41.69.25', '2020-05-09 16:35:08');
INSERT INTO `sys_log_login` VALUES (40, 47, '219.149.214.162', '2020-05-09 16:47:01');
INSERT INTO `sys_log_login` VALUES (41, 1, '106.41.69.25', '2020-05-09 16:49:03');
INSERT INTO `sys_log_login` VALUES (42, 1, '219.149.214.162', '2020-05-09 16:52:27');
INSERT INTO `sys_log_login` VALUES (43, 1, '106.41.69.25', '2020-05-09 16:53:36');
INSERT INTO `sys_log_login` VALUES (44, 1, '219.149.214.162', '2020-05-11 13:14:24');
INSERT INTO `sys_log_login` VALUES (45, 1, '175.17.167.52', '2020-05-12 09:08:35');
INSERT INTO `sys_log_login` VALUES (46, 1, '175.17.167.52', '2020-05-12 09:09:11');
INSERT INTO `sys_log_login` VALUES (47, 1, '175.17.167.52', '2020-05-12 09:10:13');
INSERT INTO `sys_log_login` VALUES (48, 1, '175.17.167.52', '2020-05-12 09:13:52');
INSERT INTO `sys_log_login` VALUES (49, 47, '175.17.167.52', '2020-05-12 09:15:26');
INSERT INTO `sys_log_login` VALUES (50, 47, '175.17.167.52', '2020-05-12 09:16:03');
INSERT INTO `sys_log_login` VALUES (51, 1, '175.17.167.52', '2020-05-12 09:16:52');
INSERT INTO `sys_log_login` VALUES (52, 1, '175.17.167.52', '2020-05-12 09:17:40');
INSERT INTO `sys_log_login` VALUES (53, 1, '175.17.167.52', '2020-05-12 09:27:47');
INSERT INTO `sys_log_login` VALUES (54, 47, '175.17.167.52', '2020-05-12 09:37:53');
INSERT INTO `sys_log_login` VALUES (55, 1, '175.17.167.52', '2020-05-12 09:43:40');
INSERT INTO `sys_log_login` VALUES (56, 48, '175.17.167.52', '2020-05-12 09:45:15');
INSERT INTO `sys_log_login` VALUES (57, 50, '175.17.167.52', '2020-05-12 09:46:36');
INSERT INTO `sys_log_login` VALUES (58, 1, '175.17.167.52', '2020-05-12 09:49:06');
INSERT INTO `sys_log_login` VALUES (59, 1, '175.17.167.52', '2020-05-12 09:51:59');
INSERT INTO `sys_log_login` VALUES (60, 1, '175.17.167.52', '2020-05-12 09:54:39');
INSERT INTO `sys_log_login` VALUES (61, 1, '175.17.167.52', '2020-05-12 10:04:07');
INSERT INTO `sys_log_login` VALUES (62, 50, '175.17.167.52', '2020-05-12 10:10:50');
INSERT INTO `sys_log_login` VALUES (63, 47, '175.17.167.52', '2020-05-12 10:13:50');
INSERT INTO `sys_log_login` VALUES (64, 1, '175.17.167.52', '2020-05-12 10:15:00');
INSERT INTO `sys_log_login` VALUES (65, 51, '175.17.167.52', '2020-05-12 10:22:00');
INSERT INTO `sys_log_login` VALUES (66, 1, '106.41.69.25', '2020-05-12 10:25:02');
INSERT INTO `sys_log_login` VALUES (67, 1, '175.17.167.52', '2020-05-12 10:25:15');
INSERT INTO `sys_log_login` VALUES (68, 1, '175.17.167.52', '2020-05-12 10:30:44');
INSERT INTO `sys_log_login` VALUES (69, 1, '175.17.167.52', '2020-05-12 10:36:03');
INSERT INTO `sys_log_login` VALUES (70, 59, '175.17.167.52', '2020-05-12 10:37:10');
INSERT INTO `sys_log_login` VALUES (71, 1, '175.17.167.52', '2020-05-12 10:41:11');
INSERT INTO `sys_log_login` VALUES (72, 47, '175.17.167.52', '2020-05-12 10:43:33');
INSERT INTO `sys_log_login` VALUES (73, 1, '175.17.167.52', '2020-05-12 10:44:35');
INSERT INTO `sys_log_login` VALUES (74, 1, '175.17.167.52', '2020-05-12 10:45:06');
INSERT INTO `sys_log_login` VALUES (75, 47, '175.17.167.52', '2020-05-12 10:56:51');
INSERT INTO `sys_log_login` VALUES (76, 54, '175.17.167.52', '2020-05-12 11:14:46');
INSERT INTO `sys_log_login` VALUES (77, 1, '175.17.167.52', '2020-05-12 12:45:03');
INSERT INTO `sys_log_login` VALUES (78, 1, '175.17.167.52', '2020-05-12 13:24:47');
INSERT INTO `sys_log_login` VALUES (79, 1, '175.17.167.52', '2020-05-12 13:31:48');
INSERT INTO `sys_log_login` VALUES (80, 1, '175.17.167.52', '2020-05-12 13:49:14');
INSERT INTO `sys_log_login` VALUES (81, 1, '106.41.69.25', '2020-05-12 13:53:28');
INSERT INTO `sys_log_login` VALUES (82, 1, '106.41.69.25', '2020-05-12 16:20:55');
INSERT INTO `sys_log_login` VALUES (83, 1, '106.41.69.25', '2020-05-12 17:23:01');
INSERT INTO `sys_log_login` VALUES (84, 1, '106.41.69.25', '2020-05-14 08:25:27');
INSERT INTO `sys_log_login` VALUES (85, 1, '106.41.69.25', '2020-05-14 08:32:20');
INSERT INTO `sys_log_login` VALUES (86, 1, '10.0.1.249', '2020-05-14 09:56:52');
INSERT INTO `sys_log_login` VALUES (87, 1, '10.0.1.249', '2020-05-14 10:03:16');
INSERT INTO `sys_log_login` VALUES (88, 1, '10.0.1.249', '2020-05-14 10:17:54');
INSERT INTO `sys_log_login` VALUES (89, 1, '10.0.1.249', '2020-05-14 10:24:18');
INSERT INTO `sys_log_login` VALUES (90, 1, '10.0.1.249', '2020-05-14 10:30:49');
INSERT INTO `sys_log_login` VALUES (91, 1, '10.0.1.249', '2020-05-14 10:33:54');
INSERT INTO `sys_log_login` VALUES (92, 48, '10.0.1.249', '2020-05-14 10:34:21');
INSERT INTO `sys_log_login` VALUES (93, 1, '10.0.1.249', '2020-05-14 10:35:38');
INSERT INTO `sys_log_login` VALUES (94, 48, '10.0.1.249', '2020-05-14 10:36:00');
INSERT INTO `sys_log_login` VALUES (95, 1, '10.0.1.249', '2020-05-14 10:36:13');
INSERT INTO `sys_log_login` VALUES (96, 1, '10.0.1.249', '2020-05-14 10:38:38');
INSERT INTO `sys_log_login` VALUES (97, 1, '10.0.1.249', '2020-05-14 10:39:17');
INSERT INTO `sys_log_login` VALUES (98, 72, '10.0.1.249', '2020-05-14 10:39:53');
INSERT INTO `sys_log_login` VALUES (99, 1, '10.0.1.249', '2020-05-14 10:40:27');
INSERT INTO `sys_log_login` VALUES (100, 1, '106.41.69.25', '2020-05-14 10:52:45');
INSERT INTO `sys_log_login` VALUES (101, 1, '106.41.69.25', '2020-05-14 10:53:38');
INSERT INTO `sys_log_login` VALUES (102, 50, '106.41.69.25', '2020-05-14 10:54:39');
INSERT INTO `sys_log_login` VALUES (103, 1, '10.0.1.249', '2020-05-14 13:06:36');
INSERT INTO `sys_log_login` VALUES (104, 1, '10.0.1.249', '2020-05-14 13:31:06');
INSERT INTO `sys_log_login` VALUES (105, 48, '10.0.1.249', '2020-05-14 14:24:59');
INSERT INTO `sys_log_login` VALUES (106, 1, '10.0.1.249', '2020-05-14 14:28:13');
INSERT INTO `sys_log_login` VALUES (107, 48, '10.0.1.249', '2020-05-14 14:28:29');
INSERT INTO `sys_log_login` VALUES (108, 1, '10.0.1.249', '2020-05-14 14:30:24');
INSERT INTO `sys_log_login` VALUES (109, 48, '10.0.1.249', '2020-05-14 14:32:18');
INSERT INTO `sys_log_login` VALUES (110, 48, '10.0.1.249', '2020-05-14 14:34:11');
INSERT INTO `sys_log_login` VALUES (111, 1, '10.0.1.249', '2020-05-14 14:40:47');
INSERT INTO `sys_log_login` VALUES (112, 1, '10.0.1.249', '2020-05-14 14:42:13');
INSERT INTO `sys_log_login` VALUES (113, 1, '10.0.1.249', '2020-05-14 14:49:12');
INSERT INTO `sys_log_login` VALUES (114, 1, '10.0.1.249', '2020-05-14 14:49:52');
INSERT INTO `sys_log_login` VALUES (115, 48, '10.0.1.249', '2020-05-14 14:51:50');
INSERT INTO `sys_log_login` VALUES (116, 48, '10.0.1.249', '2020-05-14 14:55:16');
INSERT INTO `sys_log_login` VALUES (117, 1, '10.0.1.249', '2020-05-14 15:16:14');
INSERT INTO `sys_log_login` VALUES (118, 1, '10.0.1.249', '2020-05-14 15:19:56');
INSERT INTO `sys_log_login` VALUES (119, 48, '10.0.1.249', '2020-05-14 15:38:27');
INSERT INTO `sys_log_login` VALUES (120, 48, '10.0.1.249', '2020-05-14 15:49:04');
INSERT INTO `sys_log_login` VALUES (121, 1, '10.0.1.249', '2020-05-14 15:59:13');
INSERT INTO `sys_log_login` VALUES (122, 48, '10.0.1.249', '2020-05-14 17:17:19');
INSERT INTO `sys_log_login` VALUES (123, 1, '10.0.1.249', '2020-05-15 08:25:35');
INSERT INTO `sys_log_login` VALUES (124, 48, '10.0.1.249', '2020-05-15 08:29:30');
INSERT INTO `sys_log_login` VALUES (125, 1, '10.0.1.249', '2020-05-15 09:17:34');
INSERT INTO `sys_log_login` VALUES (126, 1, '10.0.1.249', '2020-05-15 11:17:55');
INSERT INTO `sys_log_login` VALUES (127, 48, '10.0.1.249', '2020-05-15 11:18:26');
INSERT INTO `sys_log_login` VALUES (128, 1, '10.0.1.249', '2020-05-15 11:18:38');
INSERT INTO `sys_log_login` VALUES (129, 1, '10.0.1.249', '2020-05-15 12:04:48');
INSERT INTO `sys_log_login` VALUES (130, 48, '10.0.1.249', '2020-05-15 12:05:08');
INSERT INTO `sys_log_login` VALUES (131, 48, '10.0.1.249', '2020-05-15 12:05:33');
INSERT INTO `sys_log_login` VALUES (132, 1, '10.0.1.249', '2020-05-15 12:06:04');
INSERT INTO `sys_log_login` VALUES (133, 1, '139.212.118.130', '2020-05-15 12:11:20');
INSERT INTO `sys_log_login` VALUES (134, 48, '139.212.118.130', '2020-05-15 12:11:52');
INSERT INTO `sys_log_login` VALUES (135, 1, '139.212.118.130', '2020-05-15 12:12:00');
INSERT INTO `sys_log_login` VALUES (136, 1, '139.212.118.130', '2020-05-15 12:18:17');
INSERT INTO `sys_log_login` VALUES (137, 48, '139.212.118.130', '2020-05-15 12:18:28');
INSERT INTO `sys_log_login` VALUES (138, 1, '139.212.118.130', '2020-05-15 12:18:35');
INSERT INTO `sys_log_login` VALUES (139, 48, '139.212.118.130', '2020-05-15 13:58:12');
INSERT INTO `sys_log_login` VALUES (140, 48, '139.212.118.130', '2020-05-15 14:00:06');
INSERT INTO `sys_log_login` VALUES (141, 48, '139.212.118.130', '2020-05-15 14:00:15');
INSERT INTO `sys_log_login` VALUES (142, 1, '139.212.118.130', '2020-05-15 14:00:28');
INSERT INTO `sys_log_login` VALUES (143, 48, '139.212.118.130', '2020-05-15 14:00:50');
INSERT INTO `sys_log_login` VALUES (144, 1, '139.212.118.130', '2020-05-15 14:00:58');
INSERT INTO `sys_log_login` VALUES (145, 1, '10.0.1.249', '2020-05-18 08:16:46');
INSERT INTO `sys_log_login` VALUES (146, 1, '10.0.1.249', '2020-05-18 09:17:14');
INSERT INTO `sys_log_login` VALUES (147, 48, '10.0.1.249', '2020-05-18 09:17:26');
INSERT INTO `sys_log_login` VALUES (148, 48, '175.17.199.217', '2020-05-18 09:17:39');
INSERT INTO `sys_log_login` VALUES (149, 48, '10.0.1.249', '2020-05-18 09:18:02');
INSERT INTO `sys_log_login` VALUES (150, 1, '10.0.1.249', '2020-05-18 10:12:29');
INSERT INTO `sys_log_login` VALUES (151, 1, '10.0.1.249', '2020-05-18 10:13:56');
INSERT INTO `sys_log_login` VALUES (152, 1, '10.0.1.249', '2020-05-18 10:51:49');
INSERT INTO `sys_log_login` VALUES (153, 48, '10.0.1.249', '2020-05-18 11:09:31');
INSERT INTO `sys_log_login` VALUES (154, 48, '10.0.1.249', '2020-05-18 11:26:56');
INSERT INTO `sys_log_login` VALUES (155, 48, '10.0.1.249', '2020-05-18 13:12:45');
INSERT INTO `sys_log_login` VALUES (156, 1, '10.0.1.249', '2020-05-18 13:13:03');
INSERT INTO `sys_log_login` VALUES (157, 1, '192.168.74.194', '2020-05-18 13:28:09');
INSERT INTO `sys_log_login` VALUES (158, 1, '10.0.1.249', '2020-05-18 13:33:06');
INSERT INTO `sys_log_login` VALUES (159, 1, '192.168.74.194', '2020-05-18 15:29:34');
INSERT INTO `sys_log_login` VALUES (160, 1, '10.0.1.249', '2020-05-18 15:34:03');
INSERT INTO `sys_log_login` VALUES (161, 1, '175.17.199.217', '2020-05-18 16:00:56');
INSERT INTO `sys_log_login` VALUES (162, 1, '10.0.1.249', '2020-05-18 16:07:41');
INSERT INTO `sys_log_login` VALUES (163, 1, '10.0.1.249', '2020-05-18 16:11:38');
INSERT INTO `sys_log_login` VALUES (164, 1, '192.168.74.194', '2020-05-18 17:00:35');
INSERT INTO `sys_log_login` VALUES (165, 1, '192.168.74.194', '2020-05-18 20:10:53');
INSERT INTO `sys_log_login` VALUES (166, 1, '192.168.74.194', '2020-05-18 22:21:29');
INSERT INTO `sys_log_login` VALUES (167, 1, '192.168.74.194', '2020-05-19 00:22:57');
INSERT INTO `sys_log_login` VALUES (168, 47, '192.168.74.194', '2020-05-19 00:40:06');
INSERT INTO `sys_log_login` VALUES (169, 1, '10.0.1.249', '2020-05-19 08:41:19');
INSERT INTO `sys_log_login` VALUES (170, 1, '10.0.1.249', '2020-05-19 08:41:34');
INSERT INTO `sys_log_login` VALUES (171, 1, '192.168.74.194', '2020-05-19 09:06:20');
INSERT INTO `sys_log_login` VALUES (172, 47, '192.168.74.194', '2020-05-19 09:06:44');
INSERT INTO `sys_log_login` VALUES (173, 1, '10.0.1.249', '2020-05-19 10:34:35');
INSERT INTO `sys_log_login` VALUES (174, 1, '10.0.1.249', '2020-05-19 10:34:44');
INSERT INTO `sys_log_login` VALUES (175, 1, '10.0.1.249', '2020-05-19 10:58:54');
INSERT INTO `sys_log_login` VALUES (176, 1, '10.0.1.249', '2020-05-19 11:21:29');
INSERT INTO `sys_log_login` VALUES (177, 1, '10.0.1.249', '2020-05-19 11:21:50');
INSERT INTO `sys_log_login` VALUES (178, 1, '10.0.1.249', '2020-05-19 11:29:15');
INSERT INTO `sys_log_login` VALUES (179, 1, '175.17.199.217', '2020-05-19 11:44:49');
INSERT INTO `sys_log_login` VALUES (180, 48, '175.17.199.217', '2020-05-19 11:45:55');
INSERT INTO `sys_log_login` VALUES (181, 48, '175.17.199.217', '2020-05-19 11:48:56');
INSERT INTO `sys_log_login` VALUES (182, 1, '175.17.199.217', '2020-05-19 14:54:04');
INSERT INTO `sys_log_login` VALUES (183, 1, '175.17.199.217', '2020-05-19 14:56:32');
INSERT INTO `sys_log_login` VALUES (184, 1, '175.17.199.217', '2020-05-19 15:02:16');
INSERT INTO `sys_log_login` VALUES (185, 1, '175.17.199.217', '2020-05-19 15:09:06');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `pid` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '父菜单ID',
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '访问路径',
  `redirect` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '重定向地址',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件名',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'simple' COMMENT '图标',
  `hidden` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '展示状态（1=展示，2=隐藏）',
  `always_show` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '2' COMMENT '1=始终显示根菜单\r\n2=当子路由小于等于1时，隐藏根菜单',
  `add_tag` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '打开新的路由，是否添加TAG，默认添加（1=添加，2=不添加）',
  `affix` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签状态（1=可以关闭，2=不可以关闭）',
  `active_menu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '如果设置了路径，则侧边栏将突出显示您设置的路径',
  `breadcrumb` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '面包屑状态（1=展示，2=隐藏）',
  `no_cache` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '缓存（1=开启，2=关闭）',
  `parent_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留',
  `button_param` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留',
  `sort` int UNSIGNED NULL DEFAULT 100 COMMENT '排序(默认=1)',
  `create_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据创建者',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '数据创建时间',
  `update_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据修改者',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '数据更新时间',
  `data_states` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '数据状态(默认=0，删除=DELETED)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `path`(`path`) USING BTREE COMMENT '路由唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 108 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (68, 0, '/companyInfo', '/companyInfo/edit', '企业信息管理', NULL, 'excel', NULL, '2', NULL, NULL, NULL, '1', '1', NULL, NULL, 7000, 2, '2020-04-11 13:47:34', 1, '2020-05-14 14:28:19', '0');
INSERT INTO `sys_menu` VALUES (69, 68, '/companyInfo/edit', NULL, '企业信息', 'CompanyInfo', 'excel', NULL, '2', NULL, '2', NULL, '1', '1', '/companyInfo', NULL, 7000, 2, '2020-04-11 13:47:34', 1, '2020-05-14 14:28:19', '0');
INSERT INTO `sys_menu` VALUES (70, 0, '/dynamicHumanInfo', '/dynamicHumanInfo/edit', '动态人力资源管理', NULL, 'excel', NULL, '2', NULL, NULL, NULL, '1', '1', NULL, NULL, 7001, 2, '2020-04-11 13:47:34', 1, '2020-05-14 14:28:19', '0');
INSERT INTO `sys_menu` VALUES (71, 70, '/dynamicHumanInfo/edit', NULL, '动态人力资源信息', 'DynamicHumanInfo', 'excel', NULL, '2', NULL, NULL, NULL, '1', '1', '/dynamicHumanInfo', NULL, 7001, 2, '2020-04-11 13:47:34', 1, '2020-05-14 14:28:19', '0');
INSERT INTO `sys_menu` VALUES (72, 0, '/government', '/government/company/list', '企业管理', NULL, 'excel', NULL, '2', NULL, NULL, NULL, '1', '1', NULL, NULL, 8000, 2, '2020-04-11 13:47:34', 1, '2020-05-14 14:28:19', '0');
INSERT INTO `sys_menu` VALUES (73, 72, '/government/company/list', NULL, '企业列表', 'CompanyGList', NULL, NULL, '2', NULL, '2', NULL, '1', '1', '/government', NULL, 8100, 2, '2020-04-11 13:47:34', 1, '2020-05-14 14:28:19', '0');
INSERT INTO `sys_menu` VALUES (74, 72, '/government/industryType/list', NULL, '行业类别', 'IndustryTypeList', NULL, NULL, '2', NULL, NULL, NULL, '1', '1', '/government', NULL, 8100, 2, '2020-04-11 13:47:34', 1, '2020-05-14 14:28:19', '0');
INSERT INTO `sys_menu` VALUES (75, 0, '/inWorkHumanInfo', '/inWorkHumanInfo/edit', '员工基本情况', NULL, 'excel', NULL, '2', NULL, NULL, NULL, '1', '1', NULL, NULL, 7000, 2, '2020-04-11 13:47:34', 1, '2020-05-14 14:28:19', '0');
INSERT INTO `sys_menu` VALUES (76, 75, '/inWorkHumanInfo/edit', NULL, '员工基本情况', 'InWorkHumanInfo', 'excel', NULL, '2', NULL, NULL, NULL, '1', '1', '/inWorkHumanInfo', NULL, 7000, 2, '2020-04-11 13:47:34', 1, '2020-05-14 14:28:19', '0');
INSERT INTO `sys_menu` VALUES (77, 0, '/system', '/system/dict', '系统配置', NULL, 'excel', NULL, '2', NULL, NULL, NULL, '1', '1', NULL, NULL, 99999, 2, '2020-04-11 13:47:34', 1, '2020-05-14 14:28:19', '0');
INSERT INTO `sys_menu` VALUES (78, 77, '/system/dict/list', NULL, '字典管理', 'DictList', NULL, NULL, '2', NULL, NULL, NULL, '1', '1', '/system', NULL, 99999, 2, '2020-04-11 13:47:34', 1, '2020-05-14 14:28:19', '0');
INSERT INTO `sys_menu` VALUES (79, 77, '/system/menu/list', NULL, '菜单管理', 'MenuList', NULL, NULL, '2', NULL, NULL, NULL, '1', '1', '/system', NULL, 99999, 2, '2020-04-11 13:47:34', 1, '2020-05-14 14:28:19', '0');
INSERT INTO `sys_menu` VALUES (80, 77, '/system/resource/list', NULL, '资源管理', 'ResourceList', NULL, NULL, '2', NULL, NULL, NULL, '1', '1', '/system', NULL, 99999, 2, '2020-04-11 13:47:34', 1, '2020-05-14 14:28:19', '0');
INSERT INTO `sys_menu` VALUES (81, 77, '/system/role/list', NULL, '角色管理', 'RoleList', NULL, NULL, '2', NULL, NULL, NULL, '1', '1', '/system', NULL, 99999, 2, '2020-04-11 13:47:34', 1, '2020-05-14 14:28:19', '0');
INSERT INTO `sys_menu` VALUES (82, 77, '/system/user/list', NULL, '用户管理', 'UserList', NULL, NULL, '2', NULL, NULL, NULL, '1', '1', '/system', NULL, 99999, 2, '2020-04-11 13:47:34', 1, '2020-05-14 14:28:19', '0');
INSERT INTO `sys_menu` VALUES (92, 0, '/government/dynamic', '/government/dynamic/human/list', '动态人力资源信息管理', NULL, 'excel', '2', '2', NULL, NULL, NULL, '1', '1', NULL, NULL, 8200, 2, '2020-04-13 15:33:01', 44, '2020-04-15 15:24:14', '0');
INSERT INTO `sys_menu` VALUES (93, 92, '/government/dynamic/human/list', NULL, '动态人力资源信息管理', 'DynamicHumanGList', 'excel', '2', '2', NULL, NULL, NULL, '1', '1', '/government', NULL, 8200, 2, '2020-04-13 15:33:01', 1, '2020-05-14 14:28:19', '0');
INSERT INTO `sys_menu` VALUES (94, 0, '/government/inWork', '/government/inWork/human/list', '员工基本情况', NULL, 'excel', '2', '2', NULL, NULL, NULL, '1', '1', NULL, NULL, 8300, 2, '2020-04-13 15:33:01', 44, '2020-04-15 15:24:14', '0');
INSERT INTO `sys_menu` VALUES (95, 94, '/government/inWork/human/list', NULL, '员工基本情况', 'InWorkHumanGList', 'excel', '2', '2', NULL, NULL, NULL, '1', '1', '/government', NULL, 8300, 2, '2020-04-13 15:33:01', 1, '2020-04-28 15:28:01', '0');
INSERT INTO `sys_menu` VALUES (96, 0, '/government/recruitInfo', '/government/recruitInfo/edit', '招聘信息统计', NULL, 'excel', '2', '2', NULL, NULL, NULL, '1', '1', NULL, NULL, 8300, 44, '2020-04-15 15:21:14', 44, '2020-04-15 15:24:14', '0');
INSERT INTO `sys_menu` VALUES (97, 96, '/government/recruitInfo/edit', NULL, '用人单位招聘信息统计', 'RecruitInfoEdit', 'excel', '2', '2', NULL, NULL, NULL, '1', '1', '/government', NULL, 8300, 44, '2020-04-15 15:21:14', 1, '2020-05-14 14:28:19', '0');
INSERT INTO `sys_menu` VALUES (98, 0, '/statistics/chart', '/statistics/chart/CompanyTypeChart', '统计图管理', NULL, 'excel', NULL, '2', NULL, NULL, NULL, '1', '1', NULL, NULL, 11000, 44, '2020-04-15 15:21:14', 1, '2020-05-14 14:28:19', '0');
INSERT INTO `sys_menu` VALUES (99, 98, '/statistics/chart/CompanyTypeChart', NULL, '企业性质统计图', 'CompanyTypeChart', NULL, NULL, '2', NULL, NULL, NULL, '1', '1', '/statistics/chart', NULL, 11000, 44, '2020-04-15 15:21:14', 1, '2020-05-14 14:28:19', '0');
INSERT INTO `sys_menu` VALUES (100, 98, '/statistics/chart/IndustryTypeChart', NULL, '行业比较统计图', 'IndustryTypeChart', NULL, NULL, '2', NULL, NULL, NULL, '1', '1', '/statistics/chart', NULL, 11000, 44, '2020-04-15 15:21:14', 1, '2020-05-14 14:28:19', '0');
INSERT INTO `sys_menu` VALUES (101, 98, '/statistics/chart/UserPeopleChart', NULL, '企业用工动态统计图', 'UserPeopleChart', NULL, NULL, '2', NULL, NULL, NULL, '1', '1', '/statistics/chart', NULL, 11000, 44, '2020-04-15 15:21:14', 1, '2020-05-14 14:28:19', '0');
INSERT INTO `sys_menu` VALUES (102, 0, '/statistics/table', '/statistics/table/DynamicHumanTable', '统计表管理', NULL, 'excel', NULL, '2', NULL, NULL, NULL, '1', '1', NULL, NULL, 10000, 44, '2020-04-15 15:21:14', 1, '2020-05-14 14:28:19', '0');
INSERT INTO `sys_menu` VALUES (103, 102, '/statistics/table/DynamicHumanTable', NULL, '用工动态统计', 'DynamicHumanTable', NULL, NULL, '2', NULL, NULL, NULL, '1', '1', '/statistics/table', NULL, 10000, 44, '2020-04-15 15:21:14', 1, '2020-05-14 14:28:19', '0');
INSERT INTO `sys_menu` VALUES (104, 102, '/statistics/table/InWorkHumanTable', NULL, '员工基本情况统计', 'InWorkHumanTable', NULL, NULL, '2', NULL, NULL, NULL, '1', '1', '/statistics/table', NULL, 10000, 44, '2020-04-15 15:21:14', 1, '2020-05-14 14:28:19', '0');
INSERT INTO `sys_menu` VALUES (105, 102, '/statistics/table/RecruitInfoTable', NULL, '招聘情况反馈统计', 'RecruitInfoTable', NULL, NULL, '2', NULL, NULL, NULL, '1', '1', '/statistics/table', NULL, 10000, 44, '2020-04-15 15:21:14', 1, '2020-05-14 14:28:19', '0');
INSERT INTO `sys_menu` VALUES (106, 72, '/government/dynamic/human/edit', NULL, '动态人力资源信息管理', 'DynamicHumanGEdit', 'excel', '2', '2', '1', NULL, NULL, '1', '1', '/government', NULL, 8200, 1, '2020-05-14 14:28:19', 1, '2020-05-14 14:28:19', '0');
INSERT INTO `sys_menu` VALUES (107, 72, '/government/inWork/human/edit', NULL, '员工基本情况', 'InWorkHumanGEdit', 'excel', '2', '2', '1', NULL, NULL, '1', '1', '/government', NULL, 8300, 1, '2020-05-14 14:28:19', 1, '2020-05-14 14:28:19', '0');

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
) ENGINE = InnoDB AUTO_INCREMENT = 305 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES (1, 98, '分页查询用户', '/system/user/page', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (2, 98, '管理员修改自己的密码', '/system/user/updatePassword', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (3, 98, '账号登录', '/system/user/public/login', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (4, 98, '刷新登录状态', '/system/user/public/refreshToken', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (93, 94, '资源管理', '/system/resource', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (94, 0, '系统配置', '/system', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (96, 94, '菜单管理', '/system/menu', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (97, 94, '角色管理', '/system/role', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (98, 94, '用户管理', '/system/user', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (110, 96, '修改数据', '/system/menu/update', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (111, 96, '删除数据', '/system/menu/delete', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (112, 96, '列表查询', '/system/menu/list', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (113, 96, '添加数据', '/system/menu/insert', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (114, 96, '分页查询', '/system/menu/page', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (115, 96, '详情查询', '/system/menu/info', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (116, 93, '修改数据', '/system/resource/update', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (118, 93, '列表查询', '/system/resource/list', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (120, 93, '分页查询', '/system/resource/page', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (121, 93, '详情查询', '/system/resource/info', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (122, 97, '修改数据', '/system/role/update', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (123, 97, '删除数据', '/system/role/delete', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (124, 97, '列表查询', '/system/role/list', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (125, 97, '添加数据', '/system/role/insert', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (126, 97, '分页查询', '/system/role/page', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (127, 97, '详情查询', '/system/role/info', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (128, 94, '读取资源树', '/system/resource/tree', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (129, 98, '修改用户信息', '/system/user/update', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (130, 98, '删除用户', '/system/user/delete', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (131, 98, '查询用户列表', '/system/user/list', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (132, 98, '添加用户', '/system/user/insert', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (137, 98, '用户详情', '/system/user/info', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (138, 98, '重置用户密码', '/system/user/resetPassword', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (139, 94, '刷新角色资源缓存', '/system/refresh/roleResource', 100, 0, NULL, 0, '2020-03-10 13:39:59', '1', '2');
INSERT INTO `sys_resource` VALUES (197, 96, '提交前端路由数据', '/system/menu/submitMenu', 100, 0, '2020-03-10 14:26:52', 0, '2020-03-10 14:26:52', '1', '2');
INSERT INTO `sys_resource` VALUES (199, 94, '字典管理', '/system/dict', 100, 0, '2020-03-25 12:26:09', 0, '2020-03-25 12:26:09', '1', '2');
INSERT INTO `sys_resource` VALUES (200, 199, '修改字典', '/system/dict/update', 100, 0, '2020-03-25 12:26:09', 0, '2020-03-25 12:26:09', '1', '2');
INSERT INTO `sys_resource` VALUES (201, 199, '删除字典', '/system/dict/delete', 100, 0, '2020-03-25 12:26:09', 0, '2020-03-25 12:26:09', '1', '2');
INSERT INTO `sys_resource` VALUES (202, 199, '字典列表查询', '/system/dict/list', 100, 0, '2020-03-25 12:26:09', 0, '2020-03-25 12:26:09', '1', '2');
INSERT INTO `sys_resource` VALUES (203, 199, '添加字典', '/system/dict/insert', 100, 0, '2020-03-25 12:26:09', 0, '2020-03-25 12:26:09', '1', '2');
INSERT INTO `sys_resource` VALUES (204, 199, '字典分页查询', '/system/dict/page', 100, 0, '2020-03-25 12:26:09', 0, '2020-03-25 12:26:09', '1', '2');
INSERT INTO `sys_resource` VALUES (206, 199, '前端字典信息', '/system/dict/manage/dictList', 100, 0, '2020-03-25 12:26:09', 0, '2020-03-25 12:26:09', '1', '2');
INSERT INTO `sys_resource` VALUES (207, 199, '字典详情查询', '/system/dict/info', 100, 0, '2020-03-25 12:26:09', 0, '2020-03-25 12:26:09', '1', '2');
INSERT INTO `sys_resource` VALUES (210, 97, '角色资源', '/system/role/roleResource', 100, 0, '2020-04-08 13:24:53', 0, '2020-04-08 13:24:53', '1', '2');
INSERT INTO `sys_resource` VALUES (211, 97, '角色菜单', '/system/role/roleMenu', 100, 0, '2020-04-08 13:24:53', 0, '2020-04-08 13:24:53', '1', '2');
INSERT INTO `sys_resource` VALUES (212, 96, '全部菜单', '/system/menu/all', 100, 0, '2020-04-09 16:57:44', 0, '2020-04-09 16:57:44', '1', '2');
INSERT INTO `sys_resource` VALUES (213, 94, '行业类别管理', '/industry/type', 100, 0, '2020-04-11 10:12:23', 0, '2020-04-11 10:12:23', '1', '2');
INSERT INTO `sys_resource` VALUES (214, 213, '修改', '/industry/type/update', 100, 0, '2020-04-11 10:12:23', 0, '2020-04-11 10:12:23', '1', '2');
INSERT INTO `sys_resource` VALUES (215, 213, '删除', '/industry/type/delete', 100, 0, '2020-04-11 10:12:23', 0, '2020-04-11 10:12:23', '1', '2');
INSERT INTO `sys_resource` VALUES (216, 213, '列表查询', '/industry/type/list', 100, 0, '2020-04-11 10:12:23', 0, '2020-04-11 10:12:23', '1', '2');
INSERT INTO `sys_resource` VALUES (217, 213, '添加', '/industry/type/insert', 100, 0, '2020-04-11 10:12:23', 0, '2020-04-11 10:12:23', '1', '2');
INSERT INTO `sys_resource` VALUES (218, 213, '分页查询', '/industry/type/page', 100, 0, '2020-04-11 10:12:23', 0, '2020-04-11 10:12:23', '1', '2');
INSERT INTO `sys_resource` VALUES (219, 213, '详情', '/industry/type/info', 100, 0, '2020-04-11 10:12:23', 0, '2020-04-11 10:12:23', '1', '2');
INSERT INTO `sys_resource` VALUES (220, 0, '企业信息管理', '/company/companyInfo', 100, 0, '2020-04-11 10:57:30', 0, '2020-04-11 10:57:30', '1', '2');
INSERT INTO `sys_resource` VALUES (221, 220, '修改', '/company/companyInfo/update', 100, 0, '2020-04-11 10:57:30', 0, '2020-04-11 10:57:30', '1', '2');
INSERT INTO `sys_resource` VALUES (222, 220, '删除', '/company/companyInfo/delete', 100, 0, '2020-04-11 10:57:30', 0, '2020-04-11 10:57:30', '1', '2');
INSERT INTO `sys_resource` VALUES (223, 220, '列表查询', '/company/companyInfo/list', 100, 0, '2020-04-11 10:57:30', 0, '2020-04-11 10:57:30', '1', '2');
INSERT INTO `sys_resource` VALUES (224, 220, '添加', '/company/companyInfo/insert', 100, 0, '2020-04-11 10:57:30', 0, '2020-04-11 10:57:30', '1', '2');
INSERT INTO `sys_resource` VALUES (225, 220, '分页查询', '/company/companyInfo/page', 100, 0, '2020-04-11 10:57:30', 0, '2020-04-11 10:57:30', '1', '2');
INSERT INTO `sys_resource` VALUES (226, 220, '详情', '/company/companyInfo/info', 100, 0, '2020-04-11 10:57:30', 0, '2020-04-11 10:57:30', '1', '2');
INSERT INTO `sys_resource` VALUES (227, 98, '检查登录状态', '/system/user/public/checkToken', 100, 0, '2020-04-11 13:02:57', 0, '2020-04-11 13:02:57', '1', '2');
INSERT INTO `sys_resource` VALUES (235, 93, '全部资源', '/system/resource/all', 100, 0, '2020-04-11 14:48:10', 0, '2020-04-11 14:48:10', '1', '2');
INSERT INTO `sys_resource` VALUES (236, 220, '上传文件', '/company/companyInfo/uploadFile', 100, 0, '2020-04-12 19:50:03', 0, '2020-04-12 19:50:03', '1', '2');
INSERT INTO `sys_resource` VALUES (237, 199, '刷新字典缓存', '/system/dict/public/refresh/5156D05BCCAE98AC70FD2251D35D401A', 100, 0, '2020-04-12 20:37:20', 0, '2020-04-12 20:37:20', '1', '2');
INSERT INTO `sys_resource` VALUES (245, 0, '在职人员信息管理', '/inWork/humanInfo', 100, 0, '2020-04-13 14:31:50', 0, '2020-04-13 14:31:50', '1', '2');
INSERT INTO `sys_resource` VALUES (246, 220, '根据用户查询企业信息', '/company/companyInfo/infoByUserId', 100, 0, '2020-04-13 14:31:50', 0, '2020-04-13 14:31:50', '1', '2');
INSERT INTO `sys_resource` VALUES (247, 245, '修改', '/inWork/humanInfo/update', 100, 0, '2020-04-13 14:31:50', 0, '2020-04-13 14:31:50', '1', '2');
INSERT INTO `sys_resource` VALUES (248, 245, '删除', '/inWork/humanInfo/delete', 100, 0, '2020-04-13 14:31:50', 0, '2020-04-13 14:31:50', '1', '2');
INSERT INTO `sys_resource` VALUES (249, 245, '列表查询', '/inWork/humanInfo/list', 100, 0, '2020-04-13 14:31:50', 0, '2020-04-13 14:31:50', '1', '2');
INSERT INTO `sys_resource` VALUES (250, 245, '添加', '/inWork/humanInfo/insert', 100, 0, '2020-04-13 14:31:50', 0, '2020-04-13 14:31:50', '1', '2');
INSERT INTO `sys_resource` VALUES (251, 245, '分页查询', '/inWork/humanInfo/page', 100, 0, '2020-04-13 14:31:50', 0, '2020-04-13 14:31:50', '1', '2');
INSERT INTO `sys_resource` VALUES (252, 245, '详情', '/inWork/humanInfo/info', 100, 0, '2020-04-13 14:31:50', 0, '2020-04-13 14:31:50', '1', '2');
INSERT INTO `sys_resource` VALUES (253, 0, '动态人力资源信息管理', '/dynamicHumanInfo', 100, 0, '2020-04-13 14:32:48', 0, '2020-04-13 14:32:48', '1', '2');
INSERT INTO `sys_resource` VALUES (254, 253, '修改', '/dynamicHumanInfo/update', 100, 0, '2020-04-13 14:32:48', 0, '2020-04-13 14:32:48', '1', '2');
INSERT INTO `sys_resource` VALUES (255, 253, '删除', '/dynamicHumanInfo/delete', 100, 0, '2020-04-13 14:32:48', 0, '2020-04-13 14:32:48', '1', '2');
INSERT INTO `sys_resource` VALUES (256, 253, '列表查询', '/dynamicHumanInfo/list', 100, 0, '2020-04-13 14:32:48', 0, '2020-04-13 14:32:48', '1', '2');
INSERT INTO `sys_resource` VALUES (257, 253, '添加', '/dynamicHumanInfo/insert', 100, 0, '2020-04-13 14:32:48', 0, '2020-04-13 14:32:48', '1', '2');
INSERT INTO `sys_resource` VALUES (258, 253, '分页查询', '/dynamicHumanInfo/page', 100, 0, '2020-04-13 14:32:48', 0, '2020-04-13 14:32:48', '1', '2');
INSERT INTO `sys_resource` VALUES (259, 253, '详情', '/dynamicHumanInfo/info', 100, 0, '2020-04-13 14:32:48', 0, '2020-04-13 14:32:48', '1', '2');
INSERT INTO `sys_resource` VALUES (260, 220, '根据月份查询', '/dynamicHumanInfo/dynamicHumanInfoByMonth', 100, 0, '2020-04-14 08:44:00', 0, '2020-04-14 08:44:00', '1', '2');
INSERT INTO `sys_resource` VALUES (261, 0, '高新区用人单位招聘管理', '/recruitInfo', 100, 0, '2020-04-14 15:45:34', 0, '2020-04-14 15:45:34', '1', '2');
INSERT INTO `sys_resource` VALUES (272, 261, '查询招聘详情', '/recruitInfo/getRecruitInfo', 100, 0, '2020-04-14 16:52:04', 0, '2020-04-14 16:52:04', '1', '2');
INSERT INTO `sys_resource` VALUES (273, 261, '查询企业详情', '/recruitInfo/getCompanyInfo', 100, 0, '2020-04-14 16:52:04', 0, '2020-04-14 16:52:04', '1', '2');
INSERT INTO `sys_resource` VALUES (274, 261, '查询招聘岗位列表', '/recruitInfo/getRecruitJob', 100, 0, '2020-04-14 16:52:04', 0, '2020-04-14 16:52:04', '1', '2');
INSERT INTO `sys_resource` VALUES (275, 245, '根据月份查询', '/inWork/humanInfo/inWorkHumanInfoByMonth', 100, 0, '2020-04-14 17:04:07', 0, '2020-04-14 17:04:07', '1', '2');
INSERT INTO `sys_resource` VALUES (276, 261, '提交当月的招聘信息', '/recruitInfo/submit', 100, 0, '2020-04-15 14:50:33', 0, '2020-04-15 14:50:33', '1', '2');
INSERT INTO `sys_resource` VALUES (277, 261, '检查当月是否存在数据', '/recruitInfo/checkNowMonthData', 100, 0, '2020-04-15 14:50:33', 0, '2020-04-15 14:50:33', '1', '2');
INSERT INTO `sys_resource` VALUES (280, 220, '企业性质统计', '/company/companyInfo/companyUnitTypeStatistical', 100, 0, '2020-04-20 17:17:15', 0, '2020-04-20 17:17:15', '1', '2');
INSERT INTO `sys_resource` VALUES (283, 220, '用工动态统计表分页', '/dynamicHumanInfo/statisticsTable/page', 100, 0, '2020-04-21 12:55:21', 0, '2020-04-21 12:55:21', '1', '2');
INSERT INTO `sys_resource` VALUES (284, 220, '用工动态统计表导出', '/dynamicHumanInfo/statisticsTable/export', 100, 0, '2020-04-21 12:55:21', 0, '2020-04-21 12:55:21', '1', '2');
INSERT INTO `sys_resource` VALUES (289, 245, '在职人员信息统计表分页', '/inWork/humanInfo/statisticsTable/page', 100, 0, '2020-04-22 14:18:07', 0, '2020-04-22 14:18:07', '1', '2');
INSERT INTO `sys_resource` VALUES (290, 245, '在职人员信息统计表导出', '/inWork/humanInfo/statisticsTable/export', 100, 0, '2020-04-22 14:18:07', 0, '2020-04-22 14:18:07', '1', '2');
INSERT INTO `sys_resource` VALUES (293, 220, '行业比较统计图', '/company/companyInfo/companyIndustryTypeStatistical', 100, 0, '2020-04-22 17:06:36', 0, '2020-04-22 17:06:36', '1', '2');
INSERT INTO `sys_resource` VALUES (296, 261, '添加或修改企业招聘信息', '/recruitInfo/insertOrUpdate', 100, 0, '2020-04-23 08:57:42', 0, '2020-04-23 08:57:42', '1', '2');
INSERT INTO `sys_resource` VALUES (297, 261, '招聘情况反馈统计表分页', '/recruitInfo/statisticsTable/page', 100, 0, '2020-04-23 08:57:42', 0, '2020-04-23 08:57:42', '1', '2');
INSERT INTO `sys_resource` VALUES (298, 261, '招聘情况反馈统计表导出', '/recruitInfo/statisticsTable/export', 100, 0, '2020-04-23 08:57:42', 0, '2020-04-23 08:57:42', '1', '2');
INSERT INTO `sys_resource` VALUES (299, 261, '删除企业信息', '/recruitInfo/delete', 100, 0, '2020-04-23 09:40:07', 0, '2020-04-23 09:40:07', '1', '2');
INSERT INTO `sys_resource` VALUES (300, 261, '企业下的全部岗位', '/recruitInfo/allJobNames', 100, 0, '2020-04-23 13:35:41', 0, '2020-04-23 13:35:41', '1', '2');
INSERT INTO `sys_resource` VALUES (301, 261, '行业类别下的全部岗位', '/recruitInfo/typeJobNames', 100, 0, '2020-04-23 13:59:49', 0, '2020-04-23 13:59:49', '1', '2');
INSERT INTO `sys_resource` VALUES (302, 220, '企业用工动态统计图', '/dynamicHumanInfo/statisticalChart/dynamicHumanInfoStatistical', 100, 0, '2020-04-23 17:31:17', 0, '2020-04-23 17:31:17', '1', '2');
INSERT INTO `sys_resource` VALUES (303, 98, '登录日志', '/system/user/loginTimeline', 100, 0, '2020-04-27 09:48:35', 0, '2020-04-27 09:48:35', '1', '2');
INSERT INTO `sys_resource` VALUES (304, 220, '修改企业用户密码', '/company/companyInfo/resetPassword', 100, 0, '2020-05-14 10:23:49', 0, '2020-05-14 10:23:49', '1', '2');

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '系统管理员', 'system', NULL, 100, 0, NULL, 1, '2020-05-14 10:31:19', '1', '2');
INSERT INTO `sys_role` VALUES (2, '企业管理员', 'admin_company', NULL, 100, 0, NULL, 1, '2020-04-26 10:04:21', '1', '2');

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
) ENGINE = InnoDB AUTO_INCREMENT = 94 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (79, '测试数据111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_user` VALUES (80, '测试数据111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_user` VALUES (81, 'test', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 100, 0, '2020-10-13 10:29:53', 0, NULL, '1', '2');
INSERT INTO `sys_user` VALUES (82, 'test', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 100, 0, '2020-10-13 10:31:10', 0, NULL, '1', '2');
INSERT INTO `sys_user` VALUES (83, '222222', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_user` VALUES (84, '222222', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_user` VALUES (85, '222222', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_user` VALUES (86, '222222', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_user` VALUES (87, '222222', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_user` VALUES (88, '222222', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_user` VALUES (89, '222222', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_user` VALUES (90, '222222', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_user` VALUES (91, '33333', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_user` VALUES (92, '33333', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 100, 0, NULL, 0, NULL, '1', '2');
INSERT INTO `sys_user` VALUES (93, '33333', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 100, 0, NULL, 0, NULL, '1', '2');

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 100314
 Source Host           : localhost:3306
 Source Schema         : dataproviderservice

 Target Server Type    : MySQL
 Target Server Version : 100314
 File Encoding         : 65001

 Date: 19/06/2019 16:39:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for petro_server_sys_orc_tmp
-- ----------------------------
DROP TABLE IF EXISTS `petro_server_sys_orc_tmp`;
CREATE TABLE `petro_server_sys_orc_tmp`  (
  `sys_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `sys_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `user_pass` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`sys_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for petro_server_table_orc_tmp
-- ----------------------------
DROP TABLE IF EXISTS `petro_server_table_orc_tmp`;
CREATE TABLE `petro_server_table_orc_tmp`  (
  `sys_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '对接系统编号',
  `table_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '获取库表编号',
  `table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '获取库表名称',
  `table_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '获取库表描述',
  `class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'Build类',
  `driverclassname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '接出数据_数据库驱动',
  `jdbcurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '接出数据_数据库连接串',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '对接系统登陆用户',
  `user_pass` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '对接系统登陆密码',
  PRIMARY KEY (`sys_no`, `table_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

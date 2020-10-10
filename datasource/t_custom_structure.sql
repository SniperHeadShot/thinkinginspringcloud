/*
 Navicat MySQL Data Transfer

 Source Server         : 192.168.9.241
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 192.168.9.241:3306
 Source Schema         : data_center

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 19/08/2020 10:23:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_custom_structure
-- ----------------------------
DROP TABLE IF EXISTS `t_custom_structure`;
CREATE TABLE `t_custom_structure`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

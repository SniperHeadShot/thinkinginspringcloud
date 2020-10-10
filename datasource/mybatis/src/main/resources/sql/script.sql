-- ----------------------------
-- database data_center
-- ----------------------------
-- CREATE DATABASE IF NOT EXISTS data_center CHARACTER SET UTF8;

-- ----------------------------
-- Table structure for t_custom_structure
-- ----------------------------
DROP TABLE IF EXISTS `t_custom_structure`;
CREATE TABLE `t_custom_structure`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
ALTER TABLE `t_custom_structure` AUTO_INCREMENT = 1;

-- ----------------------------
-- Records of t_custom_structure
-- ----------------------------
INSERT INTO `t_custom_structure` ( `name`, `age` )
VALUES
	( '张三', 20 ),
	( '李四', 21 ),
	( '王五', 22 );
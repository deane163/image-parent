/*
Navicat MySQL Data Transfer

Source Server         : 10.10.1.52
Source Server Version : 50713
Source Host           : 10.10.1.52:3306
Source Database       : mybatis-plus

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2018-01-11 14:47:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user_token`
-- ----------------------------
DROP TABLE IF EXISTS `user_token`;
CREATE TABLE `user_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(150) DEFAULT NULL,
  `token` varchar(64) DEFAULT NULL,
  `expired_time` bigint(22) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_token
-- ----------------------------
INSERT INTO `user_token` VALUES ('1', 'admin', 'admin', 'c05bb0b25d7b4541837b4d1c82e5b2bb', '1515900616278', '2018-01-11 11:28:17');
INSERT INTO `user_token` VALUES ('1', 'root', 'root', 'c05bb0b25d7b4541837b4d1c82e5b2bb', '1515900616278', '2018-01-11 11:28:17');
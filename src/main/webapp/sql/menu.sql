/*
Navicat MySQL Data Transfer

Source Server         : me
Source Server Version : 50550
Source Host           : localhost:3306
Source Database       : menu

Target Server Type    : MYSQL
Target Server Version : 50550
File Encoding         : 65001

Date: 2016-09-05 14:46:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for resources
-- ----------------------------
DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources` (
  `res_id` int(11) NOT NULL AUTO_INCREMENT,
  `res_name` varchar(20) NOT NULL,
  `res_href` varchar(100) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`res_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resources
-- ----------------------------
INSERT INTO `resources` VALUES ('1', '系统管理', '/system', null);
INSERT INTO `resources` VALUES ('2', '用户管理', '/system/user', '1');
INSERT INTO `resources` VALUES ('3', '角色管理', '/system/role', '1');
INSERT INTO `resources` VALUES ('4', '资源管理', '/system/resources', '1');
INSERT INTO `resources` VALUES ('5', '系统配置', '/config', null);
INSERT INTO `resources` VALUES ('6', '系统信息', '/config/info', '5');
INSERT INTO `resources` VALUES ('7', '关于我们', '/config/about', '5');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'super');
INSERT INTO `role` VALUES ('2', 'admin');
INSERT INTO `role` VALUES ('3', 'test');

-- ----------------------------
-- Table structure for role_resources
-- ----------------------------
DROP TABLE IF EXISTS `role_resources`;
CREATE TABLE `role_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `res_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_resources
-- ----------------------------
INSERT INTO `role_resources` VALUES ('1', '1', '1');
INSERT INTO `role_resources` VALUES ('2', '1', '2');
INSERT INTO `role_resources` VALUES ('3', '1', '3');
INSERT INTO `role_resources` VALUES ('4', '1', '4');
INSERT INTO `role_resources` VALUES ('5', '1', '5');
INSERT INTO `role_resources` VALUES ('6', '1', '6');
INSERT INTO `role_resources` VALUES ('7', '1', '7');
INSERT INTO `role_resources` VALUES ('8', '2', '1');
INSERT INTO `role_resources` VALUES ('9', '2', '2');
INSERT INTO `role_resources` VALUES ('10', '2', '5');
INSERT INTO `role_resources` VALUES ('11', '2', '7');
INSERT INTO `role_resources` VALUES ('12', '3', '1');
INSERT INTO `role_resources` VALUES ('13', '3', '2');
INSERT INTO `role_resources` VALUES ('14', '3', '3');
INSERT INTO `role_resources` VALUES ('15', '3', '5');
INSERT INTO `role_resources` VALUES ('16', '3', '6');
INSERT INTO `role_resources` VALUES ('17', '3', '7');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'super', '1');
INSERT INTO `user` VALUES ('2', 'admin', '1');
INSERT INTO `user` VALUES ('3', 'test', '1');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '2', '2');
INSERT INTO `user_role` VALUES ('3', '3', '3');
INSERT INTO `user_role` VALUES ('4', '2', '3');

/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 100131
 Source Host           : localhost:3306
 Source Schema         : multidatasource1

 Target Server Type    : MySQL
 Target Server Version : 100131
 File Encoding         : 65001

 Date: 24/06/2020 15:37:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 'category1');
INSERT INTO `category` VALUES (2, 'category2');
INSERT INTO `category` VALUES (3, 'category3');
INSERT INTO `category` VALUES (4, 'category4');
INSERT INTO `category` VALUES (5, 'category5');

SET FOREIGN_KEY_CHECKS = 1;

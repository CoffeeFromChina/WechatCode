/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 100131
 Source Host           : localhost:3306
 Source Schema         : multidatasource2

 Target Server Type    : MySQL
 Target Server Version : 100131
 File Encoding         : 65001

 Date: 24/06/2020 15:37:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for price
-- ----------------------------
DROP TABLE IF EXISTS `price`;
CREATE TABLE `price`  (
  `RMB` int(10) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of price
-- ----------------------------
INSERT INTO `price` VALUES (10);
INSERT INTO `price` VALUES (60);
INSERT INTO `price` VALUES (29);
INSERT INTO `price` VALUES (70);
INSERT INTO `price` VALUES (50);

SET FOREIGN_KEY_CHECKS = 1;

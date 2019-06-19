/*
 Navicat Premium Data Transfer

 Source Server         : localhost_mysql
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : rabbitmq

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 19/06/2019 16:41:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for broker_message_log
-- ----------------------------
DROP TABLE IF EXISTS `broker_message_log`;
CREATE TABLE `broker_message_log`  (
  `message_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息唯一ID',
  `message` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息内容',
  `try_count` int(4) NULL DEFAULT 0 COMMENT '重试次数',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '消息投递状态 0投递中，1投递成功，2投递失败',
  `next_retry` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '下一次重试时间',
  `create_time` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`message_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of broker_message_log
-- ----------------------------
INSERT INTO `broker_message_log` VALUES ('1559113644174$f98927af-50d9-4c76-b208-d2e19a6d7fe3', '{\"id\":2018092101,\"messageId\":\"1559113644174$f98927af-50d9-4c76-b208-d2e19a6d7fe3\",\"name\":\"测试订单1\"}', 0, '1', '2019-05-29 15:07:24', '2019-05-29 15:07:24', '2019-05-29 15:07:25');
INSERT INTO `broker_message_log` VALUES ('1559113709853$4ec2898f-09c9-4d00-8d53-258f61c848c5', '{\"id\":2019092101,\"messageId\":\"1559113709853$4ec2898f-09c9-4d00-8d53-258f61c848c5\",\"name\":\"测试订单1\"}', 0, '1', '2019-05-29 15:08:30', '2019-05-29 15:08:30', '2019-05-29 15:08:31');
INSERT INTO `broker_message_log` VALUES ('1559113777653$4826e0a0-7cb3-41e9-9731-c8d2b942a020', '{\"id\":2019092103,\"messageId\":\"1559113777653$4826e0a0-7cb3-41e9-9731-c8d2b942a020\",\"name\":\"测试订单19\"}', 0, '1', '2019-05-29 15:09:38', '2019-05-29 15:09:38', '2019-05-29 15:09:38');
INSERT INTO `broker_message_log` VALUES ('1559115148832$97408137-c782-440a-af39-40d4bc879e20', '{\"id\":2019092104,\"messageId\":\"1559115148832$97408137-c782-440a-af39-40d4bc879e20\",\"name\":\"测试订单19\"}', 0, '1', '2019-05-29 15:32:29', '2019-05-29 15:32:29', '2019-05-29 15:32:30');
INSERT INTO `broker_message_log` VALUES ('1559115363516$f238c00e-f7cd-4ee8-933b-429820f0f699', '{\"id\":2019092105,\"messageId\":\"1559115363516$f238c00e-f7cd-4ee8-933b-429820f0f699\",\"name\":\"测试订单19\"}', 1, '1', '2019-05-29 15:38:26', '2019-05-29 15:38:26', '2019-05-29 15:38:26');
INSERT INTO `broker_message_log` VALUES ('1559115606447$d37b182a-9485-4285-848c-16f6b7377d34', '{\"id\":2019092106,\"messageId\":\"1559115606447$d37b182a-9485-4285-848c-16f6b7377d34\",\"name\":\"测试订单19\"}', 3, '2', '2019-05-29 15:41:43', '2019-05-29 15:41:43', '2019-05-29 15:41:43');
INSERT INTO `broker_message_log` VALUES ('1560933081518$0edcd527-0700-432d-ab34-9c95fee76922', '{\"id\":20190619,\"messageId\":\"1560933081518$0edcd527-0700-432d-ab34-9c95fee76922\",\"name\":\"测试订单20190619\"}', 1, '1', '2019-06-19 16:35:37', '2019-06-19 16:35:37', '2019-06-19 16:35:38');
INSERT INTO `broker_message_log` VALUES ('1560933419821$abe0aa2c-f3f5-428c-966c-a31f89b2111c', '{\"id\":2019092100,\"messageId\":\"1560933419821$abe0aa2c-f3f5-428c-966c-a31f89b2111c\",\"name\":\"测试订单20190619\"}', 1, '1', '2019-06-19 16:40:17', '2019-06-19 16:40:17', '2019-06-19 16:40:18');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `message_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2019092107 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (20190619, '测试订单20190619', '1560933081518$0edcd527-0700-432d-ab34-9c95fee76922');
INSERT INTO `t_order` VALUES (2018092101, '测试订单1', '1559113644174$f98927af-50d9-4c76-b208-d2e19a6d7fe3');
INSERT INTO `t_order` VALUES (2019092100, '测试订单20190619', '1560933419821$abe0aa2c-f3f5-428c-966c-a31f89b2111c');
INSERT INTO `t_order` VALUES (2019092101, '测试订单1', '1559113709853$4ec2898f-09c9-4d00-8d53-258f61c848c5');
INSERT INTO `t_order` VALUES (2019092103, '测试订单19', '1559113777653$4826e0a0-7cb3-41e9-9731-c8d2b942a020');
INSERT INTO `t_order` VALUES (2019092104, '测试订单19', '1559115148832$97408137-c782-440a-af39-40d4bc879e20');
INSERT INTO `t_order` VALUES (2019092105, '测试订单19', '1559115363516$f238c00e-f7cd-4ee8-933b-429820f0f699');
INSERT INTO `t_order` VALUES (2019092106, '测试订单19', '1559115606447$d37b182a-9485-4285-848c-16f6b7377d34');

SET FOREIGN_KEY_CHECKS = 1;

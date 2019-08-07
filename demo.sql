/*
 Navicat Premium Data Transfer

 Source Server         : Docker-Mysql
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:32803
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 07/08/2019 18:38:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for QRTZ_BLOB_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `QRTZ_BLOB_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for QRTZ_CALENDARS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE `QRTZ_CALENDARS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for QRTZ_CRON_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE `QRTZ_CRON_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CRON_EXPRESSION` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_CRON_TRIGGERS
-- ----------------------------
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('quartzScheduler', 'SayHelloJob', 'SayHelloJob', '0/5 * * * * ? ', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for QRTZ_FIRED_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TRIG_INST_NAME`(`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY`(`SCHED_NAME`, `INSTANCE_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_FT_J_G`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_T_G`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TG`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_FIRED_TRIGGERS
-- ----------------------------
INSERT INTO `QRTZ_FIRED_TRIGGERS` VALUES ('quartzScheduler', 'NON_CLUSTERED1565172342588', 'SayHelloJob', 'SayHelloJob', 'NON_CLUSTERED', 1565174325137, 1565174330000, 5, 'ACQUIRED', NULL, NULL, '0', '0');

-- ----------------------------
-- Table structure for QRTZ_JOB_DETAILS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE `QRTZ_JOB_DETAILS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_J_REQ_RECOVERY`(`SCHED_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_J_GRP`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_JOB_DETAILS
-- ----------------------------
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('quartzScheduler', 'SayHelloJob', 'SayHelloJob', NULL, 'com.example.demo.job.SayHelloJob', '0', '1', '1', '0', 0x230D0A23576564204175672030372031383A30353A34322043535420323031390D0A);

-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_LOCKS
-- ----------------------------
INSERT INTO `QRTZ_LOCKS` VALUES ('quartzScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for QRTZ_SCHEDULER_STATE
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE `QRTZ_SCHEDULER_STATE`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `INT_PROP_1` int(11) NULL DEFAULT NULL,
  `INT_PROP_2` int(11) NULL DEFAULT NULL,
  `LONG_PROP_1` bigint(20) NULL DEFAULT NULL,
  `LONG_PROP_2` bigint(20) NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for QRTZ_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PRIORITY` int(11) NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_J`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_C`(`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_T_G`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_STATE`(`SCHED_NAME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_STATE`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_G_STATE`(`SCHED_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NEXT_FIRE_TIME`(`SCHED_NAME`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST`(`SCHED_NAME`, `TRIGGER_STATE`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_TRIGGERS
-- ----------------------------
INSERT INTO `QRTZ_TRIGGERS` VALUES ('quartzScheduler', 'SayHelloJob', 'SayHelloJob', 'SayHelloJob', 'SayHelloJob', NULL, 1565174330000, 1565174325000, 5, 'ACQUIRED', 'CRON', 1565172342000, 0, NULL, 2, '');

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `job_params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `job_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `job_group_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `job_status` tinyint(2) NULL DEFAULT 0,
  `job_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_delete` tinyint(2) NULL DEFAULT 0,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of job
-- ----------------------------
INSERT INTO `job` VALUES (1, 'com.example.demo.job.SayHelloJob', NULL, 'SayHelloJob', 'SayHelloJob', '0/5 * * * * ? ', 0, NULL, 0, NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `version` tinyint(255) NULL DEFAULT NULL,
  `is_delete` tinyint(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 201 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (2, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (3, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (4, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (5, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (6, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (7, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (8, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (9, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (10, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (11, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (12, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (13, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (14, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (15, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (16, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (17, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (18, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (19, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (20, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (21, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (22, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (23, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (24, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (25, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (26, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (27, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (28, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (29, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (30, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (31, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (32, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (33, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (34, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (35, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (36, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (37, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (38, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (39, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (40, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (41, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (42, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (43, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (44, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (45, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (46, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (47, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (48, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (49, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (50, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (51, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (52, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (53, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (54, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (55, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (56, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (57, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (58, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (59, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (60, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (61, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (62, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (63, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (64, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (65, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (66, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (67, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (68, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (69, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (70, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (71, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (72, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (73, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (74, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (75, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (76, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (77, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (78, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (79, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (80, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (81, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (82, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (83, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (84, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (85, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (86, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (87, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (88, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (89, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (90, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (91, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (92, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (93, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (94, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (95, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (96, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (97, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (98, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (99, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (100, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (101, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (102, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (103, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (104, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (105, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (106, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (107, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (108, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (109, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (110, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (111, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (112, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (113, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (114, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (115, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (116, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (117, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (118, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (119, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (120, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (121, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (122, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (123, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (124, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (125, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (126, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (127, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (128, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (129, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (130, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (131, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (132, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (133, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (134, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (135, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (136, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (137, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (138, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (139, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (140, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (141, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (142, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (143, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (144, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (145, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (146, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (147, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (148, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (149, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (150, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (151, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (152, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (153, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (154, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (155, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (156, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (157, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (158, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (159, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (160, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (161, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (162, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (163, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (164, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (165, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (166, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (167, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (168, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (169, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (170, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (171, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (172, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (173, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (174, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (175, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (176, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (177, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (178, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (179, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (180, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (181, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (182, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (183, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (184, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (185, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (186, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (187, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (188, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (189, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (190, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (191, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (192, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (193, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (194, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (195, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (196, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (197, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (198, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (199, '魏超超', 23, '17687910227@163.com', NULL, NULL);
INSERT INTO `user` VALUES (200, '魏超超', 23, '17687910227@163.com', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;

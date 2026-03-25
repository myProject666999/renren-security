/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726 (5.7.26)
 Source Host           : localhost:3306
 Source Schema         : renren_security

 Target Server Type    : MySQL
 Target Server Version : 50726 (5.7.26)
 File Encoding         : 65001

 Date: 25/03/2026 16:34:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gp_contract
-- ----------------------------
DROP TABLE IF EXISTS `gp_contract`;
CREATE TABLE `gp_contract`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `contract_id` int(11) NOT NULL COMMENT '股票id',
  `symbol` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '股票代码',
  `sec_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '证券类型',
  `currency` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '货币类型',
  `exchange` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '交易所',
  `market_price` decimal(20, 6) NOT NULL DEFAULT 0.000000 COMMENT '股票价格',
  `creator` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`contract_id`) USING BTREE,
  UNIQUE INDEX `uni_contract_id`(`contract_id`) USING BTREE,
  UNIQUE INDEX `uni_symbol_currency`(`symbol`, `sec_type`, `currency`, `exchange`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '股票信息表' ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;

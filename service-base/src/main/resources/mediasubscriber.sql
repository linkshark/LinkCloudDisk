/*
Navicat MariaDB Data Transfer

Source Server         : 阿里106.14.218.153
Source Server Version : 100037
Source Host           : www.linkjb.com:3306
Source Database       : mediasubscriber

Target Server Type    : MariaDB
Target Server Version : 100037
File Encoding         : 65001

Date: 2019-03-25 16:22:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for media
-- ----------------------------
DROP TABLE IF EXISTS `media`;
CREATE TABLE `media` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `media_name` varchar(255) NOT NULL DEFAULT '' COMMENT '剧名',
  `primitive_name` varchar(255) DEFAULT NULL COMMENT '原名',
  `alias` varchar(255) DEFAULT NULL COMMENT '别名',
  `script_writer_name` varchar(255) DEFAULT NULL COMMENT '编剧名',
  `director_name` varchar(255) DEFAULT NULL COMMENT '导演',
  `actors` varchar(255) DEFAULT NULL COMMENT '主演',
  `premiere_date` varchar(255) DEFAULT NULL COMMENT '首映日期',
  `big_media_kind` varchar(255) DEFAULT NULL COMMENT '大分类',
  `media_kind` varchar(255) DEFAULT NULL COMMENT '小分类',
  `location` varchar(255) DEFAULT NULL COMMENT '地区',
  `tv_station` varchar(255) DEFAULT NULL COMMENT '电视台',
  `time_schadule` varchar(255) DEFAULT NULL COMMENT '首映时间',
  `grade` varchar(255) DEFAULT NULL COMMENT '评分',
  `image` varchar(255) DEFAULT NULL COMMENT '图片名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4360 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for media_link
-- ----------------------------
DROP TABLE IF EXISTS `media_link`;
CREATE TABLE `media_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `link_id` int(11) NOT NULL,
  `url_name` varchar(255) DEFAULT NULL,
  `url_address` varchar(3000) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `link_id` (`link_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49433 DEFAULT CHARSET=utf8;

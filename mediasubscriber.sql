/*
Navicat MySQL Data Transfer

Source Server         : linkjb
Source Server Version : 50725
Source Host           : www.linkjb.com:3306
Source Database       : mediasubscriber

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-04-18 11:04:40
*/

CREATE DATABASE mediasubscriber;

use mediasubscriber;

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
) ENGINE=InnoDB AUTO_INCREMENT=5101 DEFAULT CHARSET=utf8;

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
  KEY `link_id` (`link_id`),
  KEY `url_name` (`url_name`)
) ENGINE=InnoDB AUTO_INCREMENT=59138 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `pass_word` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

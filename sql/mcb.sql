/*
SQLyog Ultimate v9.30 
MySQL - 5.5.30 : Database - mcb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mcb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mcb`;

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `customer_level_id` smallint(6) NOT NULL COMMENT '客户等级',
  `user_id` int(11) NOT NULL COMMENT '和用户表id',
  `id_del` smallint(6) NOT NULL DEFAULT '0' COMMENT '0未删除1删除',
  `sex` smallint(6) NOT NULL COMMENT '0男1女',
  `phone_number` varchar(20) NOT NULL COMMENT '电话号码',
  `create_time` date NOT NULL COMMENT '创建时间 一般创建后 不会再修改',
  `email` varchar(50) DEFAULT NULL COMMENT '有邮箱可以预留 用于以后发推广信息',
  `agent_id` int(11) NOT NULL COMMENT '代理人ID',
  `score` int(11) NOT NULL DEFAULT '0' COMMENT '消费积分',
  `hand_id` int(11) NOT NULL COMMENT '经手人ID',
  `baby_month` int(11) NOT NULL DEFAULT '0' COMMENT '宝宝月份',
  PRIMARY KEY (`id`),
  KEY `FK_customer_lever` (`customer_level_id`),
  KEY `FK_customer_user_id` (`user_id`),
  KEY `FK_customer_agent` (`agent_id`),
  KEY `FK_customer_hand` (`hand_id`),
  CONSTRAINT `FK_customer_hand` FOREIGN KEY (`hand_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK_customer_agent` FOREIGN KEY (`agent_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK_customer_lever` FOREIGN KEY (`customer_level_id`) REFERENCES `customer_level` (`id`),
  CONSTRAINT `FK_customer_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户表';

/*Data for the table `customer` */

/*Table structure for table `customer_level` */

DROP TABLE IF EXISTS `customer_level`;

CREATE TABLE `customer_level` (
  `id` smallint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `is_del` smallint(6) NOT NULL COMMENT '0未删除1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='客户等级';

/*Data for the table `customer_level` */

insert  into `customer_level`(`id`,`name`,`is_del`) values (1,'优质',0),(2,'良好',0),(3,'一般',0);

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `sex` smallint(6) NOT NULL DEFAULT '0' COMMENT '0表示男1表示女',
  `phone_number` varchar(20) NOT NULL,
  `create_time` date NOT NULL,
  `id_del` smallint(6) NOT NULL DEFAULT '0' COMMENT '0未删除1删除',
  `user_id` int(11) NOT NULL,
  `type_id` smallint(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_employee_user` (`user_id`),
  KEY `FK_employee` (`type_id`),
  CONSTRAINT `FK_employee` FOREIGN KEY (`type_id`) REFERENCES `employee_type` (`id`),
  CONSTRAINT `FK_employee_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='职工表';

/*Data for the table `employee` */

/*Table structure for table `employee_type` */

DROP TABLE IF EXISTS `employee_type`;

CREATE TABLE `employee_type` (
  `id` smallint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='职工类别';

/*Data for the table `employee_type` */

/*Table structure for table `exchange` */

DROP TABLE IF EXISTS `exchange`;

CREATE TABLE `exchange` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL COMMENT '客户Id',
  `product_id` int(11) NOT NULL COMMENT '商品ID',
  `cost_score` int(11) NOT NULL COMMENT '话费积分数',
  `action_time` date NOT NULL COMMENT '兑换时间',
  PRIMARY KEY (`id`),
  KEY `FK_exchange_customer` (`customer_id`),
  KEY `FK_exchange_product` (`product_id`),
  CONSTRAINT `FK_exchange_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_exchange_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='兑换记录表';

/*Data for the table `exchange` */

/*Table structure for table `new_plan_list` */

DROP TABLE IF EXISTS `new_plan_list`;

CREATE TABLE `new_plan_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `agent_id` int(11) NOT NULL COMMENT '代理人',
  `hand_id` int(11) NOT NULL COMMENT '经手人',
  `count` smallint(6) NOT NULL COMMENT '数量',
  `sum` decimal(10,0) NOT NULL COMMENT '金额',
  `new_plan_type_id` int(11) NOT NULL,
  `time` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_new_plan_list_agent` (`agent_id`),
  KEY `FK_new_plan_list_hand` (`hand_id`),
  KEY `FK_new_plan_list_customer` (`customer_id`),
  KEY `FK_new_plan_list_product` (`product_id`),
  KEY `FK_new_plan_list_new_plan_type` (`new_plan_type_id`),
  CONSTRAINT `FK_new_plan_list_new_plan_type` FOREIGN KEY (`new_plan_type_id`) REFERENCES `new_plan_type` (`id`),
  CONSTRAINT `FK_new_plan_list_agent` FOREIGN KEY (`agent_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK_new_plan_list_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_new_plan_list_hand` FOREIGN KEY (`hand_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK_new_plan_list_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新课计划';

/*Data for the table `new_plan_list` */

/*Table structure for table `new_plan_type` */

DROP TABLE IF EXISTS `new_plan_type`;

CREATE TABLE `new_plan_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prodect_id` int(11) NOT NULL COMMENT '商品ID',
  `cut` decimal(10,0) NOT NULL COMMENT '元/个',
  `not_lost_count` smallint(6) NOT NULL COMMENT '不间断次数',
  `start_time` date NOT NULL,
  `end_time` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_new_plan_product` (`prodect_id`),
  CONSTRAINT `FK_new_plan_product` FOREIGN KEY (`prodect_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新客计划规则';

/*Data for the table `new_plan_type` */

/*Table structure for table `performance` */

DROP TABLE IF EXISTS `performance`;

CREATE TABLE `performance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NOT NULL,
  `create_time` int(11) NOT NULL,
  `sum_money` decimal(10,0) NOT NULL DEFAULT '0' COMMENT '金额总提成',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='绩效';

/*Data for the table `performance` */

/*Table structure for table `performance_list` */

DROP TABLE IF EXISTS `performance_list`;

CREATE TABLE `performance_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `performance_id` int(11) NOT NULL,
  `performance_type` int(11) NOT NULL,
  `monery` decimal(10,0) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_performance_listperformance` (`performance_id`),
  CONSTRAINT `FK_performance_listperformance` FOREIGN KEY (`performance_id`) REFERENCES `performance` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='绩效提成详细';

/*Data for the table `performance_list` */

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '商品名称',
  `price` decimal(10,0) NOT NULL COMMENT '价格',
  `vip_price` decimal(10,0) DEFAULT NULL COMMENT '会员价',
  `uuid` varchar(50) DEFAULT NULL COMMENT '一般是商品的条形码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

/*Data for the table `product` */

/*Table structure for table `product_cut` */

DROP TABLE IF EXISTS `product_cut`;

CREATE TABLE `product_cut` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `cut` decimal(10,0) NOT NULL COMMENT '商品提成金额',
  `start_time` date NOT NULL COMMENT '开始时间',
  `end_time` date NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`),
  KEY `FK_product_cut_product` (`product_id`),
  CONSTRAINT `FK_product_cut_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品提成';

/*Data for the table `product_cut` */

/*Table structure for table `scoure_reward` */

DROP TABLE IF EXISTS `scoure_reward`;

CREATE TABLE `scoure_reward` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL COMMENT '商品数目',
  `extends_cost` decimal(10,0) NOT NULL COMMENT '额外花费',
  `count` smallint(6) NOT NULL COMMENT '兑换商品数目',
  `soure` int(11) NOT NULL COMMENT '需要兑换积分数目',
  `start_time` date NOT NULL COMMENT '开始时间',
  `end_time` date NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`),
  KEY `FK_scoure_reward_product` (`product_id`),
  CONSTRAINT `FK_scoure_reward_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分奖励';

/*Data for the table `scoure_reward` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `password` char(32) NOT NULL,
  `is_del` smallint(6) NOT NULL DEFAULT '0' COMMENT '是否删除 1不是 0是',
  `is_sys` smallint(6) NOT NULL DEFAULT '1' COMMENT '是否管理员 1不是 0是',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_UN` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='登录用户表';

/*Data for the table `user` */

insert  into `user`(`id`,`user_name`,`password`,`is_del`,`is_sys`) values (1,'admin','ECEDEE71953F9D74FE15A4DAC5FA6726',0,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

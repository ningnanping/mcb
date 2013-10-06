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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='客户表';

/*Data for the table `customer` */

insert  into `customer`(`id`,`name`,`customer_level_id`,`user_id`,`id_del`,`sex`,`phone_number`,`create_time`,`email`,`agent_id`,`score`,`hand_id`,`baby_month`) values (1,'lu',1,2,0,1,'13611582911','2013-10-06',NULL,1,0,1,1);

/*Table structure for table `customer_level` */

DROP TABLE IF EXISTS `customer_level`;

CREATE TABLE `customer_level` (
  `id` smallint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `is_del` smallint(6) NOT NULL DEFAULT '0' COMMENT '0未删除1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='客户等级';

/*Data for the table `customer_level` */

insert  into `customer_level`(`id`,`name`,`is_del`) values (0,'无等级',0),(1,'铁牌',0),(2,'铜牌',0),(3,'银牌',0),(4,'金牌',0),(5,'钻石',0);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='职工表';

/*Data for the table `employee` */

insert  into `employee`(`id`,`name`,`sex`,`phone_number`,`create_time`,`id_del`,`user_id`,`type_id`) values (1,'员工1',1,'1234567890','2013-10-06',0,3,4),(2,'员工2',0,'1234567890','2013-10-06',0,4,4),(3,'员工3',0,'1234567890','2013-10-06',0,5,3);

/*Table structure for table `employee_type` */

DROP TABLE IF EXISTS `employee_type`;

CREATE TABLE `employee_type` (
  `id` smallint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='职工类别';

/*Data for the table `employee_type` */

insert  into `employee_type`(`id`,`name`) values (1,'A类'),(2,'B类'),(3,'C类'),(4,'D类');

/*Table structure for table `exchange` */

DROP TABLE IF EXISTS `exchange`;

CREATE TABLE `exchange` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL COMMENT '客户Id',
  `product_id` int(11) NOT NULL COMMENT '商品ID',
  `cost_score` int(11) NOT NULL COMMENT '话费积分数',
  `action_time` date NOT NULL COMMENT '兑换时间',
  `product_cut_id` int(11) NOT NULL COMMENT '兑换规则ID',
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
  `sum` decimal(10,4) NOT NULL COMMENT '金额',
  `new_plan_type_id` int(11) NOT NULL,
  `time` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_new_plan_list_agent` (`agent_id`),
  KEY `FK_new_plan_list_hand` (`hand_id`),
  KEY `FK_new_plan_list_customer` (`customer_id`),
  KEY `FK_new_plan_list_product` (`product_id`),
  KEY `FK_new_plan_list_new_plan_type` (`new_plan_type_id`),
  CONSTRAINT `FK_new_plan_list_agent` FOREIGN KEY (`agent_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK_new_plan_list_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_new_plan_list_hand` FOREIGN KEY (`hand_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK_new_plan_list_new_plan_type` FOREIGN KEY (`new_plan_type_id`) REFERENCES `new_plan_type` (`id`),
  CONSTRAINT `FK_new_plan_list_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新课计划';

/*Data for the table `new_plan_list` */

/*Table structure for table `new_plan_type` */

DROP TABLE IF EXISTS `new_plan_type`;

CREATE TABLE `new_plan_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prodect_id` int(11) NOT NULL COMMENT '商品ID',
  `cut` decimal(10,4) NOT NULL COMMENT '元/个',
  `not_lost_count` smallint(6) NOT NULL COMMENT '不间断次数',
  `start_time` date NOT NULL,
  `end_time` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_new_plan_product` (`prodect_id`),
  CONSTRAINT `FK_new_plan_product` FOREIGN KEY (`prodect_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新客计划规则';

/*Data for the table `new_plan_type` */

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` date NOT NULL COMMENT '创建时间',
  `settlemen` smallint(6) NOT NULL COMMENT '结算方式 0现金 1积分兑换',
  `total` decimal(10,4) NOT NULL COMMENT '订单总金额',
  `customer_id` int(11) NOT NULL COMMENT '客户Id',
  PRIMARY KEY (`id`),
  KEY `FK_order_customer` (`customer_id`),
  CONSTRAINT `FK_order_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='订单记录';

/*Data for the table `order` */

insert  into `order`(`id`,`create_time`,`settlemen`,`total`,`customer_id`) values (23,'2013-10-06',0,'687.5000',1),(24,'2013-10-06',0,'687.5000',1),(25,'2013-10-06',0,'687.5000',1);

/*Table structure for table `order_product` */

DROP TABLE IF EXISTS `order_product`;

CREATE TABLE `order_product` (
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  PRIMARY KEY (`order_id`,`product_id`),
  KEY `FK_order_product_product` (`product_id`),
  CONSTRAINT `FK_order_product_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FK_order_product_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单商品关联表';

/*Data for the table `order_product` */

insert  into `order_product`(`order_id`,`product_id`) values (23,1),(24,1),(25,1),(23,2),(24,2),(25,2);

/*Table structure for table `performance` */

DROP TABLE IF EXISTS `performance`;

CREATE TABLE `performance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NOT NULL,
  `create_time` int(11) NOT NULL,
  `sum_money` decimal(10,4) NOT NULL DEFAULT '0.0000' COMMENT '金额总提成',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='绩效';

/*Data for the table `performance` */

/*Table structure for table `performance_list` */

DROP TABLE IF EXISTS `performance_list`;

CREATE TABLE `performance_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `performance_id` int(11) NOT NULL,
  `performance_type` int(11) NOT NULL,
  `monery` decimal(10,4) NOT NULL,
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
  `uuid` varchar(50) NOT NULL COMMENT '一般是商品的条形码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='商品表';

/*Data for the table `product` */

insert  into `product`(`id`,`name`,`uuid`) values (1,'奶粉1','1234567'),(2,'奶粉2','1234566');

/*Table structure for table `product_cut` */

DROP TABLE IF EXISTS `product_cut`;

CREATE TABLE `product_cut` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `cut` decimal(10,4) NOT NULL COMMENT '商品提成金额',
  `start_time` date NOT NULL COMMENT '开始时间',
  `end_time` date NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`),
  KEY `FK_product_cut_product` (`product_id`),
  CONSTRAINT `FK_product_cut_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品提成';

/*Data for the table `product_cut` */

/*Table structure for table `product_price` */

DROP TABLE IF EXISTS `product_price`;

CREATE TABLE `product_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `price` decimal(10,4) NOT NULL,
  `vip_price` decimal(10,4) NOT NULL,
  `customer_level_id` smallint(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_product_price_customer_level` (`customer_level_id`),
  CONSTRAINT `FK_product_price_customer_level` FOREIGN KEY (`customer_level_id`) REFERENCES `customer_level` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='商品价格表 根据不同的客户等级 不同的价格';

/*Data for the table `product_price` */

insert  into `product_price`(`id`,`product_id`,`price`,`vip_price`,`customer_level_id`) values (16,1,'289.0000','288.5000',1),(17,1,'289.0000','286.5000',2),(18,1,'289.0000','284.5000',3),(19,1,'289.0000','282.5000',4),(20,1,'289.0000','280.5000',5),(21,1,'289.0000','289.0000',0),(22,2,'399.0000','399.0000',0),(23,2,'399.0000','399.0000',1),(24,2,'399.0000','399.0000',2),(25,2,'399.0000','399.0000',3),(26,2,'399.0000','380.5000',4),(27,2,'399.0000','370.5000',5);

/*Table structure for table `sequence` */

DROP TABLE IF EXISTS `sequence`;

CREATE TABLE `sequence` (
  `name` varchar(50) NOT NULL,
  `current_value` int(11) NOT NULL,
  `increment` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sequence` */

insert  into `sequence`(`name`,`current_value`,`increment`) values ('ORDER_SEQ',25,1),('TestSeq',11,1);

/*Table structure for table `soure_reward` */

DROP TABLE IF EXISTS `soure_reward`;

CREATE TABLE `soure_reward` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL COMMENT '商品数目',
  `extends_cost` decimal(10,4) NOT NULL COMMENT '额外花费',
  `count` smallint(6) NOT NULL COMMENT '兑换商品数目',
  `soure` int(11) NOT NULL COMMENT '需要兑换积分数目',
  `start_time` date NOT NULL COMMENT '开始时间',
  `end_time` date NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`),
  KEY `FK_scoure_reward_product` (`product_id`),
  CONSTRAINT `FK_scoure_reward_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分奖励';

/*Data for the table `soure_reward` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `password` char(32) NOT NULL,
  `is_del` smallint(6) NOT NULL DEFAULT '0' COMMENT '是否删除 1不是 0是',
  `is_sys` smallint(6) NOT NULL DEFAULT '1' COMMENT '是否管理员 1不是 0是',
  `desc` varchar(255) DEFAULT NULL COMMENT '对该账号的说明',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_UN` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='登录用户表';

/*Data for the table `user` */

insert  into `user`(`id`,`user_name`,`password`,`is_del`,`is_sys`,`desc`) values (1,'admin','ECEDEE71953F9D74FE15A4DAC5FA6726',0,1,NULL),(2,'ghost_customer','11111111111111111111111111111111',0,1,'客户一般不能登录，但是需要关联该数据,切勿不要删除'),(3,'yuangong1','D99C4EEEFC6D6428C43014FE9A35CEC2',0,1,NULL),(4,'yuangong2','0F3DB1890392616C3A746457DF811387',0,1,NULL),(5,'yuangong3','9745751582CD9271E24FCFCF0E48B22A',0,1,NULL);

/* Function  structure for function  `currval` */

/*!50003 DROP FUNCTION IF EXISTS `currval` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`mcb`@`%` FUNCTION `currval`(seq_name VARCHAR(50)) RETURNS int(11)
BEGIN
		 DECLARE VALUE INTEGER;
          SET VALUE = 0;
          SELECT current_value INTO VALUE
                    FROM sequence
                    WHERE NAME = seq_name;
          RETURN VALUE;
END */$$
DELIMITER ;

/* Function  structure for function  `func_get_split_string` */

/*!50003 DROP FUNCTION IF EXISTS `func_get_split_string` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `func_get_split_string`(f_string varchar(1000),f_delimiter varchar(5),f_order int) RETURNS varchar(255) CHARSET utf8
BEGIN
	     
	      -- 分割出字符中每个字符
	      -- 详情请见 http://blog.chinaunix.net/uid-259788-id-2139305.html
	      
	declare result varchar(255) default '';  
	set result = reverse(substring_index(reverse(substring_index(f_string,f_delimiter,f_order)),f_delimiter,1));  
	return result;
    END */$$
DELIMITER ;

/* Function  structure for function  `func_get_split_string_total` */

/*!50003 DROP FUNCTION IF EXISTS `func_get_split_string_total` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `func_get_split_string_total`(f_string VARCHAR(1000),f_delimiter VARCHAR(5)) RETURNS int(11)
BEGIN
         
	 --   用于分割字符串 统计出字符串里有多少个变量
	 --   详情请见 http://blog.chinaunix.net/uid-259788-id-2139305.html
         
	RETURN 1+(LENGTH(f_string) - LENGTH(REPLACE(f_string,f_delimiter,'')));
    END */$$
DELIMITER ;

/* Function  structure for function  `nextval` */

/*!50003 DROP FUNCTION IF EXISTS `nextval` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`mcb`@`%` FUNCTION `nextval`(seq_name VARCHAR(50)) RETURNS int(11)
BEGIN
          UPDATE sequence
                    SET current_value = current_value + increment
                    WHERE NAME = seq_name;
          RETURN currval(seq_name);
 END */$$
DELIMITER ;

/* Function  structure for function  `setval` */

/*!50003 DROP FUNCTION IF EXISTS `setval` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`mcb`@`%` FUNCTION `setval`(seq_name VARCHAR(50), VALUE INTEGER) RETURNS int(11)
BEGIN
          UPDATE sequence
                    SET current_value = VALUE
                    WHERE NAME = seq_name;
          RETURN currval(seq_name);
 END */$$
DELIMITER ;

/* Procedure structure for procedure `pro_order_add` */

/*!50003 DROP PROCEDURE IF EXISTS  `pro_order_add` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `pro_order_add`( IN customer_id  INT ,IN product_list  VARCHAR(255) )
BEGIN
	
	 DECLARE oeder_id int DEFAULT -1;
	 -- 定义客户等级ID
	 DECLARE cld INT DEFAULT -1;
	 
	 
         -- 定义分割 订单商品变量 
          DECLARE cnt INT DEFAULT 0;
	  DECLARE i INT DEFAULT 0;
	  DECLARE total decimal(10,4);
	  
	 SELECT  nextval('ORDER_SEQ') AS id into @oeder_id  FROM DUAL;
         select  customer_level_id into  @cld from customer where id=customer_id;
         
	  SET cnt = func_get_split_string_total(product_list,';');
	   DROP TABLE IF EXISTS tmp_print;
	   CREATE TEMPORARY TABLE tmp_print (num INT NOT NULL);
	   WHILE i < cnt
	   DO
	     SET i = i + 1;
	     INSERT INTO tmp_print(num) VALUES (func_get_split_string(product_list,';',i));
	   END WHILE;
	   
	   -- 获得总金额
	  SELECT SUM(vip_price) into @total FROM mcb.product_price t,tmp_print t2 WHERE t.customer_level_id=@cld  and t.product_id=t2.num;
	  
	  -- select num, order_id  from (select * from  tmp_print a ,(SELECT @oeder_id AS  order_id FROM DUAL ) b) c;
	  
	  -- 在订单表中插入记录
	  INSERT INTO mcb.order (id,create_time,settlemen,total,customer_id) VALUES (@oeder_id,SYSDATE(),0, @total,customer_id);
	  
	  -- 将order 和 product 关联 
	 INSERT INTO mcb.order_product (product_id,order_id) select num, order_id  from (select * from  tmp_print a ,(SELECT @oeder_id AS  order_id FROM DUAL ) b) c ;	  
		
    END */$$
DELIMITER ;

/* Procedure structure for procedure `sp_print_result` */

/*!50003 DROP PROCEDURE IF EXISTS  `sp_print_result` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_print_result`(
  IN f_string varchar(1000),IN f_delimiter varchar(5)
 )
BEGIN
   -- Get the separated string.
   declare cnt int default 0;
   declare i int default 0;
   set cnt = func_get_split_string_total(f_string,f_delimiter);
   drop table if exists tmp_print;
   create temporary table tmp_print (num int not null);
   while i < cnt
   do
     set i = i + 1;
     insert into tmp_print(num) values (func_get_split_string(f_string,f_delimiter,i));
   end while;
   select * from tmp_print;
   
 END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

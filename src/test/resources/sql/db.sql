CREATE TABLE `sys_user_id_alias` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(20) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户名称',
  `head_image` varchar(100) NOT NULL COMMENT '用户头像',
  `phone` char(11) NOT NULL COMMENT '手机',
  `sex` tinyint(2) DEFAULT NULL COMMENT '性别 0-女 1-男',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='主键为user_id,自增';



CREATE TABLE `sys_user_id_auto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(20) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户名称',
  `head_image` varchar(100) NOT NULL COMMENT '用户头像',
  `phone` char(11) NOT NULL COMMENT '手机',
  `sex` tinyint(2) DEFAULT NULL COMMENT '性别 0-女 1-男',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8 COMMENT='主键为id,自增';


CREATE TABLE `sys_user_id_snow` (
  `id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `user_name` varchar(20) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户名称',
  `head_image` varchar(100) NOT NULL COMMENT '用户头像',
  `phone` char(11) NOT NULL COMMENT '手机',
  `sex` tinyint(2) DEFAULT NULL COMMENT '性别 0-女 1-男',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主键为id,雪花';


CREATE TABLE `sys_user_id_snow_alias` (
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `user_name` varchar(20) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户名称',
  `head_image` varchar(100) NOT NULL COMMENT '用户头像',
  `phone` char(11) NOT NULL COMMENT '手机',
  `sex` tinyint(2) DEFAULT NULL COMMENT '性别 0-女 1-男',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主键为user_id,雪花';


CREATE TABLE `sys_user_id_varchar` (
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  `user_name` varchar(20) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户名称',
  `head_image` varchar(100) NOT NULL COMMENT '用户头像',
  `phone` char(11) NOT NULL COMMENT '手机',
  `sex` tinyint(2) DEFAULT NULL COMMENT '性别 0-女 1-男',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主键为user_id,雪花,字符串';

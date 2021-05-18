CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` enum('Teacher', 'Student') NOT NULL COMMENT '类型',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

insert into user (name, type, create_time) values ('AA', 'Student', CURRENT_TIMESTAMP)
CREATE TABLE `category`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `parent_id` int NOT NULL DEFAULT 0 COMMENT '父节点',
  `template` varchar(255) NOT NULL DEFAULT '' COMMENT '模板',
  PRIMARY KEY (`id`)
) COMMENT = '分类';

CREATE TABLE `menu`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `url` varchar(255) NOT NULL DEFAULT '' COMMENT '地址',
  `parent_id` int NOT NULL DEFAULT 0 COMMENT '父节点',
  `priority` int NOT NULL DEFAULT 0 COMMENT '优先级',
  PRIMARY KEY (`id`)
) COMMENT = '目录';

CREATE TABLE `page`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `template` varchar(255) NOT NULL DEFAULT '' COMMENT '模板',
  PRIMARY KEY (`id`)
) COMMENT = '页面';

CREATE TABLE `page_relate`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `page_id` bigint NOT NULL COMMENT '页面ID',
  `relate_id` int NOT NULL COMMENT '关联ID',
  `relate_type` tinyint NOT NULL DEFAULT 0 COMMENT '关联类型:1=分类',
  `priority` int NOT NULL DEFAULT 0 COMMENT '优先级',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '说明',
  PRIMARY KEY (`id`)
) COMMENT = '页面关联';

CREATE TABLE `post`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '标题',
  `content` text NOT NULL COMMENT '内容',
  `summary` varchar(255) NOT NULL DEFAULT '' COMMENT '简介',
  `thumbnail` varchar(255) NOT NULL DEFAULT '' COMMENT '缩略图',
  `category_id` int NULL COMMENT '分类ID',
  `type` tinyint NOT NULL DEFAULT 0 COMMENT '类型:1=文章;2=资源',
  PRIMARY KEY (`id`)
) COMMENT = '文章';

ALTER TABLE `page_relate` ADD CONSTRAINT `fk_page_relate_page_1` FOREIGN KEY (`page_id`) REFERENCES `page` (`id`);
ALTER TABLE `post` ADD CONSTRAINT `fk_post_category_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `parent_id` int NOT NULL DEFAULT 0 COMMENT '父节点',
  PRIMARY KEY (`id`)
) -- COMMENT = '分类'
;

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `url` varchar(255) NOT NULL DEFAULT '' COMMENT '地址',
  `parent_id` int NOT NULL DEFAULT 0 COMMENT '父节点',
  `priority` int NOT NULL DEFAULT 0 COMMENT '优先级',
  PRIMARY KEY (`id`)
) -- COMMENT = '目录'
;

DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '标题',
  `content` text NOT NULL COMMENT '内容',
  `category_id` int NOT NULL DEFAULT 0 COMMENT '分类ID',
  PRIMARY KEY (`id`)
) -- COMMENT = '文章'
;

ALTER TABLE `post` ADD CONSTRAINT `fk_post_category_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);


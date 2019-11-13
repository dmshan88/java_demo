SET FOREIGN_KEY_CHECKS = 0;
-- ----------------------------
-- Table structure for attendance_record
-- ----------------------------
CREATE TABLE IF NOT EXISTS `attendance_record`  (
  `id` bigint(20) NOT NULL,
  `base_check_time` datetime  NOT NULL,
  `check_type` int(11) NOT NULL,
  `location_result` int(11) NOT NULL,
  `time_result` int(11) NOT NULL,
  `user_check_time` datetime,
  `user_id` varchar(255) NOT NULL,
  `work_date` datetime NOT NULL,
  `proc_inst_id` varchar(255),
  `source_type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Table structure for department
-- ----------------------------
CREATE TABLE IF NOT EXISTS `department`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255)  NOT NULL,
  `parentid` bigint(20),
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Table structure for department_user
-- ----------------------------
CREATE TABLE IF NOT EXISTS `department_user`  (
  `user_id` varchar(255)  NOT NULL,
  `department_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`, `department_id`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE IF NOT EXISTS `user`  (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

SET FOREIGN_KEY_CHECKS = 1;

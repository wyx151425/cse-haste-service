CREATE TABLE `cse_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `object_id` char(32),
  `status` int(1),
  `create_at` datetime,
  `update_at` datetime,
  `name` varchar(8),
  `username` varchar (32),
  `password` varchar(32),
  `role` varchar(255),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE `cse_evaluation_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `object_id` char(32),
  `status` int(1),
  `create_at` datetime,
  `update_at` datetime,
  `name` varchar(32),
  `type` int(1),
  `start_at` datetime,
  `complete_at` datetime,
  `stage` int(1),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE `cse_evaluation_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `object_id` char(32),
  `status` int(1),
  `create_at` datetime,
  `update_at` datetime,
  `name` varchar(32),
  `complete` bit(1),
  `evaluation_plan_id` int(11),
  `evaluation_plan_name` varchar(32),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE `cse_evaluator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `object_id` char(32),
  `status` int(1),
  `create_at` datetime,
  `update_at` datetime,
  `user_id` int(11),
  `user_name` varchar(8),
  `evaluation_plan_id` int(11),
  `evaluation_plan_name` varchar(32),
  `evaluation_group_id` int(11),
  `evaluation_group_name` varchar(32),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE `cse_evaluatee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `object_id` char(32),
  `status` int(1),
  `create_at` datetime,
  `update_at` datetime,
  `user_id` int(11),
  `user_name` varchar(8),
  `evaluation_plan_id` int(11),
  `evaluation_plan_name` varchar(32),
  `evaluation_group_id` int(11),
  `evaluation_group_name` varchar(32),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE `cse_leadership_score_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `object_id` char(32),
  `status` int(1),
  `create_at` datetime,
  `update_at` datetime,
  `complete` bit(1),
  `politics_performance_1` int(2),
  `politics_performance_2` int(2),
  `politics_performance_3` int(2),
  `politics_performance_4` int(2),
  `politics_performance_5` int(2),
  `ability_and_quality_1` int(2),
  `ability_and_quality_2` int(2),
  `ability_and_quality_3` int(2),
  `ability_and_quality_4` int(2),
  `work_performance_1` int(2),
  `work_performance_2` int(2),
  `integrity_1` int(2),
  `integrity_2` int(2),
  `integrity_3` int(2),
  `evaluator_id` int(11),
  `evaluator_name` varchar(8),
  `evaluation_group_id` int(11),
  `evaluation_group_name` varchar(32),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE `cse_leader_cadre_score_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `object_id` char(32),
  `status` int(1),
  `create_at` datetime,
  `update_at` datetime,
  `complete` bit(1),
  `politics_performance_1` int(2),
  `politics_performance_2` int(2),
  `politics_performance_3` int(2),
  `politics_performance_4` int(2),
  `politics_performance_5` int(2),
  `ability_and_quality_1` int(2),
  `ability_and_quality_2` int(2),
  `ability_and_quality_3` int(2),
  `ability_and_quality_4` int(2),
  `work_performance_1` int(2),
  `work_performance_2` int(2),
  `integrity_1` int(2),
  `integrity_2` int(2),
  `integrity_3` int(2),
  `evaluator_id` int(11),
  `evaluator_name` varchar(8),
  `evaluatee_id` int(11),
  `evaluatee_name` varchar(8),
  `evaluation_group_id` int(11),
  `evaluation_group_name` varchar(32),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE `cse_professional_score_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `object_id` char(32),
  `status` int(1),
  `create_at` datetime,
  `update_at` datetime,
  `complete` bit(1),
  `moral_1` int(2),
  `moral_2` int(2),
  `quality_1` int(2),
  `quality_2` int(2),
  `quality_3` int(2),
  `ability_1` int(2),
  `ability_2` int(2),
  `ability_3` int(2),
  `performance_1` int(2),
  `performance_2` int(2),
  `performance_3` int(2),
  `evaluator_id` int(11),
  `evaluator_name` varchar(8),
  `evaluatee_id` int(11),
  `evaluatee_name` varchar(8),
  `evaluation_group_id` int(11),
  `evaluation_group_name` varchar(32),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;
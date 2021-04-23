ALTER TABLE <表名> ADD `create_by` char(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '创建者';
ALTER TABLE <表名> ADD `create_date` datetime NOT NULL COMMENT '创建时间';
ALTER TABLE <表名> ADD `update_by` char(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '更新者';
ALTER TABLE <表名> ADD `update_date` datetime NOT NULL COMMENT '更新时间';
ALTER TABLE <表名> ADD `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注';
ALTER TABLE <表名> ADD `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记: 0:正常  1：删除';
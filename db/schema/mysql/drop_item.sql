SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `drop_item`
-- ----------------------------
DROP TABLE IF EXISTS `drop_item`;
CREATE TABLE IF NOT EXISTS `drop_item` (
  `item_id` int(7) unsigned NOT NULL DEFAULT '0',
  `drop_rate` float unsigned NOT NULL DEFAULT '1',
  `drop_amount` float unsigned NOT NULL DEFAULT '1',
  `unique_rate` float unsigned NOT NULL DEFAULT '1',
  `note` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`item_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `getback`
-- ----------------------------
DROP TABLE IF EXISTS `getback`;
CREATE TABLE IF NOT EXISTS `getback` (
  `area_x1` int(10) unsigned NOT NULL DEFAULT '0',
  `area_y1` int(10) unsigned NOT NULL DEFAULT '0',
  `area_x2` int(10) unsigned NOT NULL DEFAULT '0',
  `area_y2` int(10) unsigned NOT NULL DEFAULT '0',
  `area_map_id` int(10) unsigned NOT NULL DEFAULT '0',
  `getback_x1` int(10) unsigned NOT NULL DEFAULT '0',
  `getback_y1` int(10) unsigned NOT NULL DEFAULT '0',
  `getback_x2` int(10) unsigned NOT NULL DEFAULT '0',
  `getback_y2` int(10) unsigned NOT NULL DEFAULT '0',
  `getback_x3` int(10) unsigned NOT NULL DEFAULT '0',
  `getback_y3` int(10) unsigned NOT NULL DEFAULT '0',
  `getback_map_id` int(10) unsigned NOT NULL DEFAULT '0',
  `getback_town_id` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `getback_town_id_elf` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `getback_town_id_darkelf` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `scroll_escape` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `note` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`area_x1`,`area_y1`,`area_x2`,`area_y2`,`area_map_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

use bbs;
CREATE TABLE `bbs_info` (
  `id` int NOT NULL auto_increment,
  `username` char(20) NOT NULL,
  `password` char(20) NOT NULL,
  `type` char(10) NOT NULL,
  `isLeaveURL` enum('false','true') not null,
  `awayFromLeaveURL` int  not null,
  `isSign` enum('false','true') not null,
  `awayFromSign` int not null,
  `totalMessage` varchar(255) NOT NULL DEFAULT '0' COMMENT '总共留言数',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


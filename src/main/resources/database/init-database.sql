USE basehub;

/* create admin user to create further user, this needs only for first run*/
INSERT IGNORE INTO `basehub`.`user`         (`name`, `password`, `isActive`) VALUES ('admin', '100:45fc4d7b475091fecacb9be9744bb8c1:d8a27c60be6d4ed25ec88ac992ab8a1b6e39a6a2f313e1fb3fd297d88f03edde', 1);
INSERT IGNORE INTO `basehub`.`user_role`    (`user_id`, `role`) VALUES ( (SELECT `id` FROM `user` WHERE `name` = 'admin'), 'ADMIN');
INSERT IGNORE INTO `basehub`.`account`      (`id`, `name`) VALUES ( (SELECT `id` FROM `user` WHERE `name` = 'admin'), 'baseHub Account');

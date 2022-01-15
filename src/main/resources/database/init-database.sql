USE basehub;

/* create admin user to create further user this needs only for first run*/
INSERT IGNORE INTO `basehub`.`user`         (`name`, `password`) VALUES ('admin', '100:8e850a82f3fa6d5dc3d48266d0d90d43:7e698cc8fc125849e7cd4dfd9ea4fb9acc6ba2290e4338c4ae0b54f36dc88316');
INSERT IGNORE INTO `basehub`.`user_role`    (`user_id`, `role`) VALUES ( (SELECT `id` FROM `user` WHERE `name` = 'admin'), 'ADMIN');
INSERT IGNORE INTO `basehub`.`account`      (`id`, `name`) VALUES ( (SELECT `id` FROM `user` WHERE `name` = 'admin'), 'Master Account');


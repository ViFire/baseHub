USE basehub;

/* create admin user to create further user this needs only for first run*/
INSERT IGNORE INTO `basehub`.`user`         (`name`, `password`, `isActive`) VALUES ('admin', '100:7cdba8df7cef320bd8522e974a3c866f:a9209b617eddc61dc822e8e538bf6f81ad5fb26e76919261de7bde01f21ebe8b', 1);
INSERT IGNORE INTO `basehub`.`user_role`    (`user_id`, `role`) VALUES ( (SELECT `id` FROM `user` WHERE `name` = 'admin'), 'ADMIN');
INSERT IGNORE INTO `basehub`.`account`      (`id`, `name`) VALUES ( (SELECT `id` FROM `user` WHERE `name` = 'admin'), 'Master Account');


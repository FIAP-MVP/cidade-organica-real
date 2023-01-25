ALTER TABLE `address` ADD CONSTRAINT `fk_user` FOREIGN KEY (user_id) REFERENCES `user`(id);
ALTER TABLE `user` DROP FOREIGN KEY `fk_adress`;

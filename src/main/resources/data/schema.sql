  CREATE TABLE `db`.`user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `fingerprint` VARCHAR(512) NOT NULL,
  PRIMARY KEY (`id`));

  
  CREATE TABLE `db`.`timetable` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `id_user` BIGINT(20)NOT NULL,
  `action_date` DATETIME NOT NULL,
  `action` BOOL NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_TIMETABLE_USER_ID` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION);
  